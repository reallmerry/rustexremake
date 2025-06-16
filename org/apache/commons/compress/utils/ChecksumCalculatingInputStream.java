/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.utils;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.zip.Checksum;

public class ChecksumCalculatingInputStream
extends FilterInputStream {
    private final Checksum checksum;

    public ChecksumCalculatingInputStream(Checksum checksum, InputStream inputStream) {
        super(Objects.requireNonNull(inputStream, "inputStream"));
        this.checksum = Objects.requireNonNull(checksum, "checksum");
    }

    public long getValue() {
        return this.checksum.getValue();
    }

    @Override
    public int read() throws IOException {
        int ret = this.in.read();
        if (ret >= 0) {
            this.checksum.update(ret);
        }
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
        }
        return ret;
    }

    @Override
    public long skip(long n2) throws IOException {
        if (this.read() >= 0) {
            return 1L;
        }
        return 0L;
    }
}

