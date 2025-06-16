/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.harmony.pack200;

import org.apache.commons.compress.harmony.pack200.CPConstant;

public class CPInt
extends CPConstant<CPInt> {
    private final int theInt;

    public CPInt(int theInt) {
        this.theInt = theInt;
    }

    @Override
    public int compareTo(CPInt obj) {
        return Integer.compare(this.theInt, obj.theInt);
    }

    public int getInt() {
        return this.theInt;
    }
}

