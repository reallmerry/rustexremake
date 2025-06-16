/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 * 
 * Could not load the following classes:
 *  org.tukaani.xz.MemoryLimitException
 *  org.tukaani.xz.SingleXZInputStream
 *  org.tukaani.xz.XZ
 *  org.tukaani.xz.XZInputStream
 */
package org.apache.commons.compress.compressors.xz;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.MemoryLimitException;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.utils.CountingInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;
import org.tukaani.xz.SingleXZInputStream;
import org.tukaani.xz.XZ;
import org.tukaani.xz.XZInputStream;

public class XZCompressorInputStream
extends CompressorInputStream
implements InputStreamStatistics {
    private final CountingInputStream countingStream;
    private final InputStream in;

    public static boolean matches(byte[] signature, int length) {
        if (length < XZ.HEADER_MAGIC.length) {
            return false;
        }
        for (int i2 = 0; i2 < XZ.HEADER_MAGIC.length; ++i2) {
            if (signature[i2] == XZ.HEADER_MAGIC[i2]) continue;
            return false;
        }
        return true;
    }

    public XZCompressorInputStream(InputStream inputStream) throws IOException {
        this(inputStream, false);
    }

    public XZCompressorInputStream(InputStream inputStream, boolean decompressConcatenated) throws IOException {
        this(inputStream, decompressConcatenated, -1);
    }

    public XZCompressorInputStream(InputStream inputStream, boolean decompressConcatenated, int memoryLimitInKb) throws IOException {
        this.countingStream = new CountingInputStream(inputStream);
        this.in = decompressConcatenated ? new XZInputStream((InputStream)this.countingStream, memoryLimitInKb) : new SingleXZInputStream((InputStream)this.countingStream, memoryLimitInKb);
    }

    @Override
    public int available() throws IOException {
        return this.in.available();
    }

    @Override
    public void close() throws IOException {
        this.in.close();
    }

    @Override
    public long getCompressedCount() {
        return this.countingStream.getBytesRead();
    }

    @Override
    public int read() throws IOException {
        try {
            int ret = this.in.read();
            this.count(ret == -1 ? -1 : 1);
            return ret;
        } catch (org.tukaani.xz.MemoryLimitException e2) {
            throw new MemoryLimitException(e2.getMemoryNeeded(), e2.getMemoryLimit(), (Exception)((Object)e2));
        }
    }

    @Override
    public int read(byte[] buf, int off, int len) throws IOException {
        if (len == 0) {
            return 0;
        }
        try {
            int ret = this.in.read(buf, off, len);
            this.count(ret);
            return ret;
        } catch (org.tukaani.xz.MemoryLimitException e2) {
            throw new MemoryLimitException(e2.getMemoryNeeded(), e2.getMemoryLimit(), (Exception)((Object)e2));
        }
    }

    @Override
    public long skip(long n2) throws IOException {
        try {
            return IOUtils.skip(this.in, n2);
        } catch (org.tukaani.xz.MemoryLimitException e2) {
            throw new MemoryLimitException(e2.getMemoryNeeded(), e2.getMemoryLimit(), (Exception)((Object)e2));
        }
    }
}

