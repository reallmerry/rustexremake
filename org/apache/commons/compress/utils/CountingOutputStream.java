/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.utils;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CountingOutputStream
extends FilterOutputStream {
    private long bytesWritten;

    public CountingOutputStream(OutputStream out) {
        super(out);
    }

    protected void count(long written) {
        if (written != -1L) {
            this.bytesWritten += written;
        }
    }

    public long getBytesWritten() {
        return this.bytesWritten;
    }

    @Override
    public void write(byte[] b2) throws IOException {
        this.write(b2, 0, b2.length);
    }

    @Override
    public void write(byte[] b2, int off, int len) throws IOException {
        this.out.write(b2, off, len);
        this.count(len);
    }

    @Override
    public void write(int b2) throws IOException {
        this.out.write(b2);
        this.count(1L);
    }
}

