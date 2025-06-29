/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import org.apache.commons.compress.archivers.ArchiveEntry;

public abstract class ArchiveOutputStream
extends OutputStream {
    static final int BYTE_MASK = 255;
    private final byte[] oneByte = new byte[1];
    private long bytesWritten;

    public boolean canWriteEntryData(ArchiveEntry archiveEntry) {
        return true;
    }

    public abstract void closeArchiveEntry() throws IOException;

    protected void count(int written) {
        this.count((long)written);
    }

    protected void count(long written) {
        if (written != -1L) {
            this.bytesWritten += written;
        }
    }

    public abstract ArchiveEntry createArchiveEntry(File var1, String var2) throws IOException;

    public ArchiveEntry createArchiveEntry(Path inputPath, String entryName, LinkOption ... options) throws IOException {
        return this.createArchiveEntry(inputPath.toFile(), entryName);
    }

    public abstract void finish() throws IOException;

    public long getBytesWritten() {
        return this.bytesWritten;
    }

    @Deprecated
    public int getCount() {
        return (int)this.bytesWritten;
    }

    public abstract void putArchiveEntry(ArchiveEntry var1) throws IOException;

    @Override
    public void write(int b2) throws IOException {
        this.oneByte[0] = (byte)(b2 & 0xFF);
        this.write(this.oneByte, 0, 1);
    }
}

