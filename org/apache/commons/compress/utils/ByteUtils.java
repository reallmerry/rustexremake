/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.utils;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class ByteUtils {
    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];

    private static void checkReadLength(int length) {
        if (length > 8) {
            throw new IllegalArgumentException("Can't read more than eight bytes into a long value");
        }
    }

    public static long fromLittleEndian(byte[] bytes) {
        return ByteUtils.fromLittleEndian(bytes, 0, bytes.length);
    }

    public static long fromLittleEndian(byte[] bytes, int off, int length) {
        ByteUtils.checkReadLength(length);
        long l2 = 0L;
        for (int i2 = 0; i2 < length; ++i2) {
            l2 |= ((long)bytes[off + i2] & 0xFFL) << 8 * i2;
        }
        return l2;
    }

    public static long fromLittleEndian(ByteSupplier supplier, int length) throws IOException {
        ByteUtils.checkReadLength(length);
        long l2 = 0L;
        for (int i2 = 0; i2 < length; ++i2) {
            long b2 = supplier.getAsByte();
            if (b2 == -1L) {
                throw new IOException("Premature end of data");
            }
            l2 |= b2 << i2 * 8;
        }
        return l2;
    }

    public static long fromLittleEndian(DataInput in, int length) throws IOException {
        ByteUtils.checkReadLength(length);
        long l2 = 0L;
        for (int i2 = 0; i2 < length; ++i2) {
            long b2 = in.readUnsignedByte();
            l2 |= b2 << i2 * 8;
        }
        return l2;
    }

    public static long fromLittleEndian(InputStream in, int length) throws IOException {
        ByteUtils.checkReadLength(length);
        long l2 = 0L;
        for (int i2 = 0; i2 < length; ++i2) {
            long b2 = in.read();
            if (b2 == -1L) {
                throw new IOException("Premature end of data");
            }
            l2 |= b2 << i2 * 8;
        }
        return l2;
    }

    public static void toLittleEndian(byte[] b2, long value, int off, int length) {
        long num = value;
        for (int i2 = 0; i2 < length; ++i2) {
            b2[off + i2] = (byte)(num & 0xFFL);
            num >>= 8;
        }
    }

    public static void toLittleEndian(ByteConsumer consumer, long value, int length) throws IOException {
        long num = value;
        for (int i2 = 0; i2 < length; ++i2) {
            consumer.accept((int)(num & 0xFFL));
            num >>= 8;
        }
    }

    public static void toLittleEndian(DataOutput out, long value, int length) throws IOException {
        long num = value;
        for (int i2 = 0; i2 < length; ++i2) {
            out.write((int)(num & 0xFFL));
            num >>= 8;
        }
    }

    public static void toLittleEndian(OutputStream out, long value, int length) throws IOException {
        long num = value;
        for (int i2 = 0; i2 < length; ++i2) {
            out.write((int)(num & 0xFFL));
            num >>= 8;
        }
    }

    private ByteUtils() {
    }

    public static interface ByteSupplier {
        public int getAsByte() throws IOException;
    }

    public static interface ByteConsumer {
        public void accept(int var1) throws IOException;
    }

    public static class OutputStreamByteConsumer
    implements ByteConsumer {
        private final OutputStream os;

        public OutputStreamByteConsumer(OutputStream os) {
            this.os = os;
        }

        @Override
        public void accept(int b2) throws IOException {
            this.os.write(b2);
        }
    }

    public static class InputStreamByteSupplier
    implements ByteSupplier {
        private final InputStream is;

        public InputStreamByteSupplier(InputStream is) {
            this.is = is;
        }

        @Override
        public int getAsByte() throws IOException {
            return this.is.read();
        }
    }
}

