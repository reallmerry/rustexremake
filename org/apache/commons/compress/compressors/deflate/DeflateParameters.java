/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.deflate;

public class DeflateParameters {
    static final int MAX_LEVEL = 9;
    static final int MIN_LEVEL = 0;
    private boolean zlibHeader = true;
    private int compressionLevel = -1;

    public int getCompressionLevel() {
        return this.compressionLevel;
    }

    public void setCompressionLevel(int compressionLevel) {
        if (compressionLevel < 0 || compressionLevel > 9) {
            throw new IllegalArgumentException("Invalid Deflate compression level: " + compressionLevel);
        }
        this.compressionLevel = compressionLevel;
    }

    public void setWithZlibHeader(boolean zlibHeader) {
        this.zlibHeader = zlibHeader;
    }

    public boolean withZlibHeader() {
        return this.zlibHeader;
    }
}

