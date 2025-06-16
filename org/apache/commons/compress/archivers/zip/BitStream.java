/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import org.apache.commons.compress.utils.BitInputStream;

class BitStream
extends BitInputStream {
    BitStream(InputStream in) {
        super(in, ByteOrder.LITTLE_ENDIAN);
    }

    int nextBit() throws IOException {
        return (int)this.readBits(1);
    }

    long nextBits(int n2) throws IOException {
        if (n2 < 0 || n2 > 8) {
            throw new IOException("Trying to read " + n2 + " bits, at most 8 are allowed");
        }
        return this.readBits(n2);
    }

    int nextByte() throws IOException {
        return (int)this.readBits(8);
    }
}

