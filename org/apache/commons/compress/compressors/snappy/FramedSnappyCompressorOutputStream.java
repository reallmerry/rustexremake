/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.snappy;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.lz77support.Parameters;
import org.apache.commons.compress.compressors.snappy.FramedSnappyCompressorInputStream;
import org.apache.commons.compress.compressors.snappy.PureJavaCrc32C;
import org.apache.commons.compress.compressors.snappy.SnappyCompressorOutputStream;
import org.apache.commons.compress.utils.ByteUtils;

public class FramedSnappyCompressorOutputStream
extends CompressorOutputStream {
    private static final int MAX_COMPRESSED_BUFFER_SIZE = 65536;
    private final OutputStream out;
    private final Parameters params;
    private final PureJavaCrc32C checksum = new PureJavaCrc32C();
    private final byte[] oneByte = new byte[1];
    private final byte[] buffer = new byte[65536];
    private int currentIndex;
    private final ByteUtils.ByteConsumer consumer;

    static long mask(long x2) {
        x2 = x2 >> 15 | x2 << 17;
        x2 += 2726488792L;
        return x2 &= 0xFFFFFFFFL;
    }

    public FramedSnappyCompressorOutputStream(OutputStream out) throws IOException {
        this(out, SnappyCompressorOutputStream.createParameterBuilder(32768).build());
    }

    public FramedSnappyCompressorOutputStream(OutputStream out, Parameters params) throws IOException {
        this.out = out;
        this.params = params;
        this.consumer = new ByteUtils.OutputStreamByteConsumer(out);
        out.write(FramedSnappyCompressorInputStream.SZ_SIGNATURE);
    }

    @Override
    public void close() throws IOException {
        try {
            this.finish();
        } finally {
            this.out.close();
        }
    }

    public void finish() throws IOException {
        if (this.currentIndex > 0) {
            this.flushBuffer();
        }
    }

    private void flushBuffer() throws IOException {
        this.out.write(0);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (SnappyCompressorOutputStream o2 = new SnappyCompressorOutputStream((OutputStream)baos, (long)this.currentIndex, this.params);){
            ((OutputStream)o2).write(this.buffer, 0, this.currentIndex);
        }
        byte[] b2 = baos.toByteArray();
        this.writeLittleEndian(3, (long)b2.length + 4L);
        this.writeCrc();
        this.out.write(b2);
        this.currentIndex = 0;
    }

    @Override
    public void write(byte[] data, int off, int len) throws IOException {
        if (this.currentIndex + len > 65536) {
            this.flushBuffer();
            while (len > 65536) {
                System.arraycopy(data, off, this.buffer, 0, 65536);
                off += 65536;
                len -= 65536;
                this.currentIndex = 65536;
                this.flushBuffer();
            }
        }
        System.arraycopy(data, off, this.buffer, this.currentIndex, len);
        this.currentIndex += len;
    }

    @Override
    public void write(int b2) throws IOException {
        this.oneByte[0] = (byte)(b2 & 0xFF);
        this.write(this.oneByte);
    }

    private void writeCrc() throws IOException {
        this.checksum.update(this.buffer, 0, this.currentIndex);
        this.writeLittleEndian(4, FramedSnappyCompressorOutputStream.mask(this.checksum.getValue()));
        this.checksum.reset();
    }

    private void writeLittleEndian(int numBytes, long num) throws IOException {
        ByteUtils.toLittleEndian(this.consumer, num, numBytes);
    }
}

