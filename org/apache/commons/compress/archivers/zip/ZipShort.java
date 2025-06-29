/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.io.Serializable;
import org.apache.commons.compress.utils.ByteUtils;

public final class ZipShort
implements Cloneable,
Serializable {
    public static final ZipShort ZERO = new ZipShort(0);
    private static final long serialVersionUID = 1L;
    private final int value;

    public static byte[] getBytes(int value) {
        byte[] result = new byte[2];
        ZipShort.putShort(value, result, 0);
        return result;
    }

    public static int getValue(byte[] bytes) {
        return ZipShort.getValue(bytes, 0);
    }

    public static int getValue(byte[] bytes, int offset) {
        return (int)ByteUtils.fromLittleEndian(bytes, offset, 2);
    }

    public static void putShort(int value, byte[] buf, int offset) {
        ByteUtils.toLittleEndian(buf, value, offset, 2);
    }

    public ZipShort(byte[] bytes) {
        this(bytes, 0);
    }

    public ZipShort(byte[] bytes, int offset) {
        this.value = ZipShort.getValue(bytes, offset);
    }

    public ZipShort(int value) {
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
        if (!(o2 instanceof ZipShort)) {
            return false;
        }
        return this.value == ((ZipShort)o2).getValue();
    }

    public byte[] getBytes() {
        byte[] result = new byte[2];
        ByteUtils.toLittleEndian(result, this.value, 0, 2);
        return result;
    }

    public int getValue() {
        return this.value;
    }

    public int hashCode() {
        return this.value;
    }

    public String toString() {
        return "ZipShort value: " + this.value;
    }
}

