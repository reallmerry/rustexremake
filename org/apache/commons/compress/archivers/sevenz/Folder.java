/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.sevenz;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import org.apache.commons.compress.archivers.sevenz.BindPair;
import org.apache.commons.compress.archivers.sevenz.Coder;

class Folder {
    static final Folder[] EMPTY_FOLDER_ARRAY = new Folder[0];
    Coder[] coders;
    long totalInputStreams;
    long totalOutputStreams;
    BindPair[] bindPairs;
    long[] packedStreams;
    long[] unpackSizes;
    boolean hasCrc;
    long crc;
    int numUnpackSubStreams;

    Folder() {
    }

    int findBindPairForInStream(int index) {
        if (this.bindPairs != null) {
            for (int i2 = 0; i2 < this.bindPairs.length; ++i2) {
                if (this.bindPairs[i2].inIndex != (long)index) continue;
                return i2;
            }
        }
        return -1;
    }

    int findBindPairForOutStream(int index) {
        if (this.bindPairs != null) {
            for (int i2 = 0; i2 < this.bindPairs.length; ++i2) {
                if (this.bindPairs[i2].outIndex != (long)index) continue;
                return i2;
            }
        }
        return -1;
    }

    Iterable<Coder> getOrderedCoders() throws IOException {
        if (this.packedStreams == null || this.coders == null || this.packedStreams.length == 0 || this.coders.length == 0) {
            return Collections.emptyList();
        }
        LinkedList<Coder> l2 = new LinkedList<Coder>();
        int current = (int)this.packedStreams[0];
        while (current >= 0 && current < this.coders.length) {
            if (l2.contains(this.coders[current])) {
                throw new IOException("folder uses the same coder more than once in coder chain");
            }
            l2.addLast(this.coders[current]);
            int pair = this.findBindPairForOutStream(current);
            current = pair != -1 ? (int)this.bindPairs[pair].inIndex : -1;
        }
        return l2;
    }

    long getUnpackSize() {
        if (this.totalOutputStreams == 0L) {
            return 0L;
        }
        for (int i2 = (int)this.totalOutputStreams - 1; i2 >= 0; --i2) {
            if (this.findBindPairForOutStream(i2) >= 0) continue;
            return this.unpackSizes[i2];
        }
        return 0L;
    }

    long getUnpackSizeForCoder(Coder coder) {
        if (this.coders != null) {
            for (int i2 = 0; i2 < this.coders.length; ++i2) {
                if (this.coders[i2] != coder) continue;
                return this.unpackSizes[i2];
            }
        }
        return 0L;
    }

    public String toString() {
        return "Folder with " + this.coders.length + " coders, " + this.totalInputStreams + " input streams, " + this.totalOutputStreams + " output streams, " + this.bindPairs.length + " bind pairs, " + this.packedStreams.length + " packed streams, " + this.unpackSizes.length + " unpack sizes, " + (this.hasCrc ? "with CRC " + this.crc : "without CRC") + " and " + this.numUnpackSubStreams + " unpack streams";
    }
}

