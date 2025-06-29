/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.arj;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.zip.CRC32;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.arj.ArjArchiveEntry;
import org.apache.commons.compress.archivers.arj.LocalFileHeader;
import org.apache.commons.compress.archivers.arj.MainHeader;
import org.apache.commons.compress.utils.BoundedInputStream;
import org.apache.commons.compress.utils.CRC32VerifyingInputStream;
import org.apache.commons.compress.utils.Charsets;
import org.apache.commons.compress.utils.IOUtils;

public class ArjArchiveInputStream
extends ArchiveInputStream {
    private static final int ARJ_MAGIC_1 = 96;
    private static final int ARJ_MAGIC_2 = 234;
    private final DataInputStream in;
    private final String charsetName;
    private final MainHeader mainHeader;
    private LocalFileHeader currentLocalFileHeader;
    private InputStream currentInputStream;

    public static boolean matches(byte[] signature, int length) {
        return length >= 2 && (0xFF & signature[0]) == 96 && (0xFF & signature[1]) == 234;
    }

    public ArjArchiveInputStream(InputStream inputStream) throws ArchiveException {
        this(inputStream, "CP437");
    }

    public ArjArchiveInputStream(InputStream inputStream, String charsetName) throws ArchiveException {
        this.in = new DataInputStream(inputStream);
        this.charsetName = charsetName;
        try {
            this.mainHeader = this.readMainHeader();
            if ((this.mainHeader.arjFlags & 1) != 0) {
                throw new ArchiveException("Encrypted ARJ files are unsupported");
            }
            if ((this.mainHeader.arjFlags & 4) != 0) {
                throw new ArchiveException("Multi-volume ARJ files are unsupported");
            }
        } catch (IOException ioException) {
            throw new ArchiveException(ioException.getMessage(), ioException);
        }
    }

    @Override
    public boolean canReadEntryData(ArchiveEntry ae) {
        return ae instanceof ArjArchiveEntry && ((ArjArchiveEntry)ae).getMethod() == 0;
    }

    @Override
    public void close() throws IOException {
        this.in.close();
    }

    public String getArchiveComment() {
        return this.mainHeader.comment;
    }

    public String getArchiveName() {
        return this.mainHeader.name;
    }

    @Override
    public ArjArchiveEntry getNextEntry() throws IOException {
        if (this.currentInputStream != null) {
            IOUtils.skip(this.currentInputStream, Long.MAX_VALUE);
            this.currentInputStream.close();
            this.currentLocalFileHeader = null;
            this.currentInputStream = null;
        }
        this.currentLocalFileHeader = this.readLocalFileHeader();
        if (this.currentLocalFileHeader != null) {
            this.currentInputStream = new BoundedInputStream(this.in, this.currentLocalFileHeader.compressedSize);
            if (this.currentLocalFileHeader.method == 0) {
                this.currentInputStream = new CRC32VerifyingInputStream(this.currentInputStream, this.currentLocalFileHeader.originalSize, this.currentLocalFileHeader.originalCrc32);
            }
            return new ArjArchiveEntry(this.currentLocalFileHeader);
        }
        this.currentInputStream = null;
        return null;
    }

    @Override
    public int read(byte[] b2, int off, int len) throws IOException {
        if (len == 0) {
            return 0;
        }
        if (this.currentLocalFileHeader == null) {
            throw new IllegalStateException("No current arj entry");
        }
        if (this.currentLocalFileHeader.method != 0) {
            throw new IOException("Unsupported compression method " + this.currentLocalFileHeader.method);
        }
        return this.currentInputStream.read(b2, off, len);
    }

    private int read16(DataInputStream dataIn) throws IOException {
        int value = dataIn.readUnsignedShort();
        this.count(2);
        return Integer.reverseBytes(value) >>> 16;
    }

    private int read32(DataInputStream dataIn) throws IOException {
        int value = dataIn.readInt();
        this.count(4);
        return Integer.reverseBytes(value);
    }

    private int read8(DataInputStream dataIn) throws IOException {
        int value = dataIn.readUnsignedByte();
        this.count(1);
        return value;
    }

    private void readExtraData(int firstHeaderSize, DataInputStream firstHeader, LocalFileHeader localFileHeader) throws IOException {
        if (firstHeaderSize >= 33) {
            localFileHeader.extendedFilePosition = this.read32(firstHeader);
            if (firstHeaderSize >= 45) {
                localFileHeader.dateTimeAccessed = this.read32(firstHeader);
                localFileHeader.dateTimeCreated = this.read32(firstHeader);
                localFileHeader.originalSizeEvenForVolumes = this.read32(firstHeader);
                this.pushedBackBytes(12L);
            }
            this.pushedBackBytes(4L);
        }
    }

    private byte[] readHeader() throws IOException {
        boolean found = false;
        byte[] basicHeaderBytes = null;
        do {
            int first;
            int second = this.read8(this.in);
            do {
                first = second;
                second = this.read8(this.in);
            } while (first != 96 && second != 234);
            int basicHeaderSize = this.read16(this.in);
            if (basicHeaderSize == 0) {
                return null;
            }
            if (basicHeaderSize > 2600) continue;
            basicHeaderBytes = this.readRange(this.in, basicHeaderSize);
            long basicHeaderCrc32 = (long)this.read32(this.in) & 0xFFFFFFFFL;
            CRC32 crc32 = new CRC32();
            crc32.update(basicHeaderBytes);
            if (basicHeaderCrc32 != crc32.getValue()) continue;
            found = true;
        } while (!found);
        return basicHeaderBytes;
    }

    private LocalFileHeader readLocalFileHeader() throws IOException {
        byte[] basicHeaderBytes = this.readHeader();
        if (basicHeaderBytes == null) {
            return null;
        }
        try (DataInputStream basicHeader = new DataInputStream(new ByteArrayInputStream(basicHeaderBytes));){
            LocalFileHeader localFileHeader;
            int firstHeaderSize = basicHeader.readUnsignedByte();
            byte[] firstHeaderBytes = this.readRange(basicHeader, firstHeaderSize - 1);
            this.pushedBackBytes(firstHeaderBytes.length);
            try (DataInputStream firstHeader = new DataInputStream(new ByteArrayInputStream(firstHeaderBytes));){
                int extendedHeaderSize;
                LocalFileHeader localFileHeader2 = new LocalFileHeader();
                localFileHeader2.archiverVersionNumber = firstHeader.readUnsignedByte();
                localFileHeader2.minVersionToExtract = firstHeader.readUnsignedByte();
                localFileHeader2.hostOS = firstHeader.readUnsignedByte();
                localFileHeader2.arjFlags = firstHeader.readUnsignedByte();
                localFileHeader2.method = firstHeader.readUnsignedByte();
                localFileHeader2.fileType = firstHeader.readUnsignedByte();
                localFileHeader2.reserved = firstHeader.readUnsignedByte();
                localFileHeader2.dateTimeModified = this.read32(firstHeader);
                localFileHeader2.compressedSize = 0xFFFFFFFFL & (long)this.read32(firstHeader);
                localFileHeader2.originalSize = 0xFFFFFFFFL & (long)this.read32(firstHeader);
                localFileHeader2.originalCrc32 = 0xFFFFFFFFL & (long)this.read32(firstHeader);
                localFileHeader2.fileSpecPosition = this.read16(firstHeader);
                localFileHeader2.fileAccessMode = this.read16(firstHeader);
                this.pushedBackBytes(20L);
                localFileHeader2.firstChapter = firstHeader.readUnsignedByte();
                localFileHeader2.lastChapter = firstHeader.readUnsignedByte();
                this.readExtraData(firstHeaderSize, firstHeader, localFileHeader2);
                localFileHeader2.name = this.readString(basicHeader);
                localFileHeader2.comment = this.readString(basicHeader);
                ArrayList<byte[]> extendedHeaders = new ArrayList<byte[]>();
                while ((extendedHeaderSize = this.read16(this.in)) > 0) {
                    byte[] extendedHeaderBytes = this.readRange(this.in, extendedHeaderSize);
                    long extendedHeaderCrc32 = 0xFFFFFFFFL & (long)this.read32(this.in);
                    CRC32 crc32 = new CRC32();
                    crc32.update(extendedHeaderBytes);
                    if (extendedHeaderCrc32 != crc32.getValue()) {
                        throw new IOException("Extended header CRC32 verification failure");
                    }
                    extendedHeaders.add(extendedHeaderBytes);
                }
                localFileHeader2.extendedHeaders = (byte[][])extendedHeaders.toArray((T[])new byte[0][]);
                localFileHeader = localFileHeader2;
            }
            return localFileHeader;
        }
    }

    private MainHeader readMainHeader() throws IOException {
        byte[] basicHeaderBytes = this.readHeader();
        if (basicHeaderBytes == null) {
            throw new IOException("Archive ends without any headers");
        }
        DataInputStream basicHeader = new DataInputStream(new ByteArrayInputStream(basicHeaderBytes));
        int firstHeaderSize = basicHeader.readUnsignedByte();
        byte[] firstHeaderBytes = this.readRange(basicHeader, firstHeaderSize - 1);
        this.pushedBackBytes(firstHeaderBytes.length);
        DataInputStream firstHeader = new DataInputStream(new ByteArrayInputStream(firstHeaderBytes));
        MainHeader hdr = new MainHeader();
        hdr.archiverVersionNumber = firstHeader.readUnsignedByte();
        hdr.minVersionToExtract = firstHeader.readUnsignedByte();
        hdr.hostOS = firstHeader.readUnsignedByte();
        hdr.arjFlags = firstHeader.readUnsignedByte();
        hdr.securityVersion = firstHeader.readUnsignedByte();
        hdr.fileType = firstHeader.readUnsignedByte();
        hdr.reserved = firstHeader.readUnsignedByte();
        hdr.dateTimeCreated = this.read32(firstHeader);
        hdr.dateTimeModified = this.read32(firstHeader);
        hdr.archiveSize = 0xFFFFFFFFL & (long)this.read32(firstHeader);
        hdr.securityEnvelopeFilePosition = this.read32(firstHeader);
        hdr.fileSpecPosition = this.read16(firstHeader);
        hdr.securityEnvelopeLength = this.read16(firstHeader);
        this.pushedBackBytes(20L);
        hdr.encryptionVersion = firstHeader.readUnsignedByte();
        hdr.lastChapter = firstHeader.readUnsignedByte();
        if (firstHeaderSize >= 33) {
            hdr.arjProtectionFactor = firstHeader.readUnsignedByte();
            hdr.arjFlags2 = firstHeader.readUnsignedByte();
            firstHeader.readUnsignedByte();
            firstHeader.readUnsignedByte();
        }
        hdr.name = this.readString(basicHeader);
        hdr.comment = this.readString(basicHeader);
        int extendedHeaderSize = this.read16(this.in);
        if (extendedHeaderSize > 0) {
            hdr.extendedHeaderBytes = this.readRange(this.in, extendedHeaderSize);
            long extendedHeaderCrc32 = 0xFFFFFFFFL & (long)this.read32(this.in);
            CRC32 crc32 = new CRC32();
            crc32.update(hdr.extendedHeaderBytes);
            if (extendedHeaderCrc32 != crc32.getValue()) {
                throw new IOException("Extended header CRC32 verification failure");
            }
        }
        return hdr;
    }

    private byte[] readRange(InputStream in, int len) throws IOException {
        byte[] b2 = IOUtils.readRange(in, len);
        this.count(b2.length);
        if (b2.length < len) {
            throw new EOFException();
        }
        return b2;
    }

    private String readString(DataInputStream dataIn) throws IOException {
        try (ByteArrayOutputStream buffer = new ByteArrayOutputStream();){
            int nextByte;
            while ((nextByte = dataIn.readUnsignedByte()) != 0) {
                buffer.write(nextByte);
            }
            String string = buffer.toString(Charsets.toCharset(this.charsetName).name());
            return string;
        }
    }
}

