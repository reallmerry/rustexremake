/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.zip.ZipExtraField;
import org.apache.commons.compress.archivers.zip.ZipShort;
import org.apache.commons.compress.archivers.zip.ZipUtil;
import org.apache.commons.compress.utils.ByteUtils;

public class X7875_NewUnix
implements ZipExtraField,
Cloneable,
Serializable {
    private static final ZipShort HEADER_ID = new ZipShort(30837);
    private static final ZipShort ZERO = new ZipShort(0);
    private static final BigInteger ONE_THOUSAND = BigInteger.valueOf(1000L);
    private static final long serialVersionUID = 1L;
    private int version = 1;
    private BigInteger uid;
    private BigInteger gid;

    static byte[] trimLeadingZeroesForceMinLength(byte[] array) {
        if (array == null) {
            return null;
        }
        int pos = 0;
        for (byte b2 : array) {
            if (b2 != 0) break;
            ++pos;
        }
        boolean MIN_LENGTH = true;
        byte[] trimmedArray = new byte[Math.max(1, array.length - pos)];
        int startPos = trimmedArray.length - (array.length - pos);
        System.arraycopy(array, pos, trimmedArray, startPos, trimmedArray.length - startPos);
        return trimmedArray;
    }

    public X7875_NewUnix() {
        this.reset();
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public boolean equals(Object o2) {
        if (o2 instanceof X7875_NewUnix) {
            X7875_NewUnix xf = (X7875_NewUnix)o2;
            return this.version == xf.version && this.uid.equals(xf.uid) && this.gid.equals(xf.gid);
        }
        return false;
    }

    @Override
    public byte[] getCentralDirectoryData() {
        return ByteUtils.EMPTY_BYTE_ARRAY;
    }

    @Override
    public ZipShort getCentralDirectoryLength() {
        return ZERO;
    }

    public long getGID() {
        return ZipUtil.bigToLong(this.gid);
    }

    @Override
    public ZipShort getHeaderId() {
        return HEADER_ID;
    }

    @Override
    public byte[] getLocalFileDataData() {
        byte[] uidBytes = this.uid.toByteArray();
        byte[] gidBytes = this.gid.toByteArray();
        int uidBytesLen = (uidBytes = X7875_NewUnix.trimLeadingZeroesForceMinLength(uidBytes)) != null ? uidBytes.length : 0;
        gidBytes = X7875_NewUnix.trimLeadingZeroesForceMinLength(gidBytes);
        int gidBytesLen = gidBytes != null ? gidBytes.length : 0;
        byte[] data = new byte[3 + uidBytesLen + gidBytesLen];
        if (uidBytes != null) {
            ZipUtil.reverse(uidBytes);
        }
        if (gidBytes != null) {
            ZipUtil.reverse(gidBytes);
        }
        int pos = 0;
        data[pos++] = ZipUtil.unsignedIntToSignedByte(this.version);
        data[pos++] = ZipUtil.unsignedIntToSignedByte(uidBytesLen);
        if (uidBytes != null) {
            System.arraycopy(uidBytes, 0, data, pos, uidBytesLen);
        }
        pos += uidBytesLen;
        data[pos++] = ZipUtil.unsignedIntToSignedByte(gidBytesLen);
        if (gidBytes != null) {
            System.arraycopy(gidBytes, 0, data, pos, gidBytesLen);
        }
        return data;
    }

    @Override
    public ZipShort getLocalFileDataLength() {
        byte[] b2 = X7875_NewUnix.trimLeadingZeroesForceMinLength(this.uid.toByteArray());
        int uidSize = b2 == null ? 0 : b2.length;
        b2 = X7875_NewUnix.trimLeadingZeroesForceMinLength(this.gid.toByteArray());
        int gidSize = b2 == null ? 0 : b2.length;
        return new ZipShort(3 + uidSize + gidSize);
    }

    public long getUID() {
        return ZipUtil.bigToLong(this.uid);
    }

    public int hashCode() {
        int hc = -1234567 * this.version;
        hc ^= Integer.rotateLeft(this.uid.hashCode(), 16);
        return hc ^= this.gid.hashCode();
    }

    @Override
    public void parseFromCentralDirectoryData(byte[] buffer, int offset, int length) throws ZipException {
    }

    @Override
    public void parseFromLocalFileData(byte[] data, int offset, int length) throws ZipException {
        int gidSize;
        int uidSize;
        this.reset();
        if (length < 3) {
            throw new ZipException("X7875_NewUnix length is too short, only " + length + " bytes");
        }
        this.version = ZipUtil.signedByteToUnsignedInt(data[offset++]);
        if ((uidSize = ZipUtil.signedByteToUnsignedInt(data[offset++])) + 3 > length) {
            throw new ZipException("X7875_NewUnix invalid: uidSize " + uidSize + " doesn't fit into " + length + " bytes");
        }
        byte[] uidBytes = Arrays.copyOfRange(data, offset, offset + uidSize);
        offset += uidSize;
        this.uid = new BigInteger(1, ZipUtil.reverse(uidBytes));
        if (uidSize + 3 + (gidSize = ZipUtil.signedByteToUnsignedInt(data[offset++])) > length) {
            throw new ZipException("X7875_NewUnix invalid: gidSize " + gidSize + " doesn't fit into " + length + " bytes");
        }
        byte[] gidBytes = Arrays.copyOfRange(data, offset, offset + gidSize);
        this.gid = new BigInteger(1, ZipUtil.reverse(gidBytes));
    }

    private void reset() {
        this.uid = ONE_THOUSAND;
        this.gid = ONE_THOUSAND;
    }

    public void setGID(long l2) {
        this.gid = ZipUtil.longToBig(l2);
    }

    public void setUID(long l2) {
        this.uid = ZipUtil.longToBig(l2);
    }

    public String toString() {
        return "0x7875 Zip Extra Field: UID=" + this.uid + " GID=" + this.gid;
    }
}

