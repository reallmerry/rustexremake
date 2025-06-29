/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.sevenz;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;
import java.util.stream.Stream;
import org.apache.commons.compress.archivers.sevenz.Coder;
import org.apache.commons.compress.utils.ByteUtils;

abstract class AbstractCoder {
    private final Class<?>[] optionClasses;

    protected static int toInt(Object options, int defaultValue) {
        return options instanceof Number ? ((Number)options).intValue() : defaultValue;
    }

    protected AbstractCoder(Class<?> ... optionClasses) {
        this.optionClasses = Objects.requireNonNull(optionClasses, "optionClasses");
    }

    abstract InputStream decode(String var1, InputStream var2, long var3, Coder var5, byte[] var6, int var7) throws IOException;

    OutputStream encode(OutputStream out, Object options) throws IOException {
        throw new UnsupportedOperationException("Method doesn't support writing");
    }

    byte[] getOptionsAsProperties(Object options) throws IOException {
        return ByteUtils.EMPTY_BYTE_ARRAY;
    }

    Object getOptionsFromCoder(Coder coder, InputStream in) throws IOException {
        return null;
    }

    boolean isOptionInstance(Object opts) {
        return Stream.of(this.optionClasses).anyMatch(c2 -> c2.isInstance(opts));
    }
}

