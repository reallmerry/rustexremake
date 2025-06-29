/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.sevenz;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import java.util.zip.CRC32;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.AES256Options;
import org.apache.commons.compress.archivers.sevenz.Coders;
import org.apache.commons.compress.archivers.sevenz.SevenZArchiveEntry;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.sevenz.SevenZMethod;
import org.apache.commons.compress.archivers.sevenz.SevenZMethodConfiguration;
import org.apache.commons.compress.utils.CountingOutputStream;
import org.apache.commons.compress.utils.TimeUtils;

public class SevenZOutputFile
implements Closeable {
    private final SeekableByteChannel channel;
    private final List<SevenZArchiveEntry> files = new ArrayList<SevenZArchiveEntry>();
    private int numNonEmptyStreams;
    private final CRC32 crc32 = new CRC32();
    private final CRC32 compressedCrc32 = new CRC32();
    private long fileBytesWritten;
    private boolean finished;
    private CountingOutputStream currentOutputStream;
    private CountingOutputStream[] additionalCountingStreams;
    private Iterable<? extends SevenZMethodConfiguration> contentMethods = Collections.singletonList(new SevenZMethodConfiguration(SevenZMethod.LZMA2));
    private final Map<SevenZArchiveEntry, long[]> additionalSizes = new HashMap<SevenZArchiveEntry, long[]>();
    private AES256Options aes256Options;

    private static <T> Iterable<T> reverse(Iterable<T> i2) {
        LinkedList<T> l2 = new LinkedList<T>();
        for (T t2 : i2) {
            l2.addFirst(t2);
        }
        return l2;
    }

    public SevenZOutputFile(File fileName) throws IOException {
        this(fileName, null);
    }

    public SevenZOutputFile(File fileName, char[] password) throws IOException {
        this(Files.newByteChannel(fileName.toPath(), EnumSet.of(StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING), new FileAttribute[0]), password);
    }

    public SevenZOutputFile(SeekableByteChannel channel) throws IOException {
        this(channel, null);
    }

    public SevenZOutputFile(SeekableByteChannel channel, char[] password) throws IOException {
        this.channel = channel;
        channel.position(32L);
        if (password != null) {
            this.aes256Options = new AES256Options(password);
        }
    }

    @Override
    public void close() throws IOException {
        try {
            if (!this.finished) {
                this.finish();
            }
        } finally {
            this.channel.close();
        }
    }

    public void closeArchiveEntry() throws IOException {
        if (this.currentOutputStream != null) {
            this.currentOutputStream.flush();
            this.currentOutputStream.close();
        }
        SevenZArchiveEntry entry = this.files.get(this.files.size() - 1);
        if (this.fileBytesWritten > 0L) {
            entry.setHasStream(true);
            ++this.numNonEmptyStreams;
            entry.setSize(this.currentOutputStream.getBytesWritten());
            entry.setCompressedSize(this.fileBytesWritten);
            entry.setCrcValue(this.crc32.getValue());
            entry.setCompressedCrcValue(this.compressedCrc32.getValue());
            entry.setHasCrc(true);
            if (this.additionalCountingStreams != null) {
                long[] sizes = new long[this.additionalCountingStreams.length];
                Arrays.setAll(sizes, i2 -> this.additionalCountingStreams[i2].getBytesWritten());
                this.additionalSizes.put(entry, sizes);
            }
        } else {
            entry.setHasStream(false);
            entry.setSize(0L);
            entry.setCompressedSize(0L);
            entry.setHasCrc(false);
        }
        this.currentOutputStream = null;
        this.additionalCountingStreams = null;
        this.crc32.reset();
        this.compressedCrc32.reset();
        this.fileBytesWritten = 0L;
    }

    public SevenZArchiveEntry createArchiveEntry(File inputFile, String entryName) {
        SevenZArchiveEntry entry = new SevenZArchiveEntry();
        entry.setDirectory(inputFile.isDirectory());
        entry.setName(entryName);
        try {
            this.fillDates(inputFile.toPath(), entry, new LinkOption[0]);
        } catch (IOException e2) {
            entry.setLastModifiedDate(new Date(inputFile.lastModified()));
        }
        return entry;
    }

    public SevenZArchiveEntry createArchiveEntry(Path inputPath, String entryName, LinkOption ... options) throws IOException {
        SevenZArchiveEntry entry = new SevenZArchiveEntry();
        entry.setDirectory(Files.isDirectory(inputPath, options));
        entry.setName(entryName);
        this.fillDates(inputPath, entry, options);
        return entry;
    }

    private void fillDates(Path inputPath, SevenZArchiveEntry entry, LinkOption ... options) throws IOException {
        BasicFileAttributes attributes = Files.readAttributes(inputPath, BasicFileAttributes.class, options);
        entry.setLastModifiedTime(attributes.lastModifiedTime());
        entry.setCreationTime(attributes.creationTime());
        entry.setAccessTime(attributes.lastAccessTime());
    }

    public void finish() throws IOException {
        if (this.finished) {
            throw new IOException("This archive has already been finished");
        }
        this.finished = true;
        long headerPosition = this.channel.position();
        ByteArrayOutputStream headerBaos = new ByteArrayOutputStream();
        DataOutputStream header = new DataOutputStream(headerBaos);
        this.writeHeader(header);
        header.flush();
        byte[] headerBytes = headerBaos.toByteArray();
        this.channel.write(ByteBuffer.wrap(headerBytes));
        CRC32 crc32 = new CRC32();
        crc32.update(headerBytes);
        ByteBuffer bb = ByteBuffer.allocate(SevenZFile.sevenZSignature.length + 2 + 4 + 8 + 8 + 4).order(ByteOrder.LITTLE_ENDIAN);
        this.channel.position(0L);
        bb.put(SevenZFile.sevenZSignature);
        bb.put((byte)0).put((byte)2);
        bb.putInt(0);
        bb.putLong(headerPosition - 32L).putLong(0xFFFFFFFFL & (long)headerBytes.length).putInt((int)crc32.getValue());
        crc32.reset();
        crc32.update(bb.array(), SevenZFile.sevenZSignature.length + 6, 20);
        bb.putInt(SevenZFile.sevenZSignature.length + 2, (int)crc32.getValue());
        bb.flip();
        this.channel.write(bb);
    }

    private Iterable<? extends SevenZMethodConfiguration> getContentMethods(SevenZArchiveEntry entry) {
        Iterable<SevenZMethodConfiguration> iter;
        Iterable<SevenZMethodConfiguration> ms = entry.getContentMethods();
        Iterable<SevenZMethodConfiguration> iterable = iter = ms == null ? this.contentMethods : ms;
        if (this.aes256Options != null) {
            iter = Stream.concat(Stream.of(new SevenZMethodConfiguration(SevenZMethod.AES256SHA256, this.aes256Options)), StreamSupport.stream(iter.spliterator(), false)).collect(Collectors.toList());
        }
        return iter;
    }

    private OutputStream getCurrentOutputStream() throws IOException {
        if (this.currentOutputStream == null) {
            this.currentOutputStream = this.setupFileOutputStream();
        }
        return this.currentOutputStream;
    }

    public void putArchiveEntry(ArchiveEntry archiveEntry) {
        SevenZArchiveEntry entry = (SevenZArchiveEntry)archiveEntry;
        this.files.add(entry);
    }

    public void setContentCompression(SevenZMethod method) {
        this.setContentMethods(Collections.singletonList(new SevenZMethodConfiguration(method)));
    }

    public void setContentMethods(Iterable<? extends SevenZMethodConfiguration> methods) {
        this.contentMethods = SevenZOutputFile.reverse(methods);
    }

    private CountingOutputStream setupFileOutputStream() throws IOException {
        if (this.files.isEmpty()) {
            throw new IllegalStateException("No current 7z entry");
        }
        OutputStream out = new OutputStreamWrapper();
        ArrayList<CountingOutputStream> moreStreams = new ArrayList<CountingOutputStream>();
        boolean first = true;
        for (SevenZMethodConfiguration sevenZMethodConfiguration : this.getContentMethods(this.files.get(this.files.size() - 1))) {
            if (!first) {
                CountingOutputStream cos = new CountingOutputStream(out);
                moreStreams.add(cos);
                out = cos;
            }
            out = Coders.addEncoder(out, sevenZMethodConfiguration.getMethod(), sevenZMethodConfiguration.getOptions());
            first = false;
        }
        if (!moreStreams.isEmpty()) {
            this.additionalCountingStreams = moreStreams.toArray(new CountingOutputStream[0]);
        }
        return new CountingOutputStream(out){

            @Override
            public void write(byte[] b2) throws IOException {
                super.write(b2);
                SevenZOutputFile.this.crc32.update(b2);
            }

            @Override
            public void write(byte[] b2, int off, int len) throws IOException {
                super.write(b2, off, len);
                SevenZOutputFile.this.crc32.update(b2, off, len);
            }

            @Override
            public void write(int b2) throws IOException {
                super.write(b2);
                SevenZOutputFile.this.crc32.update(b2);
            }
        };
    }

    public void write(byte[] b2) throws IOException {
        this.write(b2, 0, b2.length);
    }

    public void write(byte[] b2, int off, int len) throws IOException {
        if (len > 0) {
            this.getCurrentOutputStream().write(b2, off, len);
        }
    }

    public void write(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[8024];
        int n2 = 0;
        while (-1 != (n2 = inputStream.read(buffer))) {
            this.write(buffer, 0, n2);
        }
    }

    public void write(int b2) throws IOException {
        this.getCurrentOutputStream().write(b2);
    }

    public void write(Path path, OpenOption ... options) throws IOException {
        try (BufferedInputStream in = new BufferedInputStream(Files.newInputStream(path, options));){
            this.write(in);
        }
    }

    private void writeBits(DataOutput header, BitSet bits, int length) throws IOException {
        int cache = 0;
        int shift = 7;
        for (int i2 = 0; i2 < length; ++i2) {
            cache |= (bits.get(i2) ? 1 : 0) << shift;
            if (--shift >= 0) continue;
            header.write(cache);
            shift = 7;
            cache = 0;
        }
        if (shift != 7) {
            header.write(cache);
        }
    }

    private void writeFileAntiItems(DataOutput header) throws IOException {
        boolean hasAntiItems = false;
        BitSet antiItems = new BitSet(0);
        int antiItemCounter = 0;
        for (SevenZArchiveEntry file1 : this.files) {
            if (file1.hasStream()) continue;
            boolean isAnti = file1.isAntiItem();
            antiItems.set(antiItemCounter++, isAnti);
            hasAntiItems |= isAnti;
        }
        if (hasAntiItems) {
            header.write(16);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(baos);
            this.writeBits(out, antiItems, antiItemCounter);
            out.flush();
            byte[] contents = baos.toByteArray();
            this.writeUint64(header, contents.length);
            header.write(contents);
        }
    }

    private void writeFileATimes(DataOutput header) throws IOException {
        int numAccessDates = 0;
        for (SevenZArchiveEntry entry : this.files) {
            if (!entry.getHasAccessDate()) continue;
            ++numAccessDates;
        }
        if (numAccessDates > 0) {
            header.write(19);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(baos);
            if (numAccessDates != this.files.size()) {
                out.write(0);
                BitSet aTimes = new BitSet(this.files.size());
                for (int i2 = 0; i2 < this.files.size(); ++i2) {
                    aTimes.set(i2, this.files.get(i2).getHasAccessDate());
                }
                this.writeBits(out, aTimes, this.files.size());
            } else {
                out.write(1);
            }
            out.write(0);
            for (SevenZArchiveEntry entry : this.files) {
                if (!entry.getHasAccessDate()) continue;
                long ntfsTime = TimeUtils.toNtfsTime(entry.getAccessTime());
                out.writeLong(Long.reverseBytes(ntfsTime));
            }
            out.flush();
            byte[] contents = baos.toByteArray();
            this.writeUint64(header, contents.length);
            header.write(contents);
        }
    }

    private void writeFileCTimes(DataOutput header) throws IOException {
        int numCreationDates = 0;
        for (SevenZArchiveEntry entry : this.files) {
            if (!entry.getHasCreationDate()) continue;
            ++numCreationDates;
        }
        if (numCreationDates > 0) {
            header.write(18);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(baos);
            if (numCreationDates != this.files.size()) {
                out.write(0);
                BitSet cTimes = new BitSet(this.files.size());
                for (int i2 = 0; i2 < this.files.size(); ++i2) {
                    cTimes.set(i2, this.files.get(i2).getHasCreationDate());
                }
                this.writeBits(out, cTimes, this.files.size());
            } else {
                out.write(1);
            }
            out.write(0);
            for (SevenZArchiveEntry entry : this.files) {
                if (!entry.getHasCreationDate()) continue;
                long ntfsTime = TimeUtils.toNtfsTime(entry.getCreationTime());
                out.writeLong(Long.reverseBytes(ntfsTime));
            }
            out.flush();
            byte[] contents = baos.toByteArray();
            this.writeUint64(header, contents.length);
            header.write(contents);
        }
    }

    private void writeFileEmptyFiles(DataOutput header) throws IOException {
        boolean hasEmptyFiles = false;
        int emptyStreamCounter = 0;
        BitSet emptyFiles = new BitSet(0);
        for (SevenZArchiveEntry file1 : this.files) {
            if (file1.hasStream()) continue;
            boolean isDir = file1.isDirectory();
            emptyFiles.set(emptyStreamCounter++, !isDir);
            hasEmptyFiles |= !isDir;
        }
        if (hasEmptyFiles) {
            header.write(15);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(baos);
            this.writeBits(out, emptyFiles, emptyStreamCounter);
            out.flush();
            byte[] contents = baos.toByteArray();
            this.writeUint64(header, contents.length);
            header.write(contents);
        }
    }

    private void writeFileEmptyStreams(DataOutput header) throws IOException {
        boolean hasEmptyStreams = this.files.stream().anyMatch(entry -> !entry.hasStream());
        if (hasEmptyStreams) {
            header.write(14);
            BitSet emptyStreams = new BitSet(this.files.size());
            for (int i2 = 0; i2 < this.files.size(); ++i2) {
                emptyStreams.set(i2, !this.files.get(i2).hasStream());
            }
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(baos);
            this.writeBits(out, emptyStreams, this.files.size());
            out.flush();
            byte[] contents = baos.toByteArray();
            this.writeUint64(header, contents.length);
            header.write(contents);
        }
    }

    private void writeFileMTimes(DataOutput header) throws IOException {
        int numLastModifiedDates = 0;
        for (SevenZArchiveEntry entry : this.files) {
            if (!entry.getHasLastModifiedDate()) continue;
            ++numLastModifiedDates;
        }
        if (numLastModifiedDates > 0) {
            header.write(20);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(baos);
            if (numLastModifiedDates != this.files.size()) {
                out.write(0);
                BitSet mTimes = new BitSet(this.files.size());
                for (int i2 = 0; i2 < this.files.size(); ++i2) {
                    mTimes.set(i2, this.files.get(i2).getHasLastModifiedDate());
                }
                this.writeBits(out, mTimes, this.files.size());
            } else {
                out.write(1);
            }
            out.write(0);
            for (SevenZArchiveEntry entry : this.files) {
                if (!entry.getHasLastModifiedDate()) continue;
                long ntfsTime = TimeUtils.toNtfsTime(entry.getLastModifiedTime());
                out.writeLong(Long.reverseBytes(ntfsTime));
            }
            out.flush();
            byte[] contents = baos.toByteArray();
            this.writeUint64(header, contents.length);
            header.write(contents);
        }
    }

    private void writeFileNames(DataOutput header) throws IOException {
        header.write(17);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);
        out.write(0);
        for (SevenZArchiveEntry entry : this.files) {
            out.write(entry.getName().getBytes(StandardCharsets.UTF_16LE));
            out.writeShort(0);
        }
        out.flush();
        byte[] contents = baos.toByteArray();
        this.writeUint64(header, contents.length);
        header.write(contents);
    }

    private void writeFilesInfo(DataOutput header) throws IOException {
        header.write(5);
        this.writeUint64(header, this.files.size());
        this.writeFileEmptyStreams(header);
        this.writeFileEmptyFiles(header);
        this.writeFileAntiItems(header);
        this.writeFileNames(header);
        this.writeFileCTimes(header);
        this.writeFileATimes(header);
        this.writeFileMTimes(header);
        this.writeFileWindowsAttributes(header);
        header.write(0);
    }

    private void writeFileWindowsAttributes(DataOutput header) throws IOException {
        int numWindowsAttributes = 0;
        for (SevenZArchiveEntry entry : this.files) {
            if (!entry.getHasWindowsAttributes()) continue;
            ++numWindowsAttributes;
        }
        if (numWindowsAttributes > 0) {
            header.write(21);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream out = new DataOutputStream(baos);
            if (numWindowsAttributes != this.files.size()) {
                out.write(0);
                BitSet attributes = new BitSet(this.files.size());
                for (int i2 = 0; i2 < this.files.size(); ++i2) {
                    attributes.set(i2, this.files.get(i2).getHasWindowsAttributes());
                }
                this.writeBits(out, attributes, this.files.size());
            } else {
                out.write(1);
            }
            out.write(0);
            for (SevenZArchiveEntry entry : this.files) {
                if (!entry.getHasWindowsAttributes()) continue;
                out.writeInt(Integer.reverseBytes(entry.getWindowsAttributes()));
            }
            out.flush();
            byte[] contents = baos.toByteArray();
            this.writeUint64(header, contents.length);
            header.write(contents);
        }
    }

    private void writeFolder(DataOutput header, SevenZArchiveEntry entry) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int numCoders = 0;
        for (SevenZMethodConfiguration sevenZMethodConfiguration : this.getContentMethods(entry)) {
            ++numCoders;
            this.writeSingleCodec(sevenZMethodConfiguration, bos);
        }
        this.writeUint64(header, numCoders);
        header.write(bos.toByteArray());
        for (long i2 = 0L; i2 < (long)(numCoders - 1); ++i2) {
            this.writeUint64(header, i2 + 1L);
            this.writeUint64(header, i2);
        }
    }

    private void writeHeader(DataOutput header) throws IOException {
        header.write(1);
        header.write(4);
        this.writeStreamsInfo(header);
        this.writeFilesInfo(header);
        header.write(0);
    }

    private void writePackInfo(DataOutput header) throws IOException {
        header.write(6);
        this.writeUint64(header, 0L);
        this.writeUint64(header, 0xFFFFFFFFL & (long)this.numNonEmptyStreams);
        header.write(9);
        for (SevenZArchiveEntry entry : this.files) {
            if (!entry.hasStream()) continue;
            this.writeUint64(header, entry.getCompressedSize());
        }
        header.write(10);
        header.write(1);
        for (SevenZArchiveEntry entry : this.files) {
            if (!entry.hasStream()) continue;
            header.writeInt(Integer.reverseBytes((int)entry.getCompressedCrcValue()));
        }
        header.write(0);
    }

    private void writeSingleCodec(SevenZMethodConfiguration m2, OutputStream bos) throws IOException {
        byte[] id = m2.getMethod().getId();
        byte[] properties = Coders.findByMethod(m2.getMethod()).getOptionsAsProperties(m2.getOptions());
        int codecFlags = id.length;
        if (properties.length > 0) {
            codecFlags |= 0x20;
        }
        bos.write(codecFlags);
        bos.write(id);
        if (properties.length > 0) {
            bos.write(properties.length);
            bos.write(properties);
        }
    }

    private void writeStreamsInfo(DataOutput header) throws IOException {
        if (this.numNonEmptyStreams > 0) {
            this.writePackInfo(header);
            this.writeUnpackInfo(header);
        }
        this.writeSubStreamsInfo(header);
        header.write(0);
    }

    private void writeSubStreamsInfo(DataOutput header) throws IOException {
        header.write(8);
        header.write(0);
    }

    private void writeUint64(DataOutput header, long value) throws IOException {
        int i2;
        int firstByte = 0;
        int mask = 128;
        for (i2 = 0; i2 < 8; ++i2) {
            if (value < 1L << 7 * (i2 + 1)) {
                firstByte = (int)((long)firstByte | value >>> 8 * i2);
                break;
            }
            firstByte |= mask;
            mask >>>= 1;
        }
        header.write(firstByte);
        while (i2 > 0) {
            header.write((int)(0xFFL & value));
            value >>>= 8;
            --i2;
        }
    }

    private void writeUnpackInfo(DataOutput header) throws IOException {
        header.write(7);
        header.write(11);
        this.writeUint64(header, this.numNonEmptyStreams);
        header.write(0);
        for (SevenZArchiveEntry entry : this.files) {
            if (!entry.hasStream()) continue;
            this.writeFolder(header, entry);
        }
        header.write(12);
        for (SevenZArchiveEntry entry : this.files) {
            if (!entry.hasStream()) continue;
            long[] moreSizes = this.additionalSizes.get(entry);
            if (moreSizes != null) {
                for (long s2 : moreSizes) {
                    this.writeUint64(header, s2);
                }
            }
            this.writeUint64(header, entry.getSize());
        }
        header.write(10);
        header.write(1);
        for (SevenZArchiveEntry entry : this.files) {
            if (!entry.hasStream()) continue;
            header.writeInt(Integer.reverseBytes((int)entry.getCrcValue()));
        }
        header.write(0);
    }

    private class OutputStreamWrapper
    extends OutputStream {
        private static final int BUF_SIZE = 8192;
        private final ByteBuffer buffer = ByteBuffer.allocate(8192);

        private OutputStreamWrapper() {
        }

        @Override
        public void close() throws IOException {
        }

        @Override
        public void flush() throws IOException {
        }

        @Override
        public void write(byte[] b2) throws IOException {
            this.write(b2, 0, b2.length);
        }

        @Override
        public void write(byte[] b2, int off, int len) throws IOException {
            if (len > 8192) {
                SevenZOutputFile.this.channel.write(ByteBuffer.wrap(b2, off, len));
            } else {
                this.buffer.clear();
                this.buffer.put(b2, off, len).flip();
                SevenZOutputFile.this.channel.write(this.buffer);
            }
            SevenZOutputFile.this.compressedCrc32.update(b2, off, len);
            SevenZOutputFile.this.fileBytesWritten += len;
        }

        @Override
        public void write(int b2) throws IOException {
            this.buffer.clear();
            this.buffer.put((byte)b2).flip();
            SevenZOutputFile.this.channel.write(this.buffer);
            SevenZOutputFile.this.compressedCrc32.update(b2);
            SevenZOutputFile.this.fileBytesWritten++;
        }
    }
}

