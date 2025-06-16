/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.tar;

import java.util.Objects;

public final class TarArchiveStructSparse {
    private final long offset;
    private final long numbytes;

    public TarArchiveStructSparse(long offset, long numbytes) {
        if (offset < 0L) {
            throw new IllegalArgumentException("offset must not be negative");
        }
        if (numbytes < 0L) {
            throw new IllegalArgumentException("numbytes must not be negative");
        }
        this.offset = offset;
        this.numbytes = numbytes;
    }

    public boolean equals(Object o2) {
        if (this == o2) {
            return true;
        }
        if (o2 == null || this.getClass() != o2.getClass()) {
            return false;
        }
        TarArchiveStructSparse that = (TarArchiveStructSparse)o2;
        return this.offset == that.offset && this.numbytes == that.numbytes;
    }

    public long getNumbytes() {
        return this.numbytes;
    }

    public long getOffset() {
        return this.offset;
    }

    public int hashCode() {
        return Objects.hash(this.offset, this.numbytes);
    }

    public String toString() {
        return "TarArchiveStructSparse{offset=" + this.offset + ", numbytes=" + this.numbytes + '}';
    }
}

