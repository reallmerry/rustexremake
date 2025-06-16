/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.utils;

public class ExactMath {
    public static int add(int x2, long y2) {
        return Math.addExact(x2, Math.toIntExact(y2));
    }

    private ExactMath() {
    }
}

