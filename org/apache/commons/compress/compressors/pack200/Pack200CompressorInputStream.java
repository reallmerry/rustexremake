/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.pack200;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Map;
import java.util.jar.JarOutputStream;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.pack200.AbstractStreamBridge;
import org.apache.commons.compress.compressors.pack200.Pack200Strategy;
import org.apache.commons.compress.java.util.jar.Pack200;
import org.apache.commons.compress.utils.CloseShieldFilterInputStream;
import org.apache.commons.compress.utils.IOUtils;

public class Pack200CompressorInputStream
extends CompressorInputStream {
    private static final byte[] CAFE_DOOD = new byte[]{-54, -2, -48, 13};
    private static final int SIG_LENGTH = CAFE_DOOD.length;
    private final InputStream originalInput;
    private final AbstractStreamBridge abstractStreamBridge;

    public static boolean matches(byte[] signature, int length) {
        if (length < SIG_LENGTH) {
            return false;
        }
        for (int i2 = 0; i2 < SIG_LENGTH; ++i2) {
            if (signature[i2] == CAFE_DOOD[i2]) continue;
            return false;
        }
        return true;
    }

    public Pack200CompressorInputStream(File f2) throws IOException {
        this(f2, Pack200Strategy.IN_MEMORY);
    }

    public Pack200CompressorInputStream(File f2, Map<String, String> props) throws IOException {
        this(f2, Pack200Strategy.IN_MEMORY, props);
    }

    public Pack200CompressorInputStream(File f2, Pack200Strategy mode) throws IOException {
        this(null, f2, mode, null);
    }

    public Pack200CompressorInputStream(File f2, Pack200Strategy mode, Map<String, String> props) throws IOException {
        this(null, f2, mode, props);
    }

    public Pack200CompressorInputStream(InputStream in) throws IOException {
        this(in, Pack200Strategy.IN_MEMORY);
    }

    private Pack200CompressorInputStream(InputStream in, File f2, Pack200Strategy mode, Map<String, String> props) throws IOException {
        block12: {
            this.originalInput = in;
            this.abstractStreamBridge = mode.newStreamBridge();
            try (JarOutputStream jarOut = new JarOutputStream(this.abstractStreamBridge);){
                Pack200.Unpacker u2 = Pack200.newUnpacker();
                if (props != null) {
                    u2.properties().putAll(props);
                }
                if (f2 == null) {
                    try (CloseShieldFilterInputStream closeShield = new CloseShieldFilterInputStream(in);){
                        u2.unpack(closeShield, jarOut);
                        break block12;
                    }
                }
                u2.unpack(f2, jarOut);
            }
        }
    }

    public Pack200CompressorInputStream(InputStream in, Map<String, String> props) throws IOException {
        this(in, Pack200Strategy.IN_MEMORY, props);
    }

    public Pack200CompressorInputStream(InputStream in, Pack200Strategy mode) throws IOException {
        this(in, null, mode, null);
    }

    public Pack200CompressorInputStream(InputStream in, Pack200Strategy mode, Map<String, String> props) throws IOException {
        this(in, null, mode, props);
    }

    @Override
    public int available() throws IOException {
        return this.abstractStreamBridge.getInput().available();
    }

    @Override
    public void close() throws IOException {
        try {
            this.abstractStreamBridge.stop();
        } finally {
            if (this.originalInput != null) {
                this.originalInput.close();
            }
        }
    }

    @Override
    public synchronized void mark(int limit) {
        try {
            this.abstractStreamBridge.getInput().mark(limit);
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }

    @Override
    public boolean markSupported() {
        try {
            return this.abstractStreamBridge.getInput().markSupported();
        } catch (IOException ex) {
            return false;
        }
    }

    @Override
    public int read() throws IOException {
        return this.abstractStreamBridge.getInput().read();
    }

    @Override
    public int read(byte[] b2) throws IOException {
        return this.abstractStreamBridge.getInput().read(b2);
    }

    @Override
    public int read(byte[] b2, int off, int count) throws IOException {
        return this.abstractStreamBridge.getInput().read(b2, off, count);
    }

    @Override
    public synchronized void reset() throws IOException {
        this.abstractStreamBridge.getInput().reset();
    }

    @Override
    public long skip(long count) throws IOException {
        return IOUtils.skip(this.abstractStreamBridge.getInput(), count);
    }
}

