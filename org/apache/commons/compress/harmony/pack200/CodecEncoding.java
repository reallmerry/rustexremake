/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.harmony.pack200;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.compress.harmony.pack200.BHSDCodec;
import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;
import org.apache.commons.compress.harmony.pack200.PopulationCodec;
import org.apache.commons.compress.harmony.pack200.RunCodec;

public class CodecEncoding {
    private static final BHSDCodec[] canonicalCodec = new BHSDCodec[]{null, new BHSDCodec(1, 256), new BHSDCodec(1, 256, 1), new BHSDCodec(1, 256, 0, 1), new BHSDCodec(1, 256, 1, 1), new BHSDCodec(2, 256), new BHSDCodec(2, 256, 1), new BHSDCodec(2, 256, 0, 1), new BHSDCodec(2, 256, 1, 1), new BHSDCodec(3, 256), new BHSDCodec(3, 256, 1), new BHSDCodec(3, 256, 0, 1), new BHSDCodec(3, 256, 1, 1), new BHSDCodec(4, 256), new BHSDCodec(4, 256, 1), new BHSDCodec(4, 256, 0, 1), new BHSDCodec(4, 256, 1, 1), new BHSDCodec(5, 4), new BHSDCodec(5, 4, 1), new BHSDCodec(5, 4, 2), new BHSDCodec(5, 16), new BHSDCodec(5, 16, 1), new BHSDCodec(5, 16, 2), new BHSDCodec(5, 32), new BHSDCodec(5, 32, 1), new BHSDCodec(5, 32, 2), new BHSDCodec(5, 64), new BHSDCodec(5, 64, 1), new BHSDCodec(5, 64, 2), new BHSDCodec(5, 128), new BHSDCodec(5, 128, 1), new BHSDCodec(5, 128, 2), new BHSDCodec(5, 4, 0, 1), new BHSDCodec(5, 4, 1, 1), new BHSDCodec(5, 4, 2, 1), new BHSDCodec(5, 16, 0, 1), new BHSDCodec(5, 16, 1, 1), new BHSDCodec(5, 16, 2, 1), new BHSDCodec(5, 32, 0, 1), new BHSDCodec(5, 32, 1, 1), new BHSDCodec(5, 32, 2, 1), new BHSDCodec(5, 64, 0, 1), new BHSDCodec(5, 64, 1, 1), new BHSDCodec(5, 64, 2, 1), new BHSDCodec(5, 128, 0, 1), new BHSDCodec(5, 128, 1, 1), new BHSDCodec(5, 128, 2, 1), new BHSDCodec(2, 192), new BHSDCodec(2, 224), new BHSDCodec(2, 240), new BHSDCodec(2, 248), new BHSDCodec(2, 252), new BHSDCodec(2, 8, 0, 1), new BHSDCodec(2, 8, 1, 1), new BHSDCodec(2, 16, 0, 1), new BHSDCodec(2, 16, 1, 1), new BHSDCodec(2, 32, 0, 1), new BHSDCodec(2, 32, 1, 1), new BHSDCodec(2, 64, 0, 1), new BHSDCodec(2, 64, 1, 1), new BHSDCodec(2, 128, 0, 1), new BHSDCodec(2, 128, 1, 1), new BHSDCodec(2, 192, 0, 1), new BHSDCodec(2, 192, 1, 1), new BHSDCodec(2, 224, 0, 1), new BHSDCodec(2, 224, 1, 1), new BHSDCodec(2, 240, 0, 1), new BHSDCodec(2, 240, 1, 1), new BHSDCodec(2, 248, 0, 1), new BHSDCodec(2, 248, 1, 1), new BHSDCodec(3, 192), new BHSDCodec(3, 224), new BHSDCodec(3, 240), new BHSDCodec(3, 248), new BHSDCodec(3, 252), new BHSDCodec(3, 8, 0, 1), new BHSDCodec(3, 8, 1, 1), new BHSDCodec(3, 16, 0, 1), new BHSDCodec(3, 16, 1, 1), new BHSDCodec(3, 32, 0, 1), new BHSDCodec(3, 32, 1, 1), new BHSDCodec(3, 64, 0, 1), new BHSDCodec(3, 64, 1, 1), new BHSDCodec(3, 128, 0, 1), new BHSDCodec(3, 128, 1, 1), new BHSDCodec(3, 192, 0, 1), new BHSDCodec(3, 192, 1, 1), new BHSDCodec(3, 224, 0, 1), new BHSDCodec(3, 224, 1, 1), new BHSDCodec(3, 240, 0, 1), new BHSDCodec(3, 240, 1, 1), new BHSDCodec(3, 248, 0, 1), new BHSDCodec(3, 248, 1, 1), new BHSDCodec(4, 192), new BHSDCodec(4, 224), new BHSDCodec(4, 240), new BHSDCodec(4, 248), new BHSDCodec(4, 252), new BHSDCodec(4, 8, 0, 1), new BHSDCodec(4, 8, 1, 1), new BHSDCodec(4, 16, 0, 1), new BHSDCodec(4, 16, 1, 1), new BHSDCodec(4, 32, 0, 1), new BHSDCodec(4, 32, 1, 1), new BHSDCodec(4, 64, 0, 1), new BHSDCodec(4, 64, 1, 1), new BHSDCodec(4, 128, 0, 1), new BHSDCodec(4, 128, 1, 1), new BHSDCodec(4, 192, 0, 1), new BHSDCodec(4, 192, 1, 1), new BHSDCodec(4, 224, 0, 1), new BHSDCodec(4, 224, 1, 1), new BHSDCodec(4, 240, 0, 1), new BHSDCodec(4, 240, 1, 1), new BHSDCodec(4, 248, 0, 1), new BHSDCodec(4, 248, 1, 1)};
    private static Map<BHSDCodec, Integer> canonicalCodecsToSpecifiers;

    public static BHSDCodec getCanonicalCodec(int i2) {
        return canonicalCodec[i2];
    }

    public static Codec getCodec(int value, InputStream in, Codec defaultCodec) throws IOException, Pack200Exception {
        if (canonicalCodec.length != 116) {
            throw new Error("Canonical encodings have been incorrectly modified");
        }
        if (value < 0) {
            throw new IllegalArgumentException("Encoding cannot be less than zero");
        }
        if (value == 0) {
            return defaultCodec;
        }
        if (value <= 115) {
            return canonicalCodec[value];
        }
        if (value == 116) {
            int code = in.read();
            if (code == -1) {
                throw new EOFException("End of buffer read whilst trying to decode codec");
            }
            int d2 = code & 1;
            int s2 = code >> 1 & 3;
            int b2 = (code >> 3 & 7) + 1;
            code = in.read();
            if (code == -1) {
                throw new EOFException("End of buffer read whilst trying to decode codec");
            }
            int h2 = code + 1;
            return new BHSDCodec(b2, h2, s2, d2);
        }
        if (value >= 117 && value <= 140) {
            boolean bdef;
            int offset = value - 117;
            int kx = offset & 3;
            boolean kbflag = (offset >> 2 & 1) == 1;
            boolean adef = (offset >> 3 & 1) == 1;
            boolean bl = bdef = (offset >> 4 & 1) == 1;
            if (adef && bdef) {
                throw new Pack200Exception("ADef and BDef should never both be true");
            }
            int kb = kbflag ? in.read() : 3;
            int k2 = (kb + 1) * (int)Math.pow(16.0, kx);
            Codec aCodec = adef ? defaultCodec : CodecEncoding.getCodec(in.read(), in, defaultCodec);
            Codec bCodec = bdef ? defaultCodec : CodecEncoding.getCodec(in.read(), in, defaultCodec);
            return new RunCodec(k2, aCodec, bCodec);
        }
        if (value < 141 || value > 188) {
            throw new Pack200Exception("Invalid codec encoding byte (" + value + ") found");
        }
        int offset = value - 141;
        boolean fdef = (offset & 1) == 1;
        boolean udef = (offset >> 1 & 1) == 1;
        int tdefl = offset >> 2;
        boolean tdef = tdefl != 0;
        int[] tdefToL = new int[]{0, 4, 8, 16, 32, 64, 128, 192, 224, 240, 248, 252};
        int l2 = tdefToL[tdefl];
        if (tdef) {
            Codec fCodec = fdef ? defaultCodec : CodecEncoding.getCodec(in.read(), in, defaultCodec);
            Codec uCodec = udef ? defaultCodec : CodecEncoding.getCodec(in.read(), in, defaultCodec);
            return new PopulationCodec(fCodec, l2, uCodec);
        }
        Codec fCodec = fdef ? defaultCodec : CodecEncoding.getCodec(in.read(), in, defaultCodec);
        Codec tCodec = CodecEncoding.getCodec(in.read(), in, defaultCodec);
        Codec uCodec = udef ? defaultCodec : CodecEncoding.getCodec(in.read(), in, defaultCodec);
        return new PopulationCodec(fCodec, tCodec, uCodec);
    }

    public static int[] getSpecifier(Codec codec, Codec defaultForBand) {
        if (canonicalCodecsToSpecifiers == null) {
            HashMap<BHSDCodec, Integer> reverseMap = new HashMap<BHSDCodec, Integer>(canonicalCodec.length);
            for (int i2 = 0; i2 < canonicalCodec.length; ++i2) {
                reverseMap.put(canonicalCodec[i2], i2);
            }
            canonicalCodecsToSpecifiers = reverseMap;
        }
        if (canonicalCodecsToSpecifiers.containsKey(codec)) {
            return new int[]{canonicalCodecsToSpecifiers.get(codec)};
        }
        if (codec instanceof BHSDCodec) {
            BHSDCodec bhsdCodec = (BHSDCodec)codec;
            int[] specifiers = new int[]{116, (bhsdCodec.isDelta() ? 1 : 0) + 2 * bhsdCodec.getS() + 8 * (bhsdCodec.getB() - 1), bhsdCodec.getH() - 1};
            return specifiers;
        }
        if (codec instanceof RunCodec) {
            int element;
            int n2;
            int kx;
            int kb;
            RunCodec runCodec = (RunCodec)codec;
            int k2 = runCodec.getK();
            if (k2 <= 256) {
                kb = 0;
                kx = k2 - 1;
            } else if (k2 <= 4096) {
                kb = 1;
                kx = k2 / 16 - 1;
            } else if (k2 <= 65536) {
                kb = 2;
                kx = k2 / 256 - 1;
            } else {
                kb = 3;
                kx = k2 / 4096 - 1;
            }
            Codec aCodec = runCodec.getACodec();
            Codec bCodec = runCodec.getBCodec();
            int abDef = 0;
            if (aCodec.equals(defaultForBand)) {
                abDef = 1;
            } else if (bCodec.equals(defaultForBand)) {
                abDef = 2;
            }
            int first = 117 + kb + (kx == 3 ? 0 : 4) + 8 * abDef;
            int[] aSpecifier = abDef == 1 ? new int[]{} : CodecEncoding.getSpecifier(aCodec, defaultForBand);
            int[] bSpecifier = abDef == 2 ? new int[]{} : CodecEncoding.getSpecifier(bCodec, defaultForBand);
            int[] specifier = new int[1 + (kx == 3 ? 0 : 1) + aSpecifier.length + bSpecifier.length];
            specifier[0] = first;
            int index = 1;
            if (kx != 3) {
                specifier[1] = kx;
                ++index;
            }
            int[] nArray = aSpecifier;
            int n3 = nArray.length;
            for (n2 = 0; n2 < n3; ++n2) {
                specifier[index] = element = nArray[n2];
                ++index;
            }
            nArray = bSpecifier;
            n3 = nArray.length;
            for (n2 = 0; n2 < n3; ++n2) {
                specifier[index] = element = nArray[n2];
                ++index;
            }
            return specifier;
        }
        if (codec instanceof PopulationCodec) {
            int element;
            int n4;
            PopulationCodec populationCodec = (PopulationCodec)codec;
            Codec tokenCodec = populationCodec.getTokenCodec();
            Codec favouredCodec = populationCodec.getFavouredCodec();
            Codec unfavouredCodec = populationCodec.getUnfavouredCodec();
            int fDef = favouredCodec.equals(defaultForBand) ? 1 : 0;
            int uDef = unfavouredCodec.equals(defaultForBand) ? 1 : 0;
            int tDefL = 0;
            int[] favoured = populationCodec.getFavoured();
            if (favoured != null) {
                int l2;
                int[] possibleLValues;
                int index;
                BHSDCodec tokenBHSD;
                if (tokenCodec == Codec.BYTE1) {
                    tDefL = 1;
                } else if (tokenCodec instanceof BHSDCodec && (tokenBHSD = (BHSDCodec)tokenCodec).getS() == 0 && (index = Arrays.binarySearch(possibleLValues = new int[]{4, 8, 16, 32, 64, 128, 192, 224, 240, 248, 252}, l2 = 256 - tokenBHSD.getH())) != -1) {
                    tDefL = index++;
                }
            }
            int first = 141 + fDef + 2 * uDef + 4 * tDefL;
            int[] favouredSpecifier = fDef == 1 ? new int[]{} : CodecEncoding.getSpecifier(favouredCodec, defaultForBand);
            int[] tokenSpecifier = tDefL != 0 ? new int[]{} : CodecEncoding.getSpecifier(tokenCodec, defaultForBand);
            int[] unfavouredSpecifier = uDef == 1 ? new int[]{} : CodecEncoding.getSpecifier(unfavouredCodec, defaultForBand);
            int[] specifier = new int[1 + favouredSpecifier.length + unfavouredSpecifier.length + tokenSpecifier.length];
            specifier[0] = first;
            int index = 1;
            int[] nArray = favouredSpecifier;
            int n5 = nArray.length;
            for (n4 = 0; n4 < n5; ++n4) {
                specifier[index] = element = nArray[n4];
                ++index;
            }
            nArray = tokenSpecifier;
            n5 = nArray.length;
            for (n4 = 0; n4 < n5; ++n4) {
                specifier[index] = element = nArray[n4];
                ++index;
            }
            nArray = unfavouredSpecifier;
            n5 = nArray.length;
            for (n4 = 0; n4 < n5; ++n4) {
                specifier[index] = element = nArray[n4];
                ++index;
            }
            return specifier;
        }
        return null;
    }

    public static int getSpecifierForDefaultCodec(BHSDCodec defaultCodec) {
        return CodecEncoding.getSpecifier(defaultCodec, null)[0];
    }
}

