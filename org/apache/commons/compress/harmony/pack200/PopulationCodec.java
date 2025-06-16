/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.harmony.pack200;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.apache.commons.compress.harmony.pack200.BHSDCodec;
import org.apache.commons.compress.harmony.pack200.Codec;
import org.apache.commons.compress.harmony.pack200.Pack200Exception;

public class PopulationCodec
extends Codec {
    private final Codec favouredCodec;
    private Codec tokenCodec;
    private final Codec unfavouredCodec;
    private int l;
    private int[] favoured;

    public PopulationCodec(Codec favouredCodec, Codec tokenCodec, Codec unvafouredCodec) {
        this.favouredCodec = favouredCodec;
        this.tokenCodec = tokenCodec;
        this.unfavouredCodec = unvafouredCodec;
    }

    public PopulationCodec(Codec favouredCodec, int l2, Codec unfavouredCodec) {
        if (l2 >= 256 || l2 <= 0) {
            throw new IllegalArgumentException("L must be between 1..255");
        }
        this.favouredCodec = favouredCodec;
        this.l = l2;
        this.unfavouredCodec = unfavouredCodec;
    }

    @Override
    public int decode(InputStream in) throws IOException, Pack200Exception {
        throw new Pack200Exception("Population encoding does not work unless the number of elements are known");
    }

    @Override
    public int decode(InputStream in, long last) throws IOException, Pack200Exception {
        throw new Pack200Exception("Population encoding does not work unless the number of elements are known");
    }

    @Override
    public int[] decodeInts(int n2, InputStream in) throws IOException, Pack200Exception {
        this.lastBandLength = 0;
        this.favoured = new int[n2];
        int smallest = Integer.MAX_VALUE;
        int last = 0;
        int value = 0;
        int k2 = -1;
        while (true) {
            int absoluteValue;
            value = this.favouredCodec.decode(in, last);
            if (k2 > -1 && (value == smallest || value == last)) break;
            this.favoured[++k2] = value;
            int absoluteSmallest = Math.abs(smallest);
            if (absoluteSmallest > (absoluteValue = Math.abs(value))) {
                smallest = value;
            } else if (absoluteSmallest == absoluteValue) {
                smallest = absoluteSmallest;
            }
            last = value;
        }
        this.lastBandLength += k2;
        if (this.tokenCodec == null) {
            if (k2 < 256) {
                this.tokenCodec = Codec.BYTE1;
            } else {
                int b2 = 1;
                while (++b2 < 5) {
                    BHSDCodec codec = new BHSDCodec(b2, 256 - this.l, 0);
                    if (!codec.encodes(k2)) continue;
                    this.tokenCodec = codec;
                    break;
                }
                if (this.tokenCodec == null) {
                    throw new Pack200Exception("Cannot calculate token codec from " + k2 + " and " + this.l);
                }
            }
        }
        this.lastBandLength += n2;
        int[] result = this.tokenCodec.decodeInts(n2, in);
        last = 0;
        for (int i2 = 0; i2 < n2; ++i2) {
            int index = result[i2];
            if (index == 0) {
                ++this.lastBandLength;
                result[i2] = last = this.unfavouredCodec.decode(in, last);
                continue;
            }
            result[i2] = this.favoured[index - 1];
        }
        return result;
    }

    @Override
    public byte[] encode(int value) throws Pack200Exception {
        throw new Pack200Exception("Population encoding does not work unless the number of elements are known");
    }

    @Override
    public byte[] encode(int value, int last) throws Pack200Exception {
        throw new Pack200Exception("Population encoding does not work unless the number of elements are known");
    }

    public byte[] encode(int[] favoured, int[] tokens, int[] unfavoured) throws Pack200Exception {
        int[] favoured2 = Arrays.copyOf(favoured, favoured.length + 1);
        favoured2[favoured2.length - 1] = favoured[favoured.length - 1];
        byte[] favouredEncoded = this.favouredCodec.encode(favoured2);
        byte[] tokensEncoded = this.tokenCodec.encode(tokens);
        byte[] unfavouredEncoded = this.unfavouredCodec.encode(unfavoured);
        byte[] band = new byte[favouredEncoded.length + tokensEncoded.length + unfavouredEncoded.length];
        System.arraycopy(favouredEncoded, 0, band, 0, favouredEncoded.length);
        System.arraycopy(tokensEncoded, 0, band, favouredEncoded.length, tokensEncoded.length);
        System.arraycopy(unfavouredEncoded, 0, band, favouredEncoded.length + tokensEncoded.length, unfavouredEncoded.length);
        return band;
    }

    public int[] getFavoured() {
        return this.favoured;
    }

    public Codec getFavouredCodec() {
        return this.favouredCodec;
    }

    public Codec getTokenCodec() {
        return this.tokenCodec;
    }

    public Codec getUnfavouredCodec() {
        return this.unfavouredCodec;
    }
}

