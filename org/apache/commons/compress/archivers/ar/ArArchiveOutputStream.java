/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.ar;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ar.ArArchiveEntry;
import org.apache.commons.compress.utils.ArchiveUtils;

public class ArArchiveOutputStream
extends ArchiveOutputStream {
    public static final int LONGFILE_ERROR = 0;
    public static final int LONGFILE_BSD = 1;
    private final OutputStream out;
    private long entryOffset;
    private ArArchiveEntry prevEntry;
    private boolean haveUnclosedEntry;
    private int longFileMode = 0;
    private boolean finished;

    public ArArchiveOutputStream(OutputStream pOut) {
        this.out = pOut;
    }

    @Override
    public void close() throws IOException {
        try {
            if (!this.finished) {
                this.finish();
            }
        } finally {
            this.out.close();
            this.prevEntry = null;
        }
    }

    @Override
    public void closeArchiveEntry() throws IOException {
        if (this.finished) {
            throw new IOException("Stream has already been finished");
        }
        if (this.prevEntry == null || !this.haveUnclosedEntry) {
            throw new IOException("No current entry to close");
        }
        if (this.entryOffset % 2L != 0L) {
            this.out.write(10);
        }
        this.haveUnclosedEntry = false;
    }

    @Override
    public ArchiveEntry createArchiveEntry(File inputFile, String entryName) throws IOException {
        if (this.finished) {
            throw new IOException("Stream has already been finished");
        }
        return new ArArchiveEntry(inputFile, entryName);
    }

    @Override
    public ArchiveEntry createArchiveEntry(Path inputPath, String entryName, LinkOption ... options) throws IOException {
        if (this.finished) {
            throw new IOException("Stream has already been finished");
        }
        return new ArArchiveEntry(inputPath, entryName, options);
    }

    private long fill(long pOffset, long pNewOffset, char pFill) throws IOException {
        long diff = pNewOffset - pOffset;
        if (diff > 0L) {
            int i2 = 0;
            while ((long)i2 < diff) {
                this.write(pFill);
                ++i2;
            }
        }
        return pNewOffset;
    }

    @Override
    public void finish() throws IOException {
        if (this.haveUnclosedEntry) {
            throw new IOException("This archive contains unclosed entries.");
        }
        if (this.finished) {
            throw new IOException("This archive has already been finished");
        }
        this.finished = true;
    }

    @Override
    public void putArchiveEntry(ArchiveEntry pEntry) throws IOException {
        if (this.finished) {
            throw new IOException("Stream has already been finished");
        }
        ArArchiveEntry pArEntry = (ArArchiveEntry)pEntry;
        if (this.prevEntry == null) {
            this.writeArchiveHeader();
        } else {
            if (this.prevEntry.getLength() != this.entryOffset) {
                throw new IOException("Length does not match entry (" + this.prevEntry.getLength() + " != " + this.entryOffset);
            }
            if (this.haveUnclosedEntry) {
                this.closeArchiveEntry();
            }
        }
        this.prevEntry = pArEntry;
        this.writeEntryHeader(pArEntry);
        this.entryOffset = 0L;
        this.haveUnclosedEntry = true;
    }

    public void setLongFileMode(int longFileMode) {
        this.longFileMode = longFileMode;
    }

    @Override
    public void write(byte[] b2, int off, int len) throws IOException {
        this.out.write(b2, off, len);
        this.count(len);
        this.entryOffset += (long)len;
    }

    private long write(String data) throws IOException {
        byte[] bytes = data.getBytes(StandardCharsets.US_ASCII);
        this.write(bytes);
        return bytes.length;
    }

    private void writeArchiveHeader() throws IOException {
        byte[] header = ArchiveUtils.toAsciiBytes("!<arch>\n");
        this.out.write(header);
    }

    private void writeEntryHeader(ArArchiveEntry pEntry) throws IOException {
        long offset = 0L;
        boolean mustAppendName = false;
        String n2 = pEntry.getName();
        int nLength = n2.length();
        if (0 == this.longFileMode && nLength > 16) {
            throw new IOException("File name too long, > 16 chars: " + n2);
        }
        if (1 == this.longFileMode && (nLength > 16 || n2.contains(" "))) {
            mustAppendName = true;
            offset += this.write("#1/" + nLength);
        } else {
            offset += this.write(n2);
        }
        offset = this.fill(offset, 16L, ' ');
        String m2 = "" + pEntry.getLastModified();
        if (m2.length() > 12) {
            throw new IOException("Last modified too long");
        }
        offset += this.write(m2);
        offset = this.fill(offset, 28L, ' ');
        String u2 = "" + pEntry.getUserId();
        if (u2.length() > 6) {
            throw new IOException("User id too long");
        }
        offset += this.write(u2);
        offset = this.fill(offset, 34L, ' ');
        String g2 = "" + pEntry.getGroupId();
        if (g2.length() > 6) {
            throw new IOException("Group id too long");
        }
        offset += this.write(g2);
        offset = this.fill(offset, 40L, ' ');
        String fm = "" + Integer.toString(pEntry.getMode(), 8);
        if (fm.length() > 8) {
            throw new IOException("Filemode too long");
        }
        offset += this.write(fm);
        offset = this.fill(offset, 48L, ' ');
        String s2 = String.valueOf(pEntry.getLength() + (long)(mustAppendName ? nLength : 0));
        if (s2.length() > 10) {
            throw new IOException("Size too long");
        }
        offset += this.write(s2);
        offset = this.fill(offset, 58L, ' ');
        offset += this.write("`\n");
        if (mustAppendName) {
            offset += this.write(n2);
        }
    }
}

