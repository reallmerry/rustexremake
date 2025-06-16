/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.nio.file.attribute.FileTime;
import java.util.Date;
import java.util.Objects;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.zip.ZipEightByteInteger;
import org.apache.commons.compress.archivers.zip.ZipExtraField;
import org.apache.commons.compress.archivers.zip.ZipShort;
import org.apache.commons.compress.utils.TimeUtils;

public class X000A_NTFS
implements ZipExtraField {
    public static final ZipShort HEADER_ID = new ZipShort(10);
    private static final ZipShort TIME_ATTR_TAG = new ZipShort(1);
    private static final ZipShort TIME_ATTR_SIZE = new ZipShort(24);
    private ZipEightByteInteger modifyTime = ZipEightByteInteger.ZERO;
    private ZipEightByteInteger accessTime = ZipEightByteInteger.ZERO;
    private ZipEightByteInteger createTime = ZipEightByteInteger.ZERO;

    private static ZipEightByteInteger dateToZip(Date d2) {
        if (d2 == null) {
            return null;
        }
        return new ZipEightByteInteger(TimeUtils.toNtfsTime(d2));
    }

    private static ZipEightByteInteger fileTimeToZip(FileTime time) {
        if (time == null) {
            return null;
        }
        return new ZipEightByteInteger(TimeUtils.toNtfsTime(time));
    }

    private static Date zipToDate(ZipEightByteInteger z2) {
        if (z2 == null || ZipEightByteInteger.ZERO.equals(z2)) {
            return null;
        }
        return TimeUtils.ntfsTimeToDate(z2.getLongValue());
    }

    private static FileTime zipToFileTime(ZipEightByteInteger z2) {
        if (z2 == null || ZipEightByteInteger.ZERO.equals(z2)) {
            return null;
        }
        return TimeUtils.ntfsTimeToFileTime(z2.getLongValue());
    }

    public boolean equals(Object o2) {
        if (o2 instanceof X000A_NTFS) {
            X000A_NTFS xf = (X000A_NTFS)o2;
            return Objects.equals(this.modifyTime, xf.modifyTime) && Objects.equals(this.accessTime, xf.accessTime) && Objects.equals(this.createTime, xf.createTime);
        }
        return false;
    }

    public FileTime getAccessFileTime() {
        return X000A_NTFS.zipToFileTime(this.accessTime);
    }

    public Date getAccessJavaTime() {
        return X000A_NTFS.zipToDate(this.accessTime);
    }

    public ZipEightByteInteger getAccessTime() {
        return this.accessTime;
    }

    @Override
    public byte[] getCentralDirectoryData() {
        return this.getLocalFileDataData();
    }

    @Override
    public ZipShort getCentralDirectoryLength() {
        return this.getLocalFileDataLength();
    }

    public FileTime getCreateFileTime() {
        return X000A_NTFS.zipToFileTime(this.createTime);
    }

    public Date getCreateJavaTime() {
        return X000A_NTFS.zipToDate(this.createTime);
    }

    public ZipEightByteInteger getCreateTime() {
        return this.createTime;
    }

    @Override
    public ZipShort getHeaderId() {
        return HEADER_ID;
    }

    @Override
    public byte[] getLocalFileDataData() {
        byte[] data = new byte[this.getLocalFileDataLength().getValue()];
        int pos = 4;
        System.arraycopy(TIME_ATTR_TAG.getBytes(), 0, data, pos, 2);
        System.arraycopy(TIME_ATTR_SIZE.getBytes(), 0, data, pos += 2, 2);
        System.arraycopy(this.modifyTime.getBytes(), 0, data, pos += 2, 8);
        System.arraycopy(this.accessTime.getBytes(), 0, data, pos += 8, 8);
        System.arraycopy(this.createTime.getBytes(), 0, data, pos += 8, 8);
        return data;
    }

    @Override
    public ZipShort getLocalFileDataLength() {
        return new ZipShort(32);
    }

    public FileTime getModifyFileTime() {
        return X000A_NTFS.zipToFileTime(this.modifyTime);
    }

    public Date getModifyJavaTime() {
        return X000A_NTFS.zipToDate(this.modifyTime);
    }

    public ZipEightByteInteger getModifyTime() {
        return this.modifyTime;
    }

    public int hashCode() {
        int hc = -123;
        if (this.modifyTime != null) {
            hc ^= this.modifyTime.hashCode();
        }
        if (this.accessTime != null) {
            hc ^= Integer.rotateLeft(this.accessTime.hashCode(), 11);
        }
        if (this.createTime != null) {
            hc ^= Integer.rotateLeft(this.createTime.hashCode(), 22);
        }
        return hc;
    }

    @Override
    public void parseFromCentralDirectoryData(byte[] buffer, int offset, int length) throws ZipException {
        this.reset();
        this.parseFromLocalFileData(buffer, offset, length);
    }

    @Override
    public void parseFromLocalFileData(byte[] data, int offset, int length) throws ZipException {
        int len = offset + length;
        offset += 4;
        while (offset + 4 <= len) {
            ZipShort tag = new ZipShort(data, offset);
            offset += 2;
            if (tag.equals(TIME_ATTR_TAG)) {
                this.readTimeAttr(data, offset, len - offset);
                break;
            }
            ZipShort size = new ZipShort(data, offset);
            offset += 2 + size.getValue();
        }
    }

    private void readTimeAttr(byte[] data, int offset, int length) {
        ZipShort tagValueLength;
        if (length >= 26 && TIME_ATTR_SIZE.equals(tagValueLength = new ZipShort(data, offset))) {
            this.modifyTime = new ZipEightByteInteger(data, offset += 2);
            this.accessTime = new ZipEightByteInteger(data, offset += 8);
            this.createTime = new ZipEightByteInteger(data, offset += 8);
        }
    }

    private void reset() {
        this.modifyTime = ZipEightByteInteger.ZERO;
        this.accessTime = ZipEightByteInteger.ZERO;
        this.createTime = ZipEightByteInteger.ZERO;
    }

    public void setAccessFileTime(FileTime time) {
        this.setAccessTime(X000A_NTFS.fileTimeToZip(time));
    }

    public void setAccessJavaTime(Date d2) {
        this.setAccessTime(X000A_NTFS.dateToZip(d2));
    }

    public void setAccessTime(ZipEightByteInteger t2) {
        this.accessTime = t2 == null ? ZipEightByteInteger.ZERO : t2;
    }

    public void setCreateFileTime(FileTime time) {
        this.setCreateTime(X000A_NTFS.fileTimeToZip(time));
    }

    public void setCreateJavaTime(Date d2) {
        this.setCreateTime(X000A_NTFS.dateToZip(d2));
    }

    public void setCreateTime(ZipEightByteInteger t2) {
        this.createTime = t2 == null ? ZipEightByteInteger.ZERO : t2;
    }

    public void setModifyFileTime(FileTime time) {
        this.setModifyTime(X000A_NTFS.fileTimeToZip(time));
    }

    public void setModifyJavaTime(Date d2) {
        this.setModifyTime(X000A_NTFS.dateToZip(d2));
    }

    public void setModifyTime(ZipEightByteInteger t2) {
        this.modifyTime = t2 == null ? ZipEightByteInteger.ZERO : t2;
    }

    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append("0x000A Zip Extra Field:").append(" Modify:[").append(this.getModifyFileTime()).append("] ").append(" Access:[").append(this.getAccessFileTime()).append("] ").append(" Create:[").append(this.getCreateFileTime()).append("] ");
        return buf.toString();
    }
}

