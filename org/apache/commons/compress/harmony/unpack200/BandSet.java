/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.harmony.unpack200;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.apache.commons.compress.harmony.pack200.BHSDCodec;
import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.CodecEncoding;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.pack200.PopulationCodec;
import org.apache.commons.compress.harmony.unpack200.CpBands;
import org.apache.commons.compress.harmony.unpack200.Segment;
import org.apache.commons.compress.harmony.unpack200.SegmentHeader;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPClass;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPDouble;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPFieldRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPFloat;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPInteger;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPInterfaceMethodRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPLong;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPMethodRef;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPNameAndType;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPString;
import org.apache.commons.compress.harmony.unpack200.bytecode.CPUTF8;
import org.apache.commons.compress.utils.ExactMath;

public abstract class BandSet {
    protected Segment segment;
    protected SegmentHeader header;

    public BandSet(Segment segment) {
        this.segment = segment;
        this.header = segment.getSegmentHeader();
    }

    public int[] decodeBandInt(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        int[] band;
        Codec codecUsed = codec;
        if (codec.getB() == 1 || count == 0) {
            return codec.decodeInts(count, in);
        }
        int[] getFirst = codec.decodeInts(1, in);
        if (getFirst.length == 0) {
            return getFirst;
        }
        int first = getFirst[0];
        if (codec.isSigned() && first >= -256 && first <= -1) {
            codecUsed = CodecEncoding.getCodec(-1 - first, this.header.getBandHeadersInputStream(), codec);
            band = codecUsed.decodeInts(count, in);
        } else if (!codec.isSigned() && first >= codec.getL() && first <= codec.getL() + 255) {
            codecUsed = CodecEncoding.getCodec(first - codec.getL(), this.header.getBandHeadersInputStream(), codec);
            band = codecUsed.decodeInts(count, in);
        } else {
            band = codec.decodeInts(count - 1, in, first);
        }
        if (codecUsed instanceof PopulationCodec) {
            PopulationCodec popCodec = (PopulationCodec)codecUsed;
            int[] favoured = (int[])popCodec.getFavoured().clone();
            Arrays.sort(favoured);
            for (int i2 = 0; i2 < band.length; ++i2) {
                Codec theCodec;
                boolean favouredValue = Arrays.binarySearch(favoured, band[i2]) > -1;
                Codec codec2 = theCodec = favouredValue ? popCodec.getFavouredCodec() : popCodec.getUnfavouredCodec();
                if (!(theCodec instanceof BHSDCodec) || !((BHSDCodec)theCodec).isDelta()) continue;
                BHSDCodec bhsd = (BHSDCodec)theCodec;
                long cardinality = bhsd.cardinality();
                while ((long)band[i2] > bhsd.largest()) {
                    int n2 = i2;
                    band[n2] = (int)((long)band[n2] - cardinality);
                }
                while ((long)band[i2] < bhsd.smallest()) {
                    band[i2] = ExactMath.add(band[i2], cardinality);
                }
            }
        }
        return band;
    }

    public int[][] decodeBandInt(String name, InputStream in, BHSDCodec defaultCodec, int[] counts) throws IOException, Pack200Exception {
        int[][] result = new int[counts.length][];
        int totalCount = 0;
        for (int count : counts) {
            totalCount += count;
        }
        int[] twoDResult = this.decodeBandInt(name, in, defaultCodec, totalCount);
        int index = 0;
        for (int i2 = 0; i2 < result.length; ++i2) {
            result[i2] = new int[counts[i2]];
            for (int j2 = 0; j2 < result[i2].length; ++j2) {
                result[i2][j2] = twoDResult[index];
                ++index;
            }
        }
        return result;
    }

    protected String[] getReferences(int[] ints, String[] reference) {
        String[] result = new String[ints.length];
        Arrays.setAll(result, i2 -> reference[ints[i2]]);
        return result;
    }

    protected String[][] getReferences(int[][] ints, String[] reference) {
        String[][] result = new String[ints.length][];
        for (int i2 = 0; i2 < result.length; ++i2) {
            result[i2] = new String[ints[i2].length];
            for (int j2 = 0; j2 < result[i2].length; ++j2) {
                result[i2][j2] = reference[ints[i2][j2]];
            }
        }
        return result;
    }

    public CPClass[] parseCPClassReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        int[] indices = this.decodeBandInt(name, in, codec, count);
        CPClass[] result = new CPClass[indices.length];
        for (int i1 = 0; i1 < count; ++i1) {
            result[i1] = this.segment.getCpBands().cpClassValue(indices[i1]);
        }
        return result;
    }

    public CPNameAndType[] parseCPDescriptorReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        CpBands cpBands = this.segment.getCpBands();
        int[] indices = this.decodeBandInt(name, in, codec, count);
        CPNameAndType[] result = new CPNameAndType[indices.length];
        for (int i1 = 0; i1 < count; ++i1) {
            int index = indices[i1];
            result[i1] = cpBands.cpNameAndTypeValue(index);
        }
        return result;
    }

    public CPDouble[] parseCPDoubleReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        int[] indices = this.decodeBandInt(name, in, codec, count);
        CPDouble[] result = new CPDouble[indices.length];
        for (int i1 = 0; i1 < count; ++i1) {
            result[i1] = this.segment.getCpBands().cpDoubleValue(indices[i1]);
        }
        return result;
    }

    public CPFieldRef[] parseCPFieldRefReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        CpBands cpBands = this.segment.getCpBands();
        int[] indices = this.decodeBandInt(name, in, codec, count);
        CPFieldRef[] result = new CPFieldRef[indices.length];
        for (int i1 = 0; i1 < count; ++i1) {
            int index = indices[i1];
            result[i1] = cpBands.cpFieldValue(index);
        }
        return result;
    }

    public CPFloat[] parseCPFloatReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        int[] indices = this.decodeBandInt(name, in, codec, count);
        CPFloat[] result = new CPFloat[indices.length];
        for (int i1 = 0; i1 < count; ++i1) {
            result[i1] = this.segment.getCpBands().cpFloatValue(indices[i1]);
        }
        return result;
    }

    public CPInterfaceMethodRef[] parseCPInterfaceMethodRefReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        CpBands cpBands = this.segment.getCpBands();
        int[] indices = this.decodeBandInt(name, in, codec, count);
        CPInterfaceMethodRef[] result = new CPInterfaceMethodRef[indices.length];
        for (int i1 = 0; i1 < count; ++i1) {
            result[i1] = cpBands.cpIMethodValue(indices[i1]);
        }
        return result;
    }

    public CPInteger[] parseCPIntReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        int[] reference = this.segment.getCpBands().getCpInt();
        int[] indices = this.decodeBandInt(name, in, codec, count);
        CPInteger[] result = new CPInteger[indices.length];
        for (int i1 = 0; i1 < count; ++i1) {
            int index = indices[i1];
            if (index < 0 || index >= reference.length) {
                throw new Pack200Exception("Something has gone wrong during parsing references, index = " + index + ", array size = " + reference.length);
            }
            result[i1] = this.segment.getCpBands().cpIntegerValue(index);
        }
        return result;
    }

    public CPLong[] parseCPLongReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        long[] reference = this.segment.getCpBands().getCpLong();
        int[] indices = this.decodeBandInt(name, in, codec, count);
        CPLong[] result = new CPLong[indices.length];
        for (int i1 = 0; i1 < count; ++i1) {
            int index = indices[i1];
            if (index < 0 || index >= reference.length) {
                throw new Pack200Exception("Something has gone wrong during parsing references, index = " + index + ", array size = " + reference.length);
            }
            result[i1] = this.segment.getCpBands().cpLongValue(index);
        }
        return result;
    }

    public CPMethodRef[] parseCPMethodRefReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        CpBands cpBands = this.segment.getCpBands();
        int[] indices = this.decodeBandInt(name, in, codec, count);
        CPMethodRef[] result = new CPMethodRef[indices.length];
        for (int i1 = 0; i1 < count; ++i1) {
            result[i1] = cpBands.cpMethodValue(indices[i1]);
        }
        return result;
    }

    public CPUTF8[] parseCPSignatureReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        int[] indices = this.decodeBandInt(name, in, codec, count);
        CPUTF8[] result = new CPUTF8[indices.length];
        for (int i1 = 0; i1 < count; ++i1) {
            result[i1] = this.segment.getCpBands().cpSignatureValue(indices[i1]);
        }
        return result;
    }

    protected CPUTF8[][] parseCPSignatureReferences(String name, InputStream in, BHSDCodec codec, int[] counts) throws IOException, Pack200Exception {
        CPUTF8[][] result = new CPUTF8[counts.length][];
        int sum = 0;
        for (int i2 = 0; i2 < counts.length; ++i2) {
            result[i2] = new CPUTF8[counts[i2]];
            sum += counts[i2];
        }
        CPUTF8[] result1 = new CPUTF8[sum];
        int[] indices = this.decodeBandInt(name, in, codec, sum);
        for (int i1 = 0; i1 < sum; ++i1) {
            result1[i1] = this.segment.getCpBands().cpSignatureValue(indices[i1]);
        }
        int pos = 0;
        for (int i3 = 0; i3 < counts.length; ++i3) {
            int num = counts[i3];
            result[i3] = new CPUTF8[num];
            System.arraycopy(result1, pos, result[i3], 0, num);
            pos += num;
        }
        return result;
    }

    public CPString[] parseCPStringReferences(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        int[] indices = this.decodeBandInt(name, in, codec, count);
        CPString[] result = new CPString[indices.length];
        for (int i1 = 0; i1 < count; ++i1) {
            result[i1] = this.segment.getCpBands().cpStringValue(indices[i1]);
        }
        return result;
    }

    public CPUTF8[] parseCPUTF8References(String name, InputStream in, BHSDCodec codec, int count) throws IOException, Pack200Exception {
        int[] indices = this.decodeBandInt(name, in, codec, count);
        CPUTF8[] result = new CPUTF8[indices.length];
        for (int i1 = 0; i1 < count; ++i1) {
            int index = indices[i1];
            result[i1] = this.segment.getCpBands().cpUTF8Value(index);
        }
        return result;
    }

    public CPUTF8[][] parseCPUTF8References(String name, InputStream in, BHSDCodec codec, int[] counts) throws IOException, Pack200Exception {
        CPUTF8[][] result = new CPUTF8[counts.length][];
        int sum = 0;
        for (int i2 = 0; i2 < counts.length; ++i2) {
            result[i2] = new CPUTF8[counts[i2]];
            sum += counts[i2];
        }
        CPUTF8[] result1 = new CPUTF8[sum];
        int[] indices = this.decodeBandInt(name, in, codec, sum);
        for (int i1 = 0; i1 < sum; ++i1) {
            int index = indices[i1];
            result1[i1] = this.segment.getCpBands().cpUTF8Value(index);
        }
        int pos = 0;
        for (int i3 = 0; i3 < counts.length; ++i3) {
            int num = counts[i3];
            result[i3] = new CPUTF8[num];
            System.arraycopy(result1, pos, result[i3], 0, num);
            pos += num;
        }
        return result;
    }

    public long[] parseFlags(String name, InputStream in, int count, BHSDCodec hiCodec, BHSDCodec loCodec) throws IOException, Pack200Exception {
        return this.parseFlags(name, in, new int[]{count}, hiCodec, loCodec)[0];
    }

    public long[] parseFlags(String name, InputStream in, int count, BHSDCodec codec, boolean hasHi) throws IOException, Pack200Exception {
        return this.parseFlags(name, in, new int[]{count}, hasHi ? codec : null, codec)[0];
    }

    public long[][] parseFlags(String name, InputStream in, int[] counts, BHSDCodec hiCodec, BHSDCodec loCodec) throws IOException, Pack200Exception {
        int count = counts.length;
        if (count == 0) {
            return new long[][]{new long[0]};
        }
        int sum = 0;
        long[][] result = new long[count][];
        for (int i2 = 0; i2 < count; ++i2) {
            result[i2] = new long[counts[i2]];
            sum += counts[i2];
        }
        int[] hi = null;
        if (hiCodec != null) {
            hi = this.decodeBandInt(name, in, hiCodec, sum);
        }
        int[] lo = this.decodeBandInt(name, in, loCodec, sum);
        int index = 0;
        for (int i3 = 0; i3 < result.length; ++i3) {
            for (int j2 = 0; j2 < result[i3].length; ++j2) {
                result[i3][j2] = hi != null ? (long)hi[index] << 32 | (long)lo[index] & 0xFFFFFFFFL : (long)lo[index];
                ++index;
            }
        }
        return result;
    }

    public long[][] parseFlags(String name, InputStream in, int[] counts, BHSDCodec codec, boolean hasHi) throws IOException, Pack200Exception {
        return this.parseFlags(name, in, counts, hasHi ? codec : null, codec);
    }

    public String[] parseReferences(String name, InputStream in, BHSDCodec codec, int count, String[] reference) throws IOException, Pack200Exception {
        return this.parseReferences(name, in, codec, new int[]{count}, reference)[0];
    }

    public String[][] parseReferences(String name, InputStream in, BHSDCodec codec, int[] counts, String[] reference) throws IOException, Pack200Exception {
        int count = counts.length;
        if (count == 0) {
            return new String[][]{new String[0]};
        }
        String[][] result = new String[count][];
        int sum = 0;
        for (int i2 = 0; i2 < count; ++i2) {
            result[i2] = new String[counts[i2]];
            sum += counts[i2];
        }
        String[] result1 = new String[sum];
        int[] indices = this.decodeBandInt(name, in, codec, sum);
        for (int i1 = 0; i1 < sum; ++i1) {
            int index = indices[i1];
            if (index < 0 || index >= reference.length) {
                throw new Pack200Exception("Something has gone wrong during parsing references, index = " + index + ", array size = " + reference.length);
            }
            result1[i1] = reference[index];
        }
        int pos = 0;
        for (int i3 = 0; i3 < count; ++i3) {
            int num = counts[i3];
            result[i3] = new String[num];
            System.arraycopy(result1, pos, result[i3], 0, num);
            pos += num;
        }
        return result;
    }

    public abstract void read(InputStream var1) throws IOException, Pack200Exception;

    public abstract void unpack() throws IOException, Pack200Exception;

    public void unpack(InputStream in) throws IOException, Pack200Exception {
        this.read(in);
        this.unpack();
    }
}

