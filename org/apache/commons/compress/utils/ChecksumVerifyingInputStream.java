/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.utils;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Checksum;

public class ChecksumVerifyingInputStream
extends FilterInputStream {
    private long bytesRemaining;
    private final long expectedChecksum;
    private final Checksum checksum;

    public ChecksumVerifyingInputStream(Checksum checksum, InputStream in, long size, long expectedChecksum) {
        super(in);
        this.checksum = checksum;
        this.expectedChecksum = expectedChecksum;
        this.bytesRemaining = size;
    }

    public long getBytesRemaining() {
        return this.bytesRemaining;
    }

    @Override
    public int read() throws IOException {
        if (this.bytesRemaining <= 0L) {
            return -1;
        }
        int ret = this.in.read();
        if (ret >= 0) {
            this.checksum.update(ret);
            --this.bytesRemaining;
        }
        this.verify();
        return ret;
    }

    @Override
    public int read(byte[] b2, int off, int len) throws IOException {
        if (len == 0) {
            return 0;
        }
        int ret = this.in.read(b2, off, len);
        if (ret >= 0) {
            this.checksum.update(b2, off, ret);
            this.bytesRemaining -= (long)ret;
        }
        this.verify();
        return ret;
    }

    @Override
    public long skip(long n2) throws IOException {
        return this.read() >= 0 ? 1L : 0L;
    }

    private void verify() throws IOException {
        if (this.bytesRemaining <= 0L && this.expectedChecksum != this.checksum.getValue()) {
            throw new IOException("Checksum verification failed");
        }
    }
}

