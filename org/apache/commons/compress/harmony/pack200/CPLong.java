/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.harmony.pack200;

import org.apache.commons.compress.harmony.pack200.CPConstant;

public class CPLong
extends CPConstant<CPLong> {
    private final long theLong;

    public CPLong(long theLong) {
        this.theLong = theLong;
    }

    @Override
    public int compareTo(CPLong obj) {
        return Long.compare(this.theLong, obj.theLong);
    }

    public long getLong() {
        return this.theLong;
    }

    public String toString() {
        return "" + this.theLong;
    }
}

