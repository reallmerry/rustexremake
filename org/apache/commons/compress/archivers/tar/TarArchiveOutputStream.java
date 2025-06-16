/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.tar;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.utils.CountingOutputStream;
import org.apache.commons.compress.utils.FixedLengthBlockOutputStream;
import org.apache.commons.compress.utils.TimeUtils;

public class TarArchiveOutputStream
extends ArchiveOutputStream {
    public static final int LONGFILE_ERROR = 0;
    public static final int LONGFILE_TRUNCATE = 1;
    public static final int LONGFILE_GNU = 2;
    public static final int LONGFILE_POSIX = 3;
    public static final int BIGNUMBER_ERROR = 0;
    public static final int BIGNUMBER_STAR = 1;
    public static final int BIGNUMBER_POSIX = 2;
    private static final int RECORD_SIZE = 512;
    private static final ZipEncoding ASCII = ZipEncodingHelper.getZipEncoding("ASCII");
    private static final int BLOCK_SIZE_UNSPECIFIED = -511;
    private long currSize;
    private String currName;
    private long currBytes;
    private final byte[] recordBuf;
    private int longFileMode = 0;
    private int bigNumberMode = 0;
    private long recordsWritten;
    private final int recordsPerBlock;
    private boolean closed;
    private boolean haveUnclosedEntry;
    private boolean finished;
    private final FixedLengthBlockOutputStream out;
    private final CountingOutputStream countingOut;
    private final ZipEncoding zipEncoding;
    final String encoding;
    private boolean addPaxHeadersForNonAsciiNames;

    public TarArchiveOutputStream(OutputStream os) {
        this(os, -511);
    }

    public TarArchiveOutputStream(OutputStream os, int blockSize) {
        this(os, blockSize, null);
    }

    @Deprecated
    public TarArchiveOutputStream(OutputStream os, int blockSize, int recordSize) {
        this(os, blockSize, recordSize, null);
    }

    @Deprecated
    public TarArchiveOutputStream(OutputStream os, int blockSize, int recordSize, String encoding) {
        this(os, blockSize, encoding);
        if (recordSize != 512) {
            throw new IllegalArgumentException("Tar record size must always be 512 bytes. Attempt to set size of " + recordSize);
        }
    }

    public TarArchiveOutputStream(OutputStream os, int blockSize, String encoding) {
        int realBlockSize = -511 == blockSize ? 512 : blockSize;
        if (realBlockSize <= 0 || realBlockSize % 512 != 0) {
            throw new IllegalArgumentException("Block size must be a multiple of 512 bytes. Attempt to use set size of " + blockSize);
        }
        this.countingOut = new CountingOutputStream(os);
        this.out = new FixedLengthBlockOutputStream(this.countingOut, 512);
        this.encoding = encoding;
        this.zipEncoding = ZipEncodingHelper.getZipEncoding(encoding);
        this.recordBuf = new byte[512];
        this.recordsPerBlock = realBlockSize / 512;
    }

    public TarArchiveOutputStream(OutputStream os, String encoding) {
        this(os, -511, encoding);
    }

    private void addFileTimePaxHeader(Map<String, String> paxHeaders, String header, FileTime value) {
        if (value != null) {
            Instant instant = value.toInstant();
            long seconds = instant.getEpochSecond();
            int nanos = instant.getNano();
            if (nanos == 0) {
                paxHeaders.put(header, String.valueOf(seconds));
            } else {
                this.addInstantPaxHeader(paxHeaders, header, seconds, nanos);
            }
        }
    }

    private void addFileTimePaxHeaderForBigNumber(Map<String, String> paxHeaders, String header, FileTime value, long maxValue) {
        if (value != null) {
            Instant instant = value.toInstant();
            long seconds = instant.getEpochSecond();
            int nanos = instant.getNano();
            if (nanos == 0) {
                this.addPaxHeaderForBigNumber(paxHeaders, header, seconds, maxValue);
            } else {
                this.addInstantPaxHeader(paxHeaders, header, seconds, nanos);
            }
        }
    }

    private void addInstantPaxHeader(Map<String, String> paxHeaders, String header, long seconds, int nanos) {
        BigDecimal bdSeconds = BigDecimal.valueOf(seconds);
        BigDecimal bdNanos = BigDecimal.valueOf(nanos).movePointLeft(9).setScale(7, RoundingMode.DOWN);
        BigDecimal timestamp = bdSeconds.add(bdNanos);
        paxHeaders.put(header, timestamp.toPlainString());
    }

    private void addPaxHeaderForBigNumber(Map<String, String> paxHeaders, String header, long value, long maxValue) {
        if (value < 0L || value > maxValue) {
            paxHeaders.put(header, String.valueOf(value));
        }
    }

    private void addPaxHeadersForBigNumbers(Map<String, String> paxHeaders, TarArchiveEntry entry) {
        this.addPaxHeaderForBigNumber(paxHeaders, "size", entry.getSize(), 0x1FFFFFFFFL);
        this.addPaxHeaderForBigNumber(paxHeaders, "gid", entry.getLongGroupId(), 0x1FFFFFL);
        this.addFileTimePaxHeaderForBigNumber(paxHeaders, "mtime", entry.getLastModifiedTime(), 0x1FFFFFFFFL);
        this.addFileTimePaxHeader(paxHeaders, "atime", entry.getLastAccessTime());
        if (entry.getStatusChangeTime() != null) {
            this.addFileTimePaxHeader(paxHeaders, "ctime", entry.getStatusChangeTime());
        } else {
            this.addFileTimePaxHeader(paxHeaders, "ctime", entry.getCreationTime());
        }
        this.addPaxHeaderForBigNumber(paxHeaders, "uid", entry.getLongUserId(), 0x1FFFFFL);
        this.addFileTimePaxHeader(paxHeaders, "LIBARCHIVE.creationtime", entry.getCreationTime());
        this.addPaxHeaderForBigNumber(paxHeaders, "SCHILY.devmajor", entry.getDevMajor(), 0x1FFFFFL);
        this.addPaxHeaderForBigNumber(paxHeaders, "SCHILY.devminor", entry.getDevMinor(), 0x1FFFFFL);
        this.failForBigNumber("mode", entry.getMode(), 0x1FFFFFL);
    }

    @Override
    public void close() throws IOException {
        try {
            if (!this.finished) {
                this.finish();
            }
        } finally {
            if (!this.closed) {
                this.out.close();
                this.closed = true;
            }
        }
    }

    @Override
    public void closeArchiveEntry() throws IOException {
        if (this.finished) {
            throw new IOException("Stream has already been finished");
        }
        if (!this.haveUnclosedEntry) {
            throw new IOException("No current entry to close");
        }
        this.out.flushBlock();
        if (this.currBytes < this.currSize) {
            throw new IOException("Entry '" + this.currName + "' closed at '" + this.currBytes + "' before the '" + this.currSize + "' bytes specified in the header were written");
        }
        this.recordsWritten += this.currSize / 512L;
        if (0L != this.currSize % 512L) {
            ++this.recordsWritten;
        }
        this.haveUnclosedEntry = false;
    }

    @Override
    public ArchiveEntry createArchiveEntry(File inputFile, String entryName) throws IOException {
        if (this.finished) {
            throw new IOException("Stream has already been finished");
        }
        return new TarArchiveEntry(inputFile, entryName);
    }

    @Override
    public ArchiveEntry createArchiveEntry(Path inputPath, String entryName, LinkOption ... options) throws IOException {
        if (this.finished) {
            throw new IOException("Stream has already been finished");
        }
        return new TarArchiveEntry(inputPath, entryName, options);
    }

    private byte[] encodeExtendedPaxHeadersContents(Map<String, String> headers) {
        StringWriter w2 = new StringWriter();
        headers.forEach((k2, v2) -> {
            int len = k2.length() + v2.length() + 3 + 2;
            String line = len + " " + k2 + "=" + v2 + "\n";
            int actualLength = line.getBytes(StandardCharsets.UTF_8).length;
            while (len != actualLength) {
                len = actualLength;
                line = len + " " + k2 + "=" + v2 + "\n";
                actualLength = line.getBytes(StandardCharsets.UTF_8).length;
            }
            w2.write(line);
        });
        return w2.toString().getBytes(StandardCharsets.UTF_8);
    }

    private void failForBigNumber(String field, long value, long maxValue) {
        this.failForBigNumber(field, value, maxValue, "");
    }

    private void failForBigNumber(String field, long value, long maxValue, String additionalMsg) {
        if (value < 0L || value > maxValue) {
            throw new IllegalArgumentException(field + " '" + value + "' is too big ( > " + maxValue + " )." + additionalMsg);
        }
    }

    private void failForBigNumbers(TarArchiveEntry entry) {
        this.failForBigNumber("entry size", entry.getSize(), 0x1FFFFFFFFL);
        this.failForBigNumberWithPosixMessage("group id", entry.getLongGroupId(), 0x1FFFFFL);
        this.failForBigNumber("last modification time", TimeUtils.toUnixTime(entry.getLastModifiedTime()), 0x1FFFFFFFFL);
        this.failForBigNumber("user id", entry.getLongUserId(), 0x1FFFFFL);
        this.failForBigNumber("mode", entry.getMode(), 0x1FFFFFL);
        this.failForBigNumber("major device number", entry.getDevMajor(), 0x1FFFFFL);
        this.failForBigNumber("minor device number", entry.getDevMinor(), 0x1FFFFFL);
    }

    private void failForBigNumberWithPosixMessage(String field, long value, long maxValue) {
        this.failForBigNumber(field, value, maxValue, " Use STAR or POSIX extensions to overcome this limit");
    }

    @Override
    public void finish() throws IOException {
        if (this.finished) {
            throw new IOException("This archive has already been finished");
        }
        if (this.haveUnclosedEntry) {
            throw new IOException("This archive contains unclosed entries.");
        }
        this.writeEOFRecord();
        this.writeEOFRecord();
        this.padAsNeeded();
        this.out.flush();
        this.finished = true;
    }

    @Override
    public void flush() throws IOException {
        this.out.flush();
    }

    @Override
    public long getBytesWritten() {
        return this.countingOut.getBytesWritten();
    }

    @Override
    @Deprecated
    public int getCount() {
        return (int)this.getBytesWritten();
    }

    @Deprecated
    public int getRecordSize() {
        return 512;
    }

    private boolean handleLongName(TarArchiveEntry entry, String name, Map<String, String> paxHeaders, String paxHeaderName, byte linkType, String fieldName) throws IOException {
        ByteBuffer encodedName = this.zipEncoding.encode(name);
        int len = encodedName.limit() - encodedName.position();
        if (len >= 100) {
            if (this.longFileMode == 3) {
                paxHeaders.put(paxHeaderName, name);
                return true;
            }
            if (this.longFileMode == 2) {
                TarArchiveEntry longLinkEntry = new TarArchiveEntry("././@LongLink", linkType);
                longLinkEntry.setSize((long)len + 1L);
                this.transferModTime(entry, longLinkEntry);
                this.putArchiveEntry(longLinkEntry);
                this.write(encodedName.array(), encodedName.arrayOffset(), len);
                this.write(0);
                this.closeArchiveEntry();
            } else if (this.longFileMode != 1) {
                throw new IllegalArgumentException(fieldName + " '" + name + "' is too long ( > " + 100 + " bytes)");
            }
        }
        return false;
    }

    private void padAsNeeded() throws IOException {
        int start = Math.toIntExact(this.recordsWritten % (long)this.recordsPerBlock);
        if (start != 0) {
            for (int i2 = start; i2 < this.recordsPerBlock; ++i2) {
                this.writeEOFRecord();
            }
        }
    }

    @Override
    public void putArchiveEntry(ArchiveEntry archiveEntry) throws IOException {
        if (this.finished) {
            throw new IOException("Stream has already been finished");
        }
        TarArchiveEntry entry = (TarArchiveEntry)archiveEntry;
        if (entry.isGlobalPaxHeader()) {
            byte[] data = this.encodeExtendedPaxHeadersContents(entry.getExtraPaxHeaders());
            entry.setSize(data.length);
            entry.writeEntryHeader(this.recordBuf, this.zipEncoding, this.bigNumberMode == 1);
            this.writeRecord(this.recordBuf);
            this.currSize = entry.getSize();
            this.currBytes = 0L;
            this.haveUnclosedEntry = true;
            this.write(data);
            this.closeArchiveEntry();
        } else {
            boolean paxHeaderContainsLinkPath;
            HashMap<String, String> paxHeaders = new HashMap<String, String>();
            String entryName = entry.getName();
            boolean paxHeaderContainsPath = this.handleLongName(entry, entryName, paxHeaders, "path", (byte)76, "file name");
            String linkName = entry.getLinkName();
            boolean bl = paxHeaderContainsLinkPath = linkName != null && !linkName.isEmpty() && this.handleLongName(entry, linkName, paxHeaders, "linkpath", (byte)75, "link name");
            if (this.bigNumberMode == 2) {
                this.addPaxHeadersForBigNumbers(paxHeaders, entry);
            } else if (this.bigNumberMode != 1) {
                this.failForBigNumbers(entry);
            }
            if (this.addPaxHeadersForNonAsciiNames && !paxHeaderContainsPath && !ASCII.canEncode(entryName)) {
                paxHeaders.put("path", entryName);
            }
            if (this.addPaxHeadersForNonAsciiNames && !paxHeaderContainsLinkPath && (entry.isLink() || entry.isSymbolicLink()) && !ASCII.canEncode(linkName)) {
                paxHeaders.put("linkpath", linkName);
            }
            paxHeaders.putAll(entry.getExtraPaxHeaders());
            if (!paxHeaders.isEmpty()) {
                this.writePaxHeaders(entry, entryName, paxHeaders);
            }
            entry.writeEntryHeader(this.recordBuf, this.zipEncoding, this.bigNumberMode == 1);
            this.writeRecord(this.recordBuf);
            this.currBytes = 0L;
            this.currSize = entry.isDirectory() ? 0L : entry.getSize();
            this.currName = entryName;
            this.haveUnclosedEntry = true;
        }
    }

    public void setAddPaxHeadersForNonAsciiNames(boolean b2) {
        this.addPaxHeadersForNonAsciiNames = b2;
    }

    public void setBigNumberMode(int bigNumberMode) {
        this.bigNumberMode = bigNumberMode;
    }

    public void setLongFileMode(int longFileMode) {
        this.longFileMode = longFileMode;
    }

    private boolean shouldBeReplaced(char c2) {
        return c2 == '\u0000' || c2 == '/' || c2 == '\\';
    }

    private String stripTo7Bits(String name) {
        int length = name.length();
        StringBuilder result = new StringBuilder(length);
        for (int i2 = 0; i2 < length; ++i2) {
            char stripped = (char)(name.charAt(i2) & 0x7F);
            if (this.shouldBeReplaced(stripped)) {
                result.append("_");
                continue;
            }
            result.append(stripped);
        }
        return result.toString();
    }

    private void transferModTime(TarArchiveEntry from, TarArchiveEntry to) {
        long fromModTimeSeconds = TimeUtils.toUnixTime(from.getLastModifiedTime());
        if (fromModTimeSeconds < 0L || fromModTimeSeconds > 0x1FFFFFFFFL) {
            fromModTimeSeconds = 0L;
        }
        to.setLastModifiedTime(TimeUtils.unixTimeToFileTime(fromModTimeSeconds));
    }

    @Override
    public void write(byte[] wBuf, int wOffset, int numToWrite) throws IOException {
        if (!this.haveUnclosedEntry) {
            throw new IllegalStateException("No current tar entry");
        }
        if (this.currBytes + (long)numToWrite > this.currSize) {
            throw new IOException("Request to write '" + numToWrite + "' bytes exceeds size in header of '" + this.currSize + "' bytes for entry '" + this.currName + "'");
        }
        this.out.write(wBuf, wOffset, numToWrite);
        this.currBytes += (long)numToWrite;
    }

    private void writeEOFRecord() throws IOException {
        Arrays.fill(this.recordBuf, (byte)0);
        this.writeRecord(this.recordBuf);
    }

    void writePaxHeaders(TarArchiveEntry entry, String entryName, Map<String, String> headers) throws IOException {
        String name = "./PaxHeaders.X/" + this.stripTo7Bits(entryName);
        if (name.length() >= 100) {
            name = name.substring(0, 99);
        }
        TarArchiveEntry pex = new TarArchiveEntry(name, 120);
        this.transferModTime(entry, pex);
        byte[] data = this.encodeExtendedPaxHeadersContents(headers);
        pex.setSize(data.length);
        this.putArchiveEntry(pex);
        this.write(data);
        this.closeArchiveEntry();
    }

    private void writeRecord(byte[] record) throws IOException {
        if (record.length != 512) {
            throw new IOException("Record to write has length '" + record.length + "' which is not the record size of '" + 512 + "'");
        }
        this.out.write(record);
        ++this.recordsWritten;
    }
}

