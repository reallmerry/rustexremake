/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.archivers.ArchiveEntry;

public abstract class ArchiveInputStream
extends InputStream {
    private static final int BYTE_MASK = 255;
    private final byte[] single = new byte[1];
    private long bytesRead;

    public boolean canReadEntryData(ArchiveEntry archiveEntry) {
        return true;
    }

    protected void count(int read) {
        this.count((long)read);
    }

    protected void count(long read) {
        if (read != -1L) {
            this.bytesRead += read;
        }
    }

    public long getBytesRead() {
        return this.bytesRead;
    }

    @Deprecated
    public int getCount() {
        return (int)this.bytesRead;
    }

    public abstract ArchiveEntry getNextEntry() throws IOException;

    protected void pushedBackBytes(long pushedBack) {
        this.bytesRead -= pushedBack;
    }

    @Override
    public int read() throws IOException {
        int num = this.read(this.single, 0, 1);
        return num == -1 ? -1 : this.single[0] & 0xFF;
    }
}

