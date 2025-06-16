/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.pack200;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.jar.JarInputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.pack200.AbstractStreamBridge;
import org.apache.commons.compress.compressors.pack200.Pack200Strategy;
import org.apache.commons.compress.java.util.jar.Pack200;

public class Pack200CompressorOutputStream
extends CompressorOutputStream {
    private boolean finished;
    private final OutputStream originalOutput;
    private final AbstractStreamBridge abstractStreamBridge;
    private final Map<String, String> properties;

    public Pack200CompressorOutputStream(OutputStream out) throws IOException {
        this(out, Pack200Strategy.IN_MEMORY);
    }

    public Pack200CompressorOutputStream(OutputStream out, Map<String, String> props) throws IOException {
        this(out, Pack200Strategy.IN_MEMORY, props);
    }

    public Pack200CompressorOutputStream(OutputStream out, Pack200Strategy mode) throws IOException {
        this(out, mode, null);
    }

    public Pack200CompressorOutputStream(OutputStream out, Pack200Strategy mode, Map<String, String> props) throws IOException {
        this.originalOutput = out;
        this.abstractStreamBridge = mode.newStreamBridge();
        this.properties = props;
    }

    @Override
    public void close() throws IOException {
        try {
            this.finish();
        } finally {
            try {
                this.abstractStreamBridge.stop();
            } finally {
                this.originalOutput.close();
            }
        }
    }

    public void finish() throws IOException {
        if (!this.finished) {
            this.finished = true;
            Pack200.Packer p2 = Pack200.newPacker();
            if (this.properties != null) {
                p2.properties().putAll(this.properties);
            }
            try (JarInputStream ji = new JarInputStream(this.abstractStreamBridge.getInput());){
                p2.pack(ji, this.originalOutput);
            }
        }
    }

    @Override
    public void write(byte[] b2) throws IOException {
        this.abstractStreamBridge.write(b2);
    }

    @Override
    public void write(byte[] b2, int from, int length) throws IOException {
        this.abstractStreamBridge.write(b2, from, length);
    }

    @Override
    public void write(int b2) throws IOException {
        this.abstractStreamBridge.write(b2);
    }
}

