/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Objects;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.zip.ExplodingInputStream;
import org.apache.commons.compress.archivers.zip.GeneralPurposeBit;
import org.apache.commons.compress.archivers.zip.UnshrinkingInputStream;
import org.apache.commons.compress.archivers.zip.UnsupportedZipFeatureException;
import org.apache.commons.compress.archivers.zip.Zip64ExtendedInformationExtraField;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipEightByteInteger;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.archivers.zip.ZipExtraField;
import org.apache.commons.compress.archivers.zip.ZipLong;
import org.apache.commons.compress.archivers.zip.ZipMethod;
import org.apache.commons.compress.archivers.zip.ZipShort;
import org.apache.commons.compress.archivers.zip.ZipUtil;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream;
import org.apache.commons.compress.compressors.deflate64.Deflate64CompressorInputStream;
import org.apache.commons.compress.utils.ArchiveUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;

public class ZipArchiveInputStream
extends ArchiveInputStream
implements InputStreamStatistics {
    private static final int LFH_LEN = 30;
    private static final int CFH_LEN = 46;
    private static final long TWO_EXP_32 = 0x100000000L;
    private static final String USE_ZIPFILE_INSTEAD_OF_STREAM_DISCLAIMER = " while reading a stored entry using data descriptor. Either the archive is broken or it can not be read using ZipArchiveInputStream and you must use ZipFile. A common cause for this is a ZIP archive containing a ZIP archive. See http://commons.apache.org/proper/commons-compress/zip.html#ZipArchiveInputStream_vs_ZipFile";
    private static final byte[] LFH = ZipLong.LFH_SIG.getBytes();
    private static final byte[] CFH = ZipLong.CFH_SIG.getBytes();
    private static final byte[] DD = ZipLong.DD_SIG.getBytes();
    private static final byte[] APK_SIGNING_BLOCK_MAGIC = new byte[]{65, 80, 75, 32, 83, 105, 103, 32, 66, 108, 111, 99, 107, 32, 52, 50};
    private static final BigInteger LONG_MAX = BigInteger.valueOf(Long.MAX_VALUE);
    private final ZipEncoding zipEncoding;
    final String encoding;
    private final boolean useUnicodeExtraFields;
    private final InputStream inputStream;
    private final Inflater inf = new Inflater(true);
    private final ByteBuffer buf = ByteBuffer.allocate(512);
    private CurrentEntry current;
    private boolean closed;
    private boolean hitCentralDirectory;
    private ByteArrayInputStream lastStoredEntry;
    private final boolean allowStoredEntriesWithDataDescriptor;
    private long uncompressedCount;
    private final boolean skipSplitSig;
    private final byte[] lfhBuf = new byte[30];
    private final byte[] skipBuf = new byte[1024];
    private final byte[] shortBuf = new byte[2];
    private final byte[] wordBuf = new byte[4];
    private final byte[] twoDwordBuf = new byte[16];
    private int entriesRead;

    private static boolean checksig(byte[] signature, byte[] expected) {
        for (int i2 = 0; i2 < expected.length; ++i2) {
            if (signature[i2] == expected[i2]) continue;
            return false;
        }
        return true;
    }

    public static boolean matches(byte[] signature, int length) {
        if (length < ZipArchiveOutputStream.LFH_SIG.length) {
            return false;
        }
        return ZipArchiveInputStream.checksig(signature, ZipArchiveOutputStream.LFH_SIG) || ZipArchiveInputStream.checksig(signature, ZipArchiveOutputStream.EOCD_SIG) || ZipArchiveInputStream.checksig(signature, ZipArchiveOutputStream.DD_SIG) || ZipArchiveInputStream.checksig(signature, ZipLong.SINGLE_SEGMENT_SPLIT_MARKER.getBytes());
    }

    public ZipArchiveInputStream(InputStream inputStream) {
        this(inputStream, "UTF8");
    }

    public ZipArchiveInputStream(InputStream inputStream, String encoding) {
        this(inputStream, encoding, true);
    }

    public ZipArchiveInputStream(InputStream inputStream, String encoding, boolean useUnicodeExtraFields) {
        this(inputStream, encoding, useUnicodeExtraFields, false);
    }

    public ZipArchiveInputStream(InputStream inputStream, String encoding, boolean useUnicodeExtraFields, boolean allowStoredEntriesWithDataDescriptor) {
        this(inputStream, encoding, useUnicodeExtraFields, allowStoredEntriesWithDataDescriptor, false);
    }

    public ZipArchiveInputStream(InputStream inputStream, String encoding, boolean useUnicodeExtraFields, boolean allowStoredEntriesWithDataDescriptor, boolean skipSplitSig) {
        this.encoding = encoding;
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(encoding);
        this.useUnicodeExtraFields = useUnicodeExtraFields;
        this.inputStream = new PushbackInputStream(inputStream, this.buf.capacity());
        this.allowStoredEntriesWithDataDescriptor = allowStoredEntriesWithDataDescriptor;
        this.skipSplitSig = skipSplitSig;
        this.buf.limit(0);
    }

    private boolean bufferContainsSignature(ByteArrayOutputStream bos, int offset, int lastRead, int expectedDDLen) throws IOException {
        boolean done = false;
        for (int i2 = 0; !done && i2 < offset + lastRead - 4; ++i2) {
            if (this.buf.array()[i2] != LFH[0] || this.buf.array()[i2 + 1] != LFH[1]) continue;
            int expectDDPos = i2;
            if (i2 >= expectedDDLen && this.buf.array()[i2 + 2] == LFH[2] && this.buf.array()[i2 + 3] == LFH[3] || this.buf.array()[i2 + 2] == CFH[2] && this.buf.array()[i2 + 3] == CFH[3]) {
                expectDDPos = i2 - expectedDDLen;
                done = true;
            } else if (this.buf.array()[i2 + 2] == DD[2] && this.buf.array()[i2 + 3] == DD[3]) {
                done = true;
            }
            if (!done) continue;
            this.pushback(this.buf.array(), expectDDPos, offset + lastRead - expectDDPos);
            bos.write(this.buf.array(), 0, expectDDPos);
            this.readDataDescriptor();
        }
        return done;
    }

    private int cacheBytesRead(ByteArrayOutputStream bos, int offset, int lastRead, int expectedDDLen) {
        int cacheable = offset + lastRead - expectedDDLen - 3;
        if (cacheable > 0) {
            bos.write(this.buf.array(), 0, cacheable);
            System.arraycopy(this.buf.array(), cacheable, this.buf.array(), 0, expectedDDLen + 3);
            offset = expectedDDLen + 3;
        } else {
            offset += lastRead;
        }
        return offset;
    }

    @Override
    public boolean canReadEntryData(ArchiveEntry ae) {
        if (ae instanceof ZipArchiveEntry) {
            ZipArchiveEntry ze = (ZipArchiveEntry)ae;
            return ZipUtil.canHandleEntryData(ze) && this.supportsDataDescriptorFor(ze) && this.supportsCompressedSizeFor(ze);
        }
        return false;
    }

    @Override
    public void close() throws IOException {
        if (!this.closed) {
            this.closed = true;
            try {
                this.inputStream.close();
            } finally {
                this.inf.end();
            }
        }
    }

    private void closeEntry() throws IOException {
        if (this.closed) {
            throw new IOException("The stream is closed");
        }
        if (this.current == null) {
            return;
        }
        if (this.currentEntryHasOutstandingBytes()) {
            this.drainCurrentEntryData();
        } else {
            this.skip(Long.MAX_VALUE);
            long inB = this.current.entry.getMethod() == 8 ? this.getBytesInflated() : this.current.bytesRead;
            int diff = (int)(this.current.bytesReadFromStream - inB);
            if (diff > 0) {
                this.pushback(this.buf.array(), this.buf.limit() - diff, diff);
                this.current.bytesReadFromStream -= diff;
            }
            if (this.currentEntryHasOutstandingBytes()) {
                this.drainCurrentEntryData();
            }
        }
        if (this.lastStoredEntry == null && this.current.hasDataDescriptor) {
            this.readDataDescriptor();
        }
        this.inf.reset();
        this.buf.clear().flip();
        this.current = null;
        this.lastStoredEntry = null;
    }

    private boolean currentEntryHasOutstandingBytes() {
        return this.current.bytesReadFromStream <= this.current.entry.getCompressedSize() && !this.current.hasDataDescriptor;
    }

    private void drainCurrentEntryData() throws IOException {
        long n2;
        for (long remaining = this.current.entry.getCompressedSize() - this.current.bytesReadFromStream; remaining > 0L; remaining -= n2) {
            n2 = this.inputStream.read(this.buf.array(), 0, (int)Math.min((long)this.buf.capacity(), remaining));
            if (n2 < 0L) {
                throw new EOFException("Truncated ZIP entry: " + ArchiveUtils.sanitize(this.current.entry.getName()));
            }
            this.count(n2);
        }
    }

    private int fill() throws IOException {
        if (this.closed) {
            throw new IOException("The stream is closed");
        }
        int length = this.inputStream.read(this.buf.array());
        if (length > 0) {
            this.buf.limit(length);
            this.count(this.buf.limit());
            this.inf.setInput(this.buf.array(), 0, this.buf.limit());
        }
        return length;
    }

    private boolean findEocdRecord() throws IOException {
        int currentByte = -1;
        boolean skipReadCall = false;
        while (skipReadCall || (currentByte = this.readOneByte()) > -1) {
            skipReadCall = false;
            if (!this.isFirstByteOfEocdSig(currentByte)) continue;
            currentByte = this.readOneByte();
            if (currentByte != ZipArchiveOutputStream.EOCD_SIG[1]) {
                if (currentByte == -1) break;
                skipReadCall = this.isFirstByteOfEocdSig(currentByte);
                continue;
            }
            currentByte = this.readOneByte();
            if (currentByte != ZipArchiveOutputStream.EOCD_SIG[2]) {
                if (currentByte == -1) break;
                skipReadCall = this.isFirstByteOfEocdSig(currentByte);
                continue;
            }
            currentByte = this.readOneByte();
            if (currentByte == -1) break;
            if (currentByte == ZipArchiveOutputStream.EOCD_SIG[3]) {
                return true;
            }
            skipReadCall = this.isFirstByteOfEocdSig(currentByte);
        }
        return false;
    }

    private long getBytesInflated() {
        long inB = this.inf.getBytesRead();
        if (this.current.bytesReadFromStream >= 0x100000000L) {
            while (inB + 0x100000000L <= this.current.bytesReadFromStream) {
                inB += 0x100000000L;
            }
        }
        return inB;
    }

    @Override
    public long getCompressedCount() {
        int method = this.current.entry.getMethod();
        if (method == 0) {
            return this.current.bytesRead;
        }
        if (method == 8) {
            return this.getBytesInflated();
        }
        if (method == ZipMethod.UNSHRINKING.getCode() || method == ZipMethod.IMPLODING.getCode() || method == ZipMethod.ENHANCED_DEFLATED.getCode() || method == ZipMethod.BZIP2.getCode()) {
            return ((InputStreamStatistics)((Object)this.current.checkInputStream())).getCompressedCount();
        }
        return -1L;
    }

    @Override
    public ArchiveEntry getNextEntry() throws IOException {
        return this.getNextZipEntry();
    }

    public ZipArchiveEntry getNextZipEntry() throws IOException {
        this.uncompressedCount = 0L;
        boolean firstEntry = true;
        if (this.closed || this.hitCentralDirectory) {
            return null;
        }
        if (this.current != null) {
            this.closeEntry();
            firstEntry = false;
        }
        long currentHeaderOffset = this.getBytesRead();
        try {
            if (firstEntry) {
                this.readFirstLocalFileHeader();
            } else {
                this.readFully(this.lfhBuf);
            }
        } catch (EOFException e2) {
            return null;
        }
        ZipLong sig = new ZipLong(this.lfhBuf);
        if (!sig.equals(ZipLong.LFH_SIG)) {
            if (sig.equals(ZipLong.CFH_SIG) || sig.equals(ZipLong.AED_SIG) || this.isApkSigningBlock(this.lfhBuf)) {
                this.hitCentralDirectory = true;
                this.skipRemainderOfArchive();
                return null;
            }
            throw new ZipException(String.format("Unexpected record signature: 0x%x", sig.getValue()));
        }
        int off = 4;
        this.current = new CurrentEntry();
        int versionMadeBy = ZipShort.getValue(this.lfhBuf, off);
        this.current.entry.setPlatform(versionMadeBy >> 8 & 0xF);
        GeneralPurposeBit gpFlag = GeneralPurposeBit.parse(this.lfhBuf, off += 2);
        boolean hasUTF8Flag = gpFlag.usesUTF8ForNames();
        ZipEncoding entryEncoding = hasUTF8Flag ? ZipEncodingHelper.UTF8_ZIP_ENCODING : this.zipEncoding;
        this.current.hasDataDescriptor = gpFlag.usesDataDescriptor();
        this.current.entry.setGeneralPurposeBit(gpFlag);
        this.current.entry.setMethod(ZipShort.getValue(this.lfhBuf, off += 2));
        long time = ZipUtil.dosToJavaTime(ZipLong.getValue(this.lfhBuf, off += 2));
        this.current.entry.setTime(time);
        off += 4;
        ZipLong size = null;
        ZipLong cSize = null;
        if (!this.current.hasDataDescriptor) {
            this.current.entry.setCrc(ZipLong.getValue(this.lfhBuf, off));
            cSize = new ZipLong(this.lfhBuf, off += 4);
            size = new ZipLong(this.lfhBuf, off += 4);
            off += 4;
        } else {
            off += 12;
        }
        int fileNameLen = ZipShort.getValue(this.lfhBuf, off);
        int extraLen = ZipShort.getValue(this.lfhBuf, off += 2);
        off += 2;
        byte[] fileName = this.readRange(fileNameLen);
        this.current.entry.setName(entryEncoding.decode(fileName), fileName);
        if (hasUTF8Flag) {
            this.current.entry.setNameSource(ZipArchiveEntry.NameSource.NAME_WITH_EFS_FLAG);
        }
        byte[] extraData = this.readRange(extraLen);
        try {
            this.current.entry.setExtra(extraData);
        } catch (RuntimeException ex) {
            ZipException z2 = new ZipException("Invalid extra data in entry " + this.current.entry.getName());
            z2.initCause(ex);
            throw z2;
        }
        if (!hasUTF8Flag && this.useUnicodeExtraFields) {
            ZipUtil.setNameAndCommentFromExtraFields(this.current.entry, fileName, null);
        }
        this.processZip64Extra(size, cSize);
        this.current.entry.setLocalHeaderOffset(currentHeaderOffset);
        this.current.entry.setDataOffset(this.getBytesRead());
        this.current.entry.setStreamContiguous(true);
        ZipMethod m2 = ZipMethod.getMethodByCode(this.current.entry.getMethod());
        if (this.current.entry.getCompressedSize() != -1L) {
            if (ZipUtil.canHandleEntryData(this.current.entry) && m2 != ZipMethod.STORED && m2 != ZipMethod.DEFLATED) {
                BoundedInputStream bis = new BoundedInputStream(this.inputStream, this.current.entry.getCompressedSize());
                switch (m2) {
                    case UNSHRINKING: {
                        this.current.inputStream = new UnshrinkingInputStream(bis);
                        break;
                    }
                    case IMPLODING: {
                        try {
                            this.current.inputStream = new ExplodingInputStream(this.current.entry.getGeneralPurposeBit().getSlidingDictionarySize(), this.current.entry.getGeneralPurposeBit().getNumberOfShannonFanoTrees(), bis);
                            break;
                        } catch (IllegalArgumentException ex) {
                            throw new IOException("bad IMPLODE data", ex);
                        }
                    }
                    case BZIP2: {
                        this.current.inputStream = new BZip2CompressorInputStream(bis);
                        break;
                    }
                    case ENHANCED_DEFLATED: {
                        this.current.inputStream = new Deflate64CompressorInputStream(bis);
                        break;
                    }
                }
            }
        } else if (m2 == ZipMethod.ENHANCED_DEFLATED) {
            this.current.inputStream = new Deflate64CompressorInputStream(this.inputStream);
        }
        ++this.entriesRead;
        return this.current.entry;
    }

    @Override
    public long getUncompressedCount() {
        return this.uncompressedCount;
    }

    private boolean isApkSigningBlock(byte[] suspectLocalFileHeader) throws IOException {
        BigInteger len = ZipEightByteInteger.getValue(suspectLocalFileHeader);
        BigInteger toSkip = len.add(BigInteger.valueOf((long)(8 - suspectLocalFileHeader.length) - (long)APK_SIGNING_BLOCK_MAGIC.length));
        byte[] magic = new byte[APK_SIGNING_BLOCK_MAGIC.length];
        try {
            if (toSkip.signum() < 0) {
                int off = suspectLocalFileHeader.length + toSkip.intValue();
                if (off < 8) {
                    return false;
                }
                int bytesInBuffer = Math.abs(toSkip.intValue());
                System.arraycopy(suspectLocalFileHeader, off, magic, 0, Math.min(bytesInBuffer, magic.length));
                if (bytesInBuffer < magic.length) {
                    this.readFully(magic, bytesInBuffer);
                }
            } else {
                while (toSkip.compareTo(LONG_MAX) > 0) {
                    this.realSkip(Long.MAX_VALUE);
                    toSkip = toSkip.add(LONG_MAX.negate());
                }
                this.realSkip(toSkip.longValue());
                this.readFully(magic);
            }
        } catch (EOFException ex) {
            return false;
        }
        return Arrays.equals(magic, APK_SIGNING_BLOCK_MAGIC);
    }

    private boolean isFirstByteOfEocdSig(int b2) {
        return b2 == ZipArchiveOutputStream.EOCD_SIG[0];
    }

    private void processZip64Extra(ZipLong size, ZipLong cSize) throws ZipException {
        ZipExtraField extra = this.current.entry.getExtraField(Zip64ExtendedInformationExtraField.HEADER_ID);
        if (extra != null && !(extra instanceof Zip64ExtendedInformationExtraField)) {
            throw new ZipException("archive contains unparseable zip64 extra field");
        }
        Zip64ExtendedInformationExtraField z64 = (Zip64ExtendedInformationExtraField)extra;
        this.current.usesZip64 = z64 != null;
        if (!this.current.hasDataDescriptor) {
            if (z64 != null && (ZipLong.ZIP64_MAGIC.equals(cSize) || ZipLong.ZIP64_MAGIC.equals(size))) {
                if (z64.getCompressedSize() == null || z64.getSize() == null) {
                    throw new ZipException("archive contains corrupted zip64 extra field");
                }
                long s2 = z64.getCompressedSize().getLongValue();
                if (s2 < 0L) {
                    throw new ZipException("broken archive, entry with negative compressed size");
                }
                this.current.entry.setCompressedSize(s2);
                s2 = z64.getSize().getLongValue();
                if (s2 < 0L) {
                    throw new ZipException("broken archive, entry with negative size");
                }
                this.current.entry.setSize(s2);
            } else if (cSize != null && size != null) {
                if (cSize.getValue() < 0L) {
                    throw new ZipException("broken archive, entry with negative compressed size");
                }
                this.current.entry.setCompressedSize(cSize.getValue());
                if (size.getValue() < 0L) {
                    throw new ZipException("broken archive, entry with negative size");
                }
                this.current.entry.setSize(size.getValue());
            }
        }
    }

    private void pushback(byte[] buf, int offset, int length) throws IOException {
        ((PushbackInputStream)this.inputStream).unread(buf, offset, length);
        this.pushedBackBytes(length);
    }

    @Override
    public int read(byte[] buffer, int offset, int length) throws IOException {
        int read;
        if (length == 0) {
            return 0;
        }
        if (this.closed) {
            throw new IOException("The stream is closed");
        }
        if (this.current == null) {
            return -1;
        }
        if (offset > buffer.length || length < 0 || offset < 0 || buffer.length - offset < length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        ZipUtil.checkRequestedFeatures(this.current.entry);
        if (!this.supportsDataDescriptorFor(this.current.entry)) {
            throw new UnsupportedZipFeatureException(UnsupportedZipFeatureException.Feature.DATA_DESCRIPTOR, this.current.entry);
        }
        if (!this.supportsCompressedSizeFor(this.current.entry)) {
            throw new UnsupportedZipFeatureException(UnsupportedZipFeatureException.Feature.UNKNOWN_COMPRESSED_SIZE, this.current.entry);
        }
        if (this.current.entry.getMethod() == 0) {
            read = this.readStored(buffer, offset, length);
        } else if (this.current.entry.getMethod() == 8) {
            read = this.readDeflated(buffer, offset, length);
        } else if (this.current.entry.getMethod() == ZipMethod.UNSHRINKING.getCode() || this.current.entry.getMethod() == ZipMethod.IMPLODING.getCode() || this.current.entry.getMethod() == ZipMethod.ENHANCED_DEFLATED.getCode() || this.current.entry.getMethod() == ZipMethod.BZIP2.getCode()) {
            read = this.current.inputStream.read(buffer, offset, length);
        } else {
            throw new UnsupportedZipFeatureException(ZipMethod.getMethodByCode(this.current.entry.getMethod()), this.current.entry);
        }
        if (read >= 0) {
            this.current.crc.update(buffer, offset, read);
            this.uncompressedCount += (long)read;
        }
        return read;
    }

    private void readDataDescriptor() throws IOException {
        this.readFully(this.wordBuf);
        ZipLong val = new ZipLong(this.wordBuf);
        if (ZipLong.DD_SIG.equals(val)) {
            this.readFully(this.wordBuf);
            val = new ZipLong(this.wordBuf);
        }
        this.current.entry.setCrc(val.getValue());
        this.readFully(this.twoDwordBuf);
        ZipLong potentialSig = new ZipLong(this.twoDwordBuf, 8);
        if (potentialSig.equals(ZipLong.CFH_SIG) || potentialSig.equals(ZipLong.LFH_SIG)) {
            this.pushback(this.twoDwordBuf, 8, 8);
            long size = ZipLong.getValue(this.twoDwordBuf);
            if (size < 0L) {
                throw new ZipException("broken archive, entry with negative compressed size");
            }
            this.current.entry.setCompressedSize(size);
            size = ZipLong.getValue(this.twoDwordBuf, 4);
            if (size < 0L) {
                throw new ZipException("broken archive, entry with negative size");
            }
            this.current.entry.setSize(size);
        } else {
            long size = ZipEightByteInteger.getLongValue(this.twoDwordBuf);
            if (size < 0L) {
                throw new ZipException("broken archive, entry with negative compressed size");
            }
            this.current.entry.setCompressedSize(size);
            size = ZipEightByteInteger.getLongValue(this.twoDwordBuf, 8);
            if (size < 0L) {
                throw new ZipException("broken archive, entry with negative size");
            }
            this.current.entry.setSize(size);
        }
    }

    private int readDeflated(byte[] buffer, int offset, int length) throws IOException {
        int read = this.readFromInflater(buffer, offset, length);
        if (read <= 0) {
            if (this.inf.finished()) {
                return -1;
            }
            if (this.inf.needsDictionary()) {
                throw new ZipException("This archive needs a preset dictionary which is not supported by Commons Compress.");
            }
            if (read == -1) {
                throw new IOException("Truncated ZIP file");
            }
        }
        return read;
    }

    private void readFirstLocalFileHeader() throws IOException {
        this.readFully(this.lfhBuf);
        ZipLong sig = new ZipLong(this.lfhBuf);
        if (!this.skipSplitSig && sig.equals(ZipLong.DD_SIG)) {
            throw new UnsupportedZipFeatureException(UnsupportedZipFeatureException.Feature.SPLITTING);
        }
        if (sig.equals(ZipLong.SINGLE_SEGMENT_SPLIT_MARKER) || sig.equals(ZipLong.DD_SIG)) {
            byte[] missedLfhBytes = new byte[4];
            this.readFully(missedLfhBytes);
            System.arraycopy(this.lfhBuf, 4, this.lfhBuf, 0, 26);
            System.arraycopy(missedLfhBytes, 0, this.lfhBuf, 26, 4);
        }
    }

    private int readFromInflater(byte[] buffer, int offset, int length) throws IOException {
        int read = 0;
        do {
            if (this.inf.needsInput()) {
                int l2 = this.fill();
                if (l2 > 0) {
                    this.current.bytesReadFromStream += this.buf.limit();
                } else {
                    if (l2 != -1) break;
                    return -1;
                }
            }
            try {
                read = this.inf.inflate(buffer, offset, length);
            } catch (DataFormatException e2) {
                throw (IOException)new ZipException(e2.getMessage()).initCause(e2);
            }
        } while (read == 0 && this.inf.needsInput());
        return read;
    }

    private void readFully(byte[] b2) throws IOException {
        this.readFully(b2, 0);
    }

    private void readFully(byte[] b2, int off) throws IOException {
        int len = b2.length - off;
        int count = IOUtils.readFully(this.inputStream, b2, off, len);
        this.count(count);
        if (count < len) {
            throw new EOFException();
        }
    }

    private int readOneByte() throws IOException {
        int b2 = this.inputStream.read();
        if (b2 != -1) {
            this.count(1);
        }
        return b2;
    }

    private byte[] readRange(int len) throws IOException {
        byte[] ret = IOUtils.readRange(this.inputStream, len);
        this.count(ret.length);
        if (ret.length < len) {
            throw new EOFException();
        }
        return ret;
    }

    private int readStored(byte[] buffer, int offset, int length) throws IOException {
        if (this.current.hasDataDescriptor) {
            if (this.lastStoredEntry == null) {
                this.readStoredEntry();
            }
            return this.lastStoredEntry.read(buffer, offset, length);
        }
        long csize = this.current.entry.getSize();
        if (this.current.bytesRead >= csize) {
            return -1;
        }
        if (this.buf.position() >= this.buf.limit()) {
            this.buf.position(0);
            int l2 = this.inputStream.read(this.buf.array());
            if (l2 == -1) {
                this.buf.limit(0);
                throw new IOException("Truncated ZIP file");
            }
            this.buf.limit(l2);
            this.count(l2);
            this.current.bytesReadFromStream += l2;
        }
        int toRead = Math.min(this.buf.remaining(), length);
        if (csize - this.current.bytesRead < (long)toRead) {
            toRead = (int)(csize - this.current.bytesRead);
        }
        this.buf.get(buffer, offset, toRead);
        this.current.bytesRead += toRead;
        return toRead;
    }

    private void readStoredEntry() throws IOException {
        int ddLen;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        int off = 0;
        boolean done = false;
        int n2 = ddLen = this.current.usesZip64 ? 20 : 12;
        while (!done) {
            int r2 = this.inputStream.read(this.buf.array(), off, 512 - off);
            if (r2 <= 0) {
                throw new IOException("Truncated ZIP file");
            }
            if (r2 + off < 4) {
                off += r2;
                continue;
            }
            done = this.bufferContainsSignature(bos, off, r2, ddLen);
            if (done) continue;
            off = this.cacheBytesRead(bos, off, r2, ddLen);
        }
        if (this.current.entry.getCompressedSize() != this.current.entry.getSize()) {
            throw new ZipException("compressed and uncompressed size don't match while reading a stored entry using data descriptor. Either the archive is broken or it can not be read using ZipArchiveInputStream and you must use ZipFile. A common cause for this is a ZIP archive containing a ZIP archive. See http://commons.apache.org/proper/commons-compress/zip.html#ZipArchiveInputStream_vs_ZipFile");
        }
        byte[] b2 = bos.toByteArray();
        if ((long)b2.length != this.current.entry.getSize()) {
            throw new ZipException("actual and claimed size don't match while reading a stored entry using data descriptor. Either the archive is broken or it can not be read using ZipArchiveInputStream and you must use ZipFile. A common cause for this is a ZIP archive containing a ZIP archive. See http://commons.apache.org/proper/commons-compress/zip.html#ZipArchiveInputStream_vs_ZipFile");
        }
        this.lastStoredEntry = new ByteArrayInputStream(b2);
    }

    private void realSkip(long value) throws IOException {
        if (value >= 0L) {
            int x2;
            for (long skipped = 0L; skipped < value; skipped += (long)x2) {
                long rem = value - skipped;
                x2 = this.inputStream.read(this.skipBuf, 0, (int)((long)this.skipBuf.length > rem ? rem : (long)this.skipBuf.length));
                if (x2 == -1) {
                    return;
                }
                this.count(x2);
            }
            return;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public long skip(long value) throws IOException {
        if (value >= 0L) {
            long skipped;
            int x2;
            for (skipped = 0L; skipped < value; skipped += (long)x2) {
                long rem = value - skipped;
                x2 = this.read(this.skipBuf, 0, (int)((long)this.skipBuf.length > rem ? rem : (long)this.skipBuf.length));
                if (x2 != -1) continue;
                return skipped;
            }
            return skipped;
        }
        throw new IllegalArgumentException();
    }

    private void skipRemainderOfArchive() throws IOException {
        if (this.entriesRead > 0) {
            this.realSkip((long)this.entriesRead * 46L - 30L);
            boolean foundEocd = this.findEocdRecord();
            if (foundEocd) {
                this.realSkip(16L);
                this.readFully(this.shortBuf);
                int commentLen = ZipShort.getValue(this.shortBuf);
                if (commentLen >= 0) {
                    this.realSkip(commentLen);
                    return;
                }
            }
        }
        throw new IOException("Truncated ZIP file");
    }

    private boolean supportsCompressedSizeFor(ZipArchiveEntry entry) {
        return entry.getCompressedSize() != -1L || entry.getMethod() == 8 || entry.getMethod() == ZipMethod.ENHANCED_DEFLATED.getCode() || entry.getGeneralPurposeBit().usesDataDescriptor() && this.allowStoredEntriesWithDataDescriptor && entry.getMethod() == 0;
    }

    private boolean supportsDataDescriptorFor(ZipArchiveEntry entry) {
        return !entry.getGeneralPurposeBit().usesDataDescriptor() || this.allowStoredEntriesWithDataDescriptor && entry.getMethod() == 0 || entry.getMethod() == 8 || entry.getMethod() == ZipMethod.ENHANCED_DEFLATED.getCode();
    }

    private static final class CurrentEntry {
        private final ZipArchiveEntry entry = new ZipArchiveEntry();
        private boolean hasDataDescriptor;
        private boolean usesZip64;
        private long bytesRead;
        private long bytesReadFromStream;
        private final CRC32 crc = new CRC32();
        private InputStream inputStream;

        private CurrentEntry() {
        }

        private <T extends InputStream> T checkInputStream() {
            return (T)Objects.requireNonNull(this.inputStream, "inputStream");
        }
    }

    private class BoundedInputStream
    extends FilterInputStream {
        private final long max;
        private long pos;

        public BoundedInputStream(InputStream in, long size) {
            super(in);
            this.max = size;
        }

        @Override
        public int available() throws IOException {
            if (this.max >= 0L && this.pos >= this.max) {
                return 0;
            }
            return this.in.available();
        }

        @Override
        public int read() throws IOException {
            if (this.max >= 0L && this.pos >= this.max) {
                return -1;
            }
            int result = this.in.read();
            ++this.pos;
            ZipArchiveInputStream.this.count(1);
            ZipArchiveInputStream.this.current.bytesReadFromStream++;
            return result;
        }

        @Override
        public int read(byte[] b2) throws IOException {
            return this.read(b2, 0, b2.length);
        }

        @Override
        public int read(byte[] b2, int off, int len) throws IOException {
            if (len == 0) {
                return 0;
            }
            if (this.max >= 0L && this.pos >= this.max) {
                return -1;
            }
            long maxRead = this.max >= 0L ? Math.min((long)len, this.max - this.pos) : (long)len;
            int bytesRead = this.in.read(b2, off, (int)maxRead);
            if (bytesRead == -1) {
                return -1;
            }
            this.pos += (long)bytesRead;
            ZipArchiveInputStream.this.count(bytesRead);
            ZipArchiveInputStream.this.current.bytesReadFromStream += bytesRead;
            return bytesRead;
        }

        @Override
        public long skip(long n2) throws IOException {
            long toSkip = this.max >= 0L ? Math.min(n2, this.max - this.pos) : n2;
            long skippedBytes = IOUtils.skip(this.in, toSkip);
            this.pos += skippedBytes;
            return skippedBytes;
        }
    }
}

