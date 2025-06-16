/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.utils;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CountingInputStream
extends FilterInputStream {
    private long bytesRead;

    public CountingInputStream(InputStream in) {
        super(in);
    }

    protected final void count(long read) {
        if (read != -1L) {
            this.bytesRead += read;
        }
    }

    public long getBytesRead() {
        return this.bytesRead;
    }

    @Override
    public int read() throws IOException {
        int r2 = this.in.read();
        if (r2 >= 0) {
            this.count(1L);
        }
        return r2;
    }

    @Override
    public int read(byte[] b2) throws IOException {
        return this.read(b2, 0, b2.length);
    }

    @Override
    public int read(byte[] b2, int off, int len) throws IOException {
        if (len == 0) {
            return 0;
        }
        int r2 = this.in.read(b2, off, len);
        if (r2 >= 0) {
            this.count(r2);
        }
        return r2;
    }
}

