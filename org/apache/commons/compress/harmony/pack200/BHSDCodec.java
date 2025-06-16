/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.harmony.pack200;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.utils.ExactMath;

public final class BHSDCodec
extends Codec {
    private final int b;
    private final int d;
    private final int h;
    private final int l;
    private final int s;
    private long cardinality;
    private final long smallest;
    private final long largest;
    private final long[] powers;

    public BHSDCodec(int b2, int h2) {
        this(b2, h2, 0, 0);
    }

    public BHSDCodec(int b2, int h2, int s2) {
        this(b2, h2, s2, 0);
    }

    public BHSDCodec(int b2, int h2, int s2, int d2) {
        if (b2 < 1 || b2 > 5) {
            throw new IllegalArgumentException("1<=b<=5");
        }
        if (h2 < 1 || h2 > 256) {
            throw new IllegalArgumentException("1<=h<=256");
        }
        if (s2 < 0 || s2 > 2) {
            throw new IllegalArgumentException("0<=s<=2");
        }
        if (d2 < 0 || d2 > 1) {
            throw new IllegalArgumentException("0<=d<=1");
        }
        if (b2 == 1 && h2 != 256) {
            throw new IllegalArgumentException("b=1 -> h=256");
        }
        if (h2 == 256 && b2 == 5) {
            throw new IllegalArgumentException("h=256 -> b!=5");
        }
        this.b = b2;
        this.h = h2;
        this.s = s2;
        this.d = d2;
        this.l = 256 - h2;
        this.cardinality = h2 == 1 ? (long)(b2 * 255 + 1) : (long)((double)((long)((double)this.l * (1.0 - Math.pow(h2, b2)) / (double)(1 - h2))) + Math.pow(h2, b2));
        this.smallest = this.calculateSmallest();
        this.largest = this.calculateLargest();
        this.powers = new long[b2];
        Arrays.setAll(this.powers, c2 -> (long)Math.pow(h2, c2));
    }

    private long calculateLargest() {
        long result;
        if (this.d == 1) {
            BHSDCodec bh0 = new BHSDCodec(this.b, this.h);
            return bh0.largest();
        }
        switch (this.s) {
            case 0: {
                result = this.cardinality() - 1L;
                break;
            }
            case 1: {
                result = this.cardinality() / 2L - 1L;
                break;
            }
            case 2: {
                result = 3L * this.cardinality() / 4L - 1L;
                break;
            }
            default: {
                throw new Error("Unknown s value");
            }
        }
        return Math.min((this.s == 0 ? 0xFFFFFFFEL : Integer.MAX_VALUE) - 1L, result);
    }

    private long calculateSmallest() {
        long result = this.d == 1 || !this.isSigned() ? (this.cardinality >= 0x100000000L ? Integer.MIN_VALUE : 0L) : Math.max(Integer.MIN_VALUE, -this.cardinality() / (long)(1 << this.s));
        return result;
    }

    public long cardinality() {
        return this.cardinality;
    }

    @Override
    public int decode(InputStream in) throws IOException, Pack200Exception {
        if (this.d != 0) {
            throw new Pack200Exception("Delta encoding used without passing in last value; this is a coding error");
        }
        return this.decode(in, 0L);
    }

    @Override
    public int decode(InputStream in, long last) throws IOException, Pack200Exception {
        int n2 = 0;
        long z2 = 0L;
        long x2 = 0L;
        do {
            x2 = in.read();
            ++this.lastBandLength;
            z2 += x2 * this.powers[n2];
        } while (x2 >= (long)this.l && ++n2 < this.b);
        if (x2 == -1L) {
            throw new EOFException("End of stream reached whilst decoding");
        }
        if (this.isSigned()) {
            int u2 = (1 << this.s) - 1;
            z2 = (z2 & (long)u2) == (long)u2 ? z2 >>> this.s ^ 0xFFFFFFFFFFFFFFFFL : (z2 -= z2 >>> this.s);
        }
        if (this.isDelta()) {
            z2 += last;
        }
        return (int)z2;
    }

    @Override
    public int[] decodeInts(int n2, InputStream in) throws IOException, Pack200Exception {
        int[] band = super.decodeInts(n2, in);
        if (this.isDelta()) {
            for (int i2 = 0; i2 < band.length; ++i2) {
                while ((long)band[i2] > this.largest) {
                    int n3 = i2;
                    band[n3] = (int)((long)band[n3] - this.cardinality);
                }
                while ((long)band[i2] < this.smallest) {
                    band[i2] = ExactMath.add(band[i2], this.cardinality);
                }
            }
        }
        return band;
    }

    @Override
    public int[] decodeInts(int n2, InputStream in, int firstValue) throws IOException, Pack200Exception {
        int[] band = super.decodeInts(n2, in, firstValue);
        if (this.isDelta()) {
            for (int i2 = 0; i2 < band.length; ++i2) {
                while ((long)band[i2] > this.largest) {
                    int n3 = i2;
                    band[n3] = (int)((long)band[n3] - this.cardinality);
                }
                while ((long)band[i2] < this.smallest) {
                    band[i2] = ExactMath.add(band[i2], this.cardinality);
                }
            }
        }
        return band;
    }

    @Override
    public byte[] encode(int value) throws Pack200Exception {
        return this.encode(value, 0);
    }

    @Override
    public byte[] encode(int value, int last) throws Pack200Exception {
        if (!this.encodes(value)) {
            throw new Pack200Exception("The codec " + this + " does not encode the value " + value);
        }
        long z2 = value;
        if (this.isDelta()) {
            z2 -= (long)last;
        }
        if (this.isSigned()) {
            if (z2 < Integer.MIN_VALUE) {
                z2 += 0x100000000L;
            } else if (z2 > Integer.MAX_VALUE) {
                z2 -= 0x100000000L;
            }
            z2 = z2 < 0L ? (-z2 << this.s) - 1L : (this.s == 1 ? (z2 <<= this.s) : (z2 += (z2 - z2 % 3L) / 3L));
        } else if (z2 < 0L) {
            z2 += Math.min(this.cardinality, 0x100000000L);
        }
        if (z2 < 0L) {
            throw new Pack200Exception("unable to encode");
        }
        ArrayList<Byte> byteList = new ArrayList<Byte>();
        for (int n2 = 0; n2 < this.b; ++n2) {
            long byteN;
            if (z2 < (long)this.l) {
                byteN = z2;
            } else {
                for (byteN = z2 % (long)this.h; byteN < (long)this.l; byteN += (long)this.h) {
                }
            }
            byteList.add((byte)byteN);
            if (byteN < (long)this.l) break;
            z2 -= byteN;
            z2 /= (long)this.h;
        }
        byte[] bytes = new byte[byteList.size()];
        for (int i2 = 0; i2 < bytes.length; ++i2) {
            bytes[i2] = (Byte)byteList.get(i2);
        }
        return bytes;
    }

    public boolean encodes(long value) {
        return value >= this.smallest && value <= this.largest;
    }

    public boolean equals(Object o2) {
        if (o2 instanceof BHSDCodec) {
            BHSDCodec codec = (BHSDCodec)o2;
            return codec.b == this.b && codec.h == this.h && codec.s == this.s && codec.d == this.d;
        }
        return false;
    }

    public int getB() {
        return this.b;
    }

    public int getH() {
        return this.h;
    }

    public int getL() {
        return this.l;
    }

    public int getS() {
        return this.s;
    }

    public int hashCode() {
        return ((this.b * 37 + this.h) * 37 + this.s) * 37 + this.d;
    }

    public boolean isDelta() {
        return this.d != 0;
    }

    public boolean isSigned() {
        return this.s != 0;
    }

    public long largest() {
        return this.largest;
    }

    public long smallest() {
        return this.smallest;
    }

    public String toString() {
        StringBuilder buffer = new StringBuilder(11);
        buffer.append('(');
        buffer.append(this.b);
        buffer.append(',');
        buffer.append(this.h);
        if (this.s != 0 || this.d != 0) {
            buffer.append(',');
            buffer.append(this.s);
        }
        if (this.d != 0) {
            buffer.append(',');
            buffer.append(this.d);
        }
        buffer.append(')');
        return buffer.toString();
    }
}

