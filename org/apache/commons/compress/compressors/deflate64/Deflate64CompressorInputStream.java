/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.deflate64;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.deflate64.HuffmanDecoder;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;

public class Deflate64CompressorInputStream
extends CompressorInputStream
implements InputStreamStatistics {
    private InputStream originalStream;
    private HuffmanDecoder decoder;
    private long compressedBytesRead;
    private final byte[] oneByte = new byte[1];

    Deflate64CompressorInputStream(HuffmanDecoder decoder) {
        this.decoder = decoder;
    }

    public Deflate64CompressorInputStream(InputStream in) {
        this(new HuffmanDecoder(in));
        this.originalStream = in;
    }

    @Override
    public int available() throws IOException {
        return this.decoder != null ? this.decoder.available() : 0;
    }

    @Override
    public void close() throws IOException {
        try {
            this.closeDecoder();
        } finally {
            if (this.originalStream != null) {
                this.originalStream.close();
                this.originalStream = null;
            }
        }
    }

    private void closeDecoder() {
        IOUtils.closeQuietly(this.decoder);
        this.decoder = null;
    }

    @Override
    public long getCompressedCount() {
        return this.compressedBytesRead;
    }

    @Override
    public int read() throws IOException {
        int r2;
        block5: while (true) {
            r2 = this.read(this.oneByte);
            switch (r2) {
                case 1: {
                    return this.oneByte[0] & 0xFF;
                }
                case -1: {
                    return -1;
                }
                case 0: {
                    continue block5;
                }
            }
            break;
        }
        throw new IllegalStateException("Invalid return value from read: " + r2);
    }

    @Override
    public int read(byte[] b2, int off, int len) throws IOException {
        if (len == 0) {
            return 0;
        }
        int read = -1;
        if (this.decoder != null) {
            try {
                read = this.decoder.decode(b2, off, len);
            } catch (RuntimeException ex) {
                throw new IOException("Invalid Deflate64 input", ex);
            }
            this.compressedBytesRead = this.decoder.getBytesRead();
            this.count(read);
            if (read == -1) {
                this.closeDecoder();
            }
        }
        return read;
    }
}

