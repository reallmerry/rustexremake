/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.harmony.pack200;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import org.apache.commons.compress.harmony.pack200.BHSDCodec;
import org.apache.commons.compress.harmony.pack200.CanonicalCodecFamilies;
import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.CodecEncoding;
import org.apache.commons.compress.harmony.pack200.ConstantPoolEntry;
import org.apache.commons.compress.harmony.pack200.IntList;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.pack200.PopulationCodec;
import org.apache.commons.compress.harmony.pack200.RunCodec;
import org.apache.commons.compress.harmony.pack200.SegmentHeader;

public abstract class BandSet {
    private static final int[] effortThresholds = new int[]{0, 0, 1000, 500, 100, 100, 100, 100, 100, 0};
    protected final SegmentHeader segmentHeader;
    final int effort;
    private long[] canonicalLargest;
    private long[] canonicalSmallest;

    public BandSet(int effort, SegmentHeader header) {
        this.effort = effort;
        this.segmentHeader = header;
    }

    private BandAnalysisResults analyseBand(String name, int[] band, BHSDCodec defaultCodec) throws Pack200Exception {
        BandAnalysisResults results = new BandAnalysisResults();
        if (this.canonicalLargest == null) {
            this.canonicalLargest = new long[116];
            this.canonicalSmallest = new long[116];
            for (int i2 = 1; i2 < this.canonicalLargest.length; ++i2) {
                this.canonicalLargest[i2] = CodecEncoding.getCanonicalCodec(i2).largest();
                this.canonicalSmallest[i2] = CodecEncoding.getCanonicalCodec(i2).smallest();
            }
        }
        BandData bandData = new BandData(band);
        byte[] encoded = defaultCodec.encode(band);
        BandAnalysisResults.access$002(results, encoded);
        if (encoded.length <= band.length + 23 - 2 * this.effort) {
            return results;
        }
        if (!bandData.anyNegatives() && (long)bandData.largest <= Codec.BYTE1.largest()) {
            BandAnalysisResults.access$002(results, Codec.BYTE1.encode(band));
            results.betterCodec = Codec.BYTE1;
            return results;
        }
        if (this.effort > 3 && !name.equals("POPULATION")) {
            int numDistinctValues = bandData.numDistinctValues();
            float distinctValuesAsProportion = (float)numDistinctValues / (float)band.length;
            if (numDistinctValues < 100 || (double)distinctValuesAsProportion < 0.02 || this.effort > 6 && (double)distinctValuesAsProportion < 0.04) {
                this.encodeWithPopulationCodec(name, band, defaultCodec, bandData, results);
                if (this.timeToStop(results)) {
                    return results;
                }
            }
        }
        ArrayList<BHSDCodec[]> codecFamiliesToTry = new ArrayList<BHSDCodec[]>();
        if (bandData.mainlyPositiveDeltas() && bandData.mainlySmallDeltas()) {
            codecFamiliesToTry.add(CanonicalCodecFamilies.deltaUnsignedCodecs2);
        }
        if (bandData.wellCorrelated()) {
            if (bandData.mainlyPositiveDeltas()) {
                codecFamiliesToTry.add(CanonicalCodecFamilies.deltaUnsignedCodecs1);
                codecFamiliesToTry.add(CanonicalCodecFamilies.deltaUnsignedCodecs3);
                codecFamiliesToTry.add(CanonicalCodecFamilies.deltaUnsignedCodecs4);
                codecFamiliesToTry.add(CanonicalCodecFamilies.deltaUnsignedCodecs5);
                codecFamiliesToTry.add(CanonicalCodecFamilies.nonDeltaUnsignedCodecs1);
                codecFamiliesToTry.add(CanonicalCodecFamilies.nonDeltaUnsignedCodecs3);
                codecFamiliesToTry.add(CanonicalCodecFamilies.nonDeltaUnsignedCodecs4);
                codecFamiliesToTry.add(CanonicalCodecFamilies.nonDeltaUnsignedCodecs5);
                codecFamiliesToTry.add(CanonicalCodecFamilies.nonDeltaUnsignedCodecs2);
            } else {
                codecFamiliesToTry.add(CanonicalCodecFamilies.deltaSignedCodecs1);
                codecFamiliesToTry.add(CanonicalCodecFamilies.deltaSignedCodecs3);
                codecFamiliesToTry.add(CanonicalCodecFamilies.deltaSignedCodecs2);
                codecFamiliesToTry.add(CanonicalCodecFamilies.deltaSignedCodecs4);
                codecFamiliesToTry.add(CanonicalCodecFamilies.deltaSignedCodecs5);
                codecFamiliesToTry.add(CanonicalCodecFamilies.nonDeltaSignedCodecs1);
                codecFamiliesToTry.add(CanonicalCodecFamilies.nonDeltaSignedCodecs2);
            }
        } else if (bandData.anyNegatives()) {
            codecFamiliesToTry.add(CanonicalCodecFamilies.nonDeltaSignedCodecs1);
            codecFamiliesToTry.add(CanonicalCodecFamilies.nonDeltaSignedCodecs2);
            codecFamiliesToTry.add(CanonicalCodecFamilies.deltaSignedCodecs1);
            codecFamiliesToTry.add(CanonicalCodecFamilies.deltaSignedCodecs2);
            codecFamiliesToTry.add(CanonicalCodecFamilies.deltaSignedCodecs3);
            codecFamiliesToTry.add(CanonicalCodecFamilies.deltaSignedCodecs4);
            codecFamiliesToTry.add(CanonicalCodecFamilies.deltaSignedCodecs5);
        } else {
            codecFamiliesToTry.add(CanonicalCodecFamilies.nonDeltaUnsignedCodecs1);
            codecFamiliesToTry.add(CanonicalCodecFamilies.nonDeltaUnsignedCodecs3);
            codecFamiliesToTry.add(CanonicalCodecFamilies.nonDeltaUnsignedCodecs4);
            codecFamiliesToTry.add(CanonicalCodecFamilies.nonDeltaUnsignedCodecs5);
            codecFamiliesToTry.add(CanonicalCodecFamilies.nonDeltaUnsignedCodecs2);
            codecFamiliesToTry.add(CanonicalCodecFamilies.deltaUnsignedCodecs1);
            codecFamiliesToTry.add(CanonicalCodecFamilies.deltaUnsignedCodecs3);
            codecFamiliesToTry.add(CanonicalCodecFamilies.deltaUnsignedCodecs4);
            codecFamiliesToTry.add(CanonicalCodecFamilies.deltaUnsignedCodecs5);
        }
        if (name.equalsIgnoreCase("cpint")) {
            System.out.print("");
        }
        for (BHSDCodec[] family : codecFamiliesToTry) {
            this.tryCodecs(name, band, defaultCodec, bandData, results, encoded, family);
            if (!this.timeToStop(results)) continue;
            break;
        }
        return results;
    }

    protected int[] cpEntryListToArray(List<? extends ConstantPoolEntry> list) {
        int[] array = new int[list.size()];
        for (int i2 = 0; i2 < array.length; ++i2) {
            array[i2] = list.get(i2).getIndex();
            if (array[i2] >= 0) continue;
            throw new IllegalArgumentException("Index should be > 0");
        }
        return array;
    }

    protected int[] cpEntryOrNullListToArray(List<? extends ConstantPoolEntry> list) {
        int[] array = new int[list.size()];
        for (int j2 = 0; j2 < array.length; ++j2) {
            ConstantPoolEntry cpEntry = list.get(j2);
            int n2 = array[j2] = cpEntry == null ? 0 : cpEntry.getIndex() + 1;
            if (cpEntry == null || cpEntry.getIndex() >= 0) continue;
            throw new IllegalArgumentException("Index should be > 0");
        }
        return array;
    }

    public byte[] encodeBandInt(String name, int[] ints, BHSDCodec defaultCodec) throws Pack200Exception {
        byte[] encodedBand = null;
        if (this.effort > 1 && ints.length >= effortThresholds[this.effort]) {
            BandAnalysisResults results = this.analyseBand(name, ints, defaultCodec);
            Codec betterCodec = results.betterCodec;
            encodedBand = results.encodedBand;
            if (betterCodec != null) {
                if (betterCodec instanceof BHSDCodec) {
                    int[] specifierBand = CodecEncoding.getSpecifier(betterCodec, defaultCodec);
                    int specifier = specifierBand[0];
                    if (specifierBand.length > 1) {
                        for (int i2 = 1; i2 < specifierBand.length; ++i2) {
                            this.segmentHeader.appendBandCodingSpecifier(specifierBand[i2]);
                        }
                    }
                    specifier = defaultCodec.isSigned() ? -1 - specifier : (specifier += defaultCodec.getL());
                    byte[] specifierEncoded = defaultCodec.encode(new int[]{specifier});
                    byte[] band = new byte[specifierEncoded.length + encodedBand.length];
                    System.arraycopy(specifierEncoded, 0, band, 0, specifierEncoded.length);
                    System.arraycopy(encodedBand, 0, band, specifierEncoded.length, encodedBand.length);
                    return band;
                }
                if (betterCodec instanceof PopulationCodec) {
                    IntStream.of(results.extraMetadata).forEach(this.segmentHeader::appendBandCodingSpecifier);
                    return encodedBand;
                }
                if (betterCodec instanceof RunCodec) {
                    // empty if block
                }
            }
        }
        if (ints.length > 0) {
            if (encodedBand == null) {
                encodedBand = defaultCodec.encode(ints);
            }
            int first = ints[0];
            if (defaultCodec.getB() != 1) {
                if (defaultCodec.isSigned() && first >= -256 && first <= -1) {
                    int specifier = -1 - CodecEncoding.getSpecifierForDefaultCodec(defaultCodec);
                    byte[] specifierEncoded = defaultCodec.encode(new int[]{specifier});
                    byte[] band = new byte[specifierEncoded.length + encodedBand.length];
                    System.arraycopy(specifierEncoded, 0, band, 0, specifierEncoded.length);
                    System.arraycopy(encodedBand, 0, band, specifierEncoded.length, encodedBand.length);
                    return band;
                }
                if (!defaultCodec.isSigned() && first >= defaultCodec.getL() && first <= defaultCodec.getL() + 255) {
                    int specifier = CodecEncoding.getSpecifierForDefaultCodec(defaultCodec) + defaultCodec.getL();
                    byte[] specifierEncoded = defaultCodec.encode(new int[]{specifier});
                    byte[] band = new byte[specifierEncoded.length + encodedBand.length];
                    System.arraycopy(specifierEncoded, 0, band, 0, specifierEncoded.length);
                    System.arraycopy(encodedBand, 0, band, specifierEncoded.length, encodedBand.length);
                    return band;
                }
            }
            return encodedBand;
        }
        return new byte[0];
    }

    protected byte[] encodeFlags(String name, long[] flags, BHSDCodec loCodec, BHSDCodec hiCodec, boolean haveHiFlags) throws Pack200Exception {
        if (!haveHiFlags) {
            int[] loBits = new int[flags.length];
            Arrays.setAll(loBits, i2 -> (int)flags[i2]);
            return this.encodeBandInt(name, loBits, loCodec);
        }
        int[] hiBits = new int[flags.length];
        int[] loBits = new int[flags.length];
        for (int i3 = 0; i3 < flags.length; ++i3) {
            long l2 = flags[i3];
            hiBits[i3] = (int)(l2 >> 32);
            loBits[i3] = (int)l2;
        }
        byte[] hi = this.encodeBandInt(name, hiBits, hiCodec);
        byte[] lo = this.encodeBandInt(name, loBits, loCodec);
        byte[] total = new byte[hi.length + lo.length];
        System.arraycopy(hi, 0, total, 0, hi.length);
        System.arraycopy(lo, 0, total, hi.length + 1, lo.length);
        return total;
    }

    protected byte[] encodeFlags(String name, long[][] flags, BHSDCodec loCodec, BHSDCodec hiCodec, boolean haveHiFlags) throws Pack200Exception {
        return this.encodeFlags(name, this.flatten(flags), loCodec, hiCodec, haveHiFlags);
    }

    public byte[] encodeScalar(int value, BHSDCodec codec) throws Pack200Exception {
        return codec.encode(value);
    }

    public byte[] encodeScalar(int[] band, BHSDCodec codec) throws Pack200Exception {
        return codec.encode(band);
    }

    private void encodeWithPopulationCodec(String name, int[] band, BHSDCodec defaultCodec, BandData bandData, BandAnalysisResults results) throws Pack200Exception {
        byte[] tokensEncoded;
        results.numCodecsTried += 3;
        Map distinctValues = bandData.distinctValues;
        ArrayList<Integer> favored = new ArrayList<Integer>();
        distinctValues.forEach((k2, v2) -> {
            if (v2 > 2 || distinctValues.size() < 256) {
                favored.add((Integer)k2);
            }
        });
        if (distinctValues.size() > 255) {
            favored.sort((arg0, arg1) -> ((Integer)distinctValues.get(arg1)).compareTo((Integer)distinctValues.get(arg0)));
        }
        HashMap<Integer, Integer> favoredToIndex = new HashMap<Integer, Integer>();
        for (int i2 = 0; i2 < favored.size(); ++i2) {
            favoredToIndex.put((Integer)favored.get(i2), i2);
        }
        IntList unfavoured = new IntList();
        int[] tokens = new int[band.length];
        for (int i3 = 0; i3 < band.length; ++i3) {
            Integer favouredIndex = (Integer)favoredToIndex.get(band[i3]);
            if (favouredIndex == null) {
                tokens[i3] = 0;
                unfavoured.add(band[i3]);
                continue;
            }
            tokens[i3] = favouredIndex + 1;
        }
        favored.add((Integer)favored.get(favored.size() - 1));
        int[] favouredBand = this.integerListToArray(favored);
        int[] unfavouredBand = unfavoured.toArray();
        BandAnalysisResults favouredResults = this.analyseBand("POPULATION", favouredBand, defaultCodec);
        BandAnalysisResults unfavouredResults = this.analyseBand("POPULATION", unfavouredBand, defaultCodec);
        int tdefL = 0;
        int l2 = 0;
        Codec tokenCodec = null;
        int k3 = favored.size() - 1;
        if (k3 < 256) {
            tdefL = 1;
            tokensEncoded = Codec.BYTE1.encode(tokens);
        } else {
            boolean d2;
            BandAnalysisResults tokenResults = this.analyseBand("POPULATION", tokens, defaultCodec);
            tokenCodec = tokenResults.betterCodec;
            tokensEncoded = tokenResults.encodedBand;
            if (tokenCodec == null) {
                tokenCodec = defaultCodec;
            }
            l2 = ((BHSDCodec)tokenCodec).getL();
            int h2 = ((BHSDCodec)tokenCodec).getH();
            int s2 = ((BHSDCodec)tokenCodec).getS();
            int b2 = ((BHSDCodec)tokenCodec).getB();
            boolean bl = d2 = ((BHSDCodec)tokenCodec).isDelta();
            if (s2 == 0 && !d2) {
                BHSDCodec oneLowerB;
                boolean canUseTDefL = true;
                if (b2 > 1 && (oneLowerB = new BHSDCodec(b2 - 1, h2)).largest() >= (long)k3) {
                    canUseTDefL = false;
                }
                if (canUseTDefL) {
                    switch (l2) {
                        case 4: {
                            tdefL = 1;
                            break;
                        }
                        case 8: {
                            tdefL = 2;
                            break;
                        }
                        case 16: {
                            tdefL = 3;
                            break;
                        }
                        case 32: {
                            tdefL = 4;
                            break;
                        }
                        case 64: {
                            tdefL = 5;
                            break;
                        }
                        case 128: {
                            tdefL = 6;
                            break;
                        }
                        case 192: {
                            tdefL = 7;
                            break;
                        }
                        case 224: {
                            tdefL = 8;
                            break;
                        }
                        case 240: {
                            tdefL = 9;
                            break;
                        }
                        case 248: {
                            tdefL = 10;
                            break;
                        }
                        case 252: {
                            tdefL = 11;
                        }
                    }
                }
            }
        }
        byte[] favouredEncoded = favouredResults.encodedBand;
        byte[] unfavouredEncoded = unfavouredResults.encodedBand;
        Codec favouredCodec = favouredResults.betterCodec;
        Codec unfavouredCodec = unfavouredResults.betterCodec;
        int specifier = 141 + (favouredCodec == null ? 1 : 0) + 4 * tdefL + (unfavouredCodec == null ? 2 : 0);
        IntList extraBandMetadata = new IntList(3);
        if (favouredCodec != null) {
            IntStream.of(CodecEncoding.getSpecifier(favouredCodec, null)).forEach(extraBandMetadata::add);
        }
        if (tdefL == 0) {
            IntStream.of(CodecEncoding.getSpecifier(tokenCodec, null)).forEach(extraBandMetadata::add);
        }
        if (unfavouredCodec != null) {
            IntStream.of(CodecEncoding.getSpecifier(unfavouredCodec, null)).forEach(extraBandMetadata::add);
        }
        int[] extraMetadata = extraBandMetadata.toArray();
        byte[] extraMetadataEncoded = Codec.UNSIGNED5.encode(extraMetadata);
        specifier = defaultCodec.isSigned() ? -1 - specifier : (specifier += defaultCodec.getL());
        byte[] firstValueEncoded = defaultCodec.encode(new int[]{specifier});
        int totalBandLength = firstValueEncoded.length + favouredEncoded.length + tokensEncoded.length + unfavouredEncoded.length;
        if (totalBandLength + extraMetadataEncoded.length < results.encodedBand.length) {
            results.saved += results.encodedBand.length - (totalBandLength + extraMetadataEncoded.length);
            byte[] encodedBand = new byte[totalBandLength];
            System.arraycopy(firstValueEncoded, 0, encodedBand, 0, firstValueEncoded.length);
            System.arraycopy(favouredEncoded, 0, encodedBand, firstValueEncoded.length, favouredEncoded.length);
            System.arraycopy(tokensEncoded, 0, encodedBand, firstValueEncoded.length + favouredEncoded.length, tokensEncoded.length);
            System.arraycopy(unfavouredEncoded, 0, encodedBand, firstValueEncoded.length + favouredEncoded.length + tokensEncoded.length, unfavouredEncoded.length);
            BandAnalysisResults.access$002(results, encodedBand);
            BandAnalysisResults.access$302(results, extraMetadata);
            if (l2 != 0) {
                results.betterCodec = new PopulationCodec(favouredCodec, l2, unfavouredCodec);
            } else {
                results.betterCodec = new PopulationCodec(favouredCodec, tokenCodec, unfavouredCodec);
            }
        }
    }

    private long[] flatten(long[][] flags) {
        int totalSize = 0;
        for (long[] flag : flags) {
            totalSize += flag.length;
        }
        long[] flatArray = new long[totalSize];
        int index = 0;
        long[][] lArray = flags;
        int n2 = lArray.length;
        for (int i2 = 0; i2 < n2; ++i2) {
            long[] flag;
            long[] lArray2 = flag = lArray[i2];
            int n3 = lArray2.length;
            for (int i3 = 0; i3 < n3; ++i3) {
                long element;
                flatArray[index] = element = lArray2[i3];
                ++index;
            }
        }
        return flatArray;
    }

    protected int[] integerListToArray(List<Integer> integerList) {
        return integerList.stream().mapToInt(Integer::intValue).toArray();
    }

    protected long[] longListToArray(List<Long> longList) {
        return longList.stream().mapToLong(Long::longValue).toArray();
    }

    public abstract void pack(OutputStream var1) throws IOException, Pack200Exception;

    private boolean timeToStop(BandAnalysisResults results) {
        if (this.effort > 6) {
            return results.numCodecsTried >= this.effort * 2;
        }
        return results.numCodecsTried >= this.effort;
    }

    private void tryCodecs(String name, int[] band, BHSDCodec defaultCodec, BandData bandData, BandAnalysisResults results, byte[] encoded, BHSDCodec[] potentialCodecs) throws Pack200Exception {
        for (BHSDCodec potential : potentialCodecs) {
            int saved;
            byte[] specifierEncoded;
            byte[] encoded2;
            if (potential.equals(defaultCodec)) {
                return;
            }
            if (potential.isDelta()) {
                if (potential.largest() >= (long)bandData.largestDelta && potential.smallest() <= (long)bandData.smallestDelta && potential.largest() >= (long)bandData.largest && potential.smallest() <= (long)bandData.smallest) {
                    encoded2 = potential.encode(band);
                    results.numCodecsTried++;
                    specifierEncoded = defaultCodec.encode(CodecEncoding.getSpecifier(potential, null));
                    saved = encoded.length - encoded2.length - specifierEncoded.length;
                    if (saved > results.saved) {
                        results.betterCodec = potential;
                        BandAnalysisResults.access$002(results, encoded2);
                        results.saved = saved;
                    }
                }
            } else if (potential.largest() >= (long)bandData.largest && potential.smallest() <= (long)bandData.smallest) {
                encoded2 = potential.encode(band);
                results.numCodecsTried++;
                specifierEncoded = defaultCodec.encode(CodecEncoding.getSpecifier(potential, null));
                saved = encoded.length - encoded2.length - specifierEncoded.length;
                if (saved > results.saved) {
                    results.betterCodec = potential;
                    BandAnalysisResults.access$002(results, encoded2);
                    results.saved = saved;
                }
            }
            if (!this.timeToStop(results)) continue;
            return;
        }
    }

    public class BandAnalysisResults {
        private int numCodecsTried = 0;
        private int saved = 0;
        private int[] extraMetadata;
        private byte[] encodedBand;
        private Codec betterCodec;

        static /* synthetic */ byte[] access$002(BandAnalysisResults x0, byte[] x1) {
            x0.encodedBand = x1;
            return x1;
        }

        static /* synthetic */ int[] access$302(BandAnalysisResults x0, int[] x1) {
            x0.extraMetadata = x1;
            return x1;
        }
    }

    public class BandData {
        private final int[] band;
        private int smallest = Integer.MAX_VALUE;
        private int largest = Integer.MIN_VALUE;
        private int smallestDelta;
        private int largestDelta;
        private int deltaIsAscending = 0;
        private int smallDeltaCount = 0;
        private double averageAbsoluteDelta = 0.0;
        private double averageAbsoluteValue = 0.0;
        private Map<Integer, Integer> distinctValues;

        public BandData(int[] band) {
            this.band = band;
            Integer one = 1;
            for (int i2 = 0; i2 < band.length; ++i2) {
                Integer value;
                Integer count;
                if (band[i2] < this.smallest) {
                    this.smallest = band[i2];
                }
                if (band[i2] > this.largest) {
                    this.largest = band[i2];
                }
                if (i2 != 0) {
                    int delta = band[i2] - band[i2 - 1];
                    if (delta < this.smallestDelta) {
                        this.smallestDelta = delta;
                    }
                    if (delta > this.largestDelta) {
                        this.largestDelta = delta;
                    }
                    if (delta >= 0) {
                        ++this.deltaIsAscending;
                    }
                    this.averageAbsoluteDelta += (double)Math.abs(delta) / (double)(band.length - 1);
                    if (Math.abs(delta) < 256) {
                        ++this.smallDeltaCount;
                    }
                } else {
                    this.smallestDelta = band[0];
                    this.largestDelta = band[0];
                }
                this.averageAbsoluteValue += (double)Math.abs(band[i2]) / (double)band.length;
                if (BandSet.this.effort <= 3) continue;
                if (this.distinctValues == null) {
                    this.distinctValues = new HashMap<Integer, Integer>();
                }
                count = (count = this.distinctValues.get(value = Integer.valueOf(band[i2]))) == null ? one : Integer.valueOf(count + 1);
                this.distinctValues.put(value, count);
            }
        }

        public boolean anyNegatives() {
            return this.smallest < 0;
        }

        public boolean mainlyPositiveDeltas() {
            return (float)this.deltaIsAscending / (float)this.band.length > 0.95f;
        }

        public boolean mainlySmallDeltas() {
            return (float)this.smallDeltaCount / (float)this.band.length > 0.7f;
        }

        public int numDistinctValues() {
            if (this.distinctValues == null) {
                return this.band.length;
            }
            return this.distinctValues.size();
        }

        public boolean wellCorrelated() {
            return this.averageAbsoluteDelta * 3.1 < this.averageAbsoluteValue;
        }
    }
}

