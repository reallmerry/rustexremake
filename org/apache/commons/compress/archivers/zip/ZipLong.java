/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.io.Serializable;
import org.apache.commons.compress.utils.ByteUtils;

public final class ZipLong
implements Cloneable,
Serializable {
    private static final long serialVersionUID = 1L;
    public static final ZipLong CFH_SIG = new ZipLong(33639248L);
    public static final ZipLong LFH_SIG = new ZipLong(67324752L);
    public static final ZipLong DD_SIG = new ZipLong(134695760L);
    static final ZipLong ZIP64_MAGIC = new ZipLong(0xFFFFFFFFL);
    public static final ZipLong SINGLE_SEGMENT_SPLIT_MARKER = new ZipLong(808471376L);
    public static final ZipLong AED_SIG = new ZipLong(134630224L);
    private final long value;

    public static byte[] getBytes(long value) {
        byte[] result = new byte[4];
        ZipLong.putLong(value, result, 0);
        return result;
    }

    public static long getValue(byte[] bytes) {
        return ZipLong.getValue(bytes, 0);
    }

    public static long getValue(byte[] bytes, int offset) {
        return ByteUtils.fromLittleEndian(bytes, offset, 4);
    }

    public static void putLong(long value, byte[] buf, int offset) {
        ByteUtils.toLittleEndian(buf, value, offset, 4);
    }

    public ZipLong(byte[] bytes) {
        this(bytes, 0);
    }

    public ZipLong(byte[] bytes, int offset) {
        this.value = ZipLong.getValue(bytes, offset);
    }

    public ZipLong(int value) {
        this.value = value;
    }

    public ZipLong(long value) {
        this.value = value;
    }

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException cnfe) {
            throw new UnsupportedOperationException(cnfe);
        }
    }

    public boolean equals(Object o2) {
        if (!(o2 instanceof ZipLong)) {
            return false;
        }
        return this.value == ((ZipLong)o2).getValue();
    }

    public byte[] getBytes() {
        return ZipLong.getBytes(this.value);
    }

    public int getIntValue() {
        return (int)this.value;
    }

    public long getValue() {
        return this.value;
    }

    public int hashCode() {
        return (int)this.value;
    }

    public void putLong(byte[] buf, int offset) {
        ZipLong.putLong(this.value, buf, offset);
    }

    public String toString() {
        return "ZipLong value: " + this.value;
    }
}

