/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.zip.CRC32;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.zip.ZipExtraField;
import org.apache.commons.compress.archivers.zip.ZipLong;
import org.apache.commons.compress.archivers.zip.ZipShort;

public abstract class AbstractUnicodeExtraField
implements ZipExtraField {
    private long nameCRC32;
    private byte[] unicodeName;
    private byte[] data;

    protected AbstractUnicodeExtraField() {
    }

    protected AbstractUnicodeExtraField(String text, byte[] bytes) {
        this(text, bytes, 0, bytes.length);
    }

    protected AbstractUnicodeExtraField(String text, byte[] bytes, int off, int len) {
        CRC32 crc32 = new CRC32();
        crc32.update(bytes, off, len);
        this.nameCRC32 = crc32.getValue();
        this.unicodeName = text.getBytes(StandardCharsets.UTF_8);
    }

    private void assembleData() {
        if (this.unicodeName == null) {
            return;
        }
        this.data = new byte[5 + this.unicodeName.length];
        this.data[0] = 1;
        System.arraycopy(ZipLong.getBytes(this.nameCRC32), 0, this.data, 1, 4);
        System.arraycopy(this.unicodeName, 0, this.data, 5, this.unicodeName.length);
    }

    @Override
    public byte[] getCentralDirectoryData() {
        if (this.data == null) {
            this.assembleData();
        }
        byte[] b2 = null;
        if (this.data != null) {
            b2 = Arrays.copyOf(this.data, this.data.length);
        }
        return b2;
    }

    @Override
    public ZipShort getCentralDirectoryLength() {
        if (this.data == null) {
            this.assembleData();
        }
        return new ZipShort(this.data != null ? this.data.length : 0);
    }

    @Override
    public byte[] getLocalFileDataData() {
        return this.getCentralDirectoryData();
    }

    @Override
    public ZipShort getLocalFileDataLength() {
        return this.getCentralDirectoryLength();
    }

    public long getNameCRC32() {
        return this.nameCRC32;
    }

    public byte[] getUnicodeName() {
        return this.unicodeName != null ? Arrays.copyOf(this.unicodeName, this.unicodeName.length) : null;
    }

    @Override
    public void parseFromCentralDirectoryData(byte[] buffer, int offset, int length) throws ZipException {
        this.parseFromLocalFileData(buffer, offset, length);
    }

    @Override
    public void parseFromLocalFileData(byte[] buffer, int offset, int length) throws ZipException {
        if (length < 5) {
            throw new ZipException("UniCode path extra data must have at least 5 bytes.");
        }
        byte version = buffer[offset];
        if (version != 1) {
            throw new ZipException("Unsupported version [" + version + "] for UniCode path extra data.");
        }
        this.nameCRC32 = ZipLong.getValue(buffer, offset + 1);
        this.unicodeName = new byte[length - 5];
        System.arraycopy(buffer, offset + 5, this.unicodeName, 0, length - 5);
        this.data = null;
    }

    public void setNameCRC32(long nameCRC32) {
        this.nameCRC32 = nameCRC32;
        this.data = null;
    }

    public void setUnicodeName(byte[] unicodeName) {
        this.unicodeName = (byte[])(unicodeName != null ? Arrays.copyOf(unicodeName, unicodeName.length) : null);
        this.data = null;
    }
}

