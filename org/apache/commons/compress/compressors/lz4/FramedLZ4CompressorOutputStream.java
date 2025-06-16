/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.lz4;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.lz4.BlockLZ4CompressorOutputStream;
import org.apache.commons.compress.compressors.lz4.FramedLZ4CompressorInputStream;
import org.apache.commons.compress.compressors.lz4.XXHash32;
import org.apache.commons.compress.utils.ByteUtils;

public class FramedLZ4CompressorOutputStream
extends CompressorOutputStream {
    private static final byte[] END_MARK = new byte[4];
    private final byte[] oneByte = new byte[1];
    private final byte[] blockData;
    private final OutputStream out;
    private final Parameters params;
    private boolean finished;
    private int currentIndex;
    private final XXHash32 contentHash = new XXHash32();
    private final XXHash32 blockHash;
    private final byte[] blockDependencyBuffer;
    private int collectedBlockDependencyBytes;

    public FramedLZ4CompressorOutputStream(OutputStream out) throws IOException {
        this(out, Parameters.DEFAULT);
    }

    public FramedLZ4CompressorOutputStream(OutputStream out, Parameters params) throws IOException {
        this.params = params;
        this.blockData = new byte[params.blockSize.getSize()];
        this.out = out;
        this.blockHash = params.withBlockChecksum ? new XXHash32() : null;
        out.write(FramedLZ4CompressorInputStream.LZ4_SIGNATURE);
        this.writeFrameDescriptor();
        this.blockDependencyBuffer = params.withBlockDependency ? new byte[65536] : null;
    }

    private void appendToBlockDependencyBuffer(byte[] b2, int off, int len) {
        if ((len = Math.min(len, this.blockDependencyBuffer.length)) > 0) {
            int keep = this.blockDependencyBuffer.length - len;
            if (keep > 0) {
                System.arraycopy(this.blockDependencyBuffer, len, this.blockDependencyBuffer, 0, keep);
            }
            System.arraycopy(b2, off, this.blockDependencyBuffer, keep, len);
            this.collectedBlockDependencyBytes = Math.min(this.collectedBlockDependencyBytes + len, this.blockDependencyBuffer.length);
        }
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
        if (!this.finished) {
            if (this.currentIndex > 0) {
                this.flushBlock();
            }
            this.writeTrailer();
            this.finished = true;
        }
    }

    private void flushBlock() throws IOException {
        byte[] b2;
        boolean withBlockDependency = this.params.withBlockDependency;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (BlockLZ4CompressorOutputStream o2 = new BlockLZ4CompressorOutputStream(baos, this.params.lz77params);){
            if (withBlockDependency) {
                o2.prefill(this.blockDependencyBuffer, this.blockDependencyBuffer.length - this.collectedBlockDependencyBytes, this.collectedBlockDependencyBytes);
            }
            o2.write(this.blockData, 0, this.currentIndex);
        }
        if (withBlockDependency) {
            this.appendToBlockDependencyBuffer(this.blockData, 0, this.currentIndex);
        }
        if ((b2 = baos.toByteArray()).length > this.currentIndex) {
            ByteUtils.toLittleEndian(this.out, (long)(this.currentIndex | Integer.MIN_VALUE), 4);
            this.out.write(this.blockData, 0, this.currentIndex);
            if (this.params.withBlockChecksum) {
                this.blockHash.update(this.blockData, 0, this.currentIndex);
            }
        } else {
            ByteUtils.toLittleEndian(this.out, (long)b2.length, 4);
            this.out.write(b2);
            if (this.params.withBlockChecksum) {
                this.blockHash.update(b2, 0, b2.length);
            }
        }
        if (this.params.withBlockChecksum) {
            ByteUtils.toLittleEndian(this.out, this.blockHash.getValue(), 4);
            this.blockHash.reset();
        }
        this.currentIndex = 0;
    }

    @Override
    public void write(byte[] data, int off, int len) throws IOException {
        int blockDataLength;
        if (this.params.withContentChecksum) {
            this.contentHash.update(data, off, len);
        }
        if (this.currentIndex + len > (blockDataLength = this.blockData.length)) {
            this.flushBlock();
            while (len > blockDataLength) {
                System.arraycopy(data, off, this.blockData, 0, blockDataLength);
                off += blockDataLength;
                len -= blockDataLength;
                this.currentIndex = blockDataLength;
                this.flushBlock();
            }
        }
        System.arraycopy(data, off, this.blockData, this.currentIndex, len);
        this.currentIndex += len;
    }

    @Override
    public void write(int b2) throws IOException {
        this.oneByte[0] = (byte)(b2 & 0xFF);
        this.write(this.oneByte);
    }

    private void writeFrameDescriptor() throws IOException {
        int flags = 64;
        if (!this.params.withBlockDependency) {
            flags |= 0x20;
        }
        if (this.params.withContentChecksum) {
            flags |= 4;
        }
        if (this.params.withBlockChecksum) {
            flags |= 0x10;
        }
        this.out.write(flags);
        this.contentHash.update(flags);
        int bd = this.params.blockSize.getIndex() << 4 & 0x70;
        this.out.write(bd);
        this.contentHash.update(bd);
        this.out.write((int)(this.contentHash.getValue() >> 8 & 0xFFL));
        this.contentHash.reset();
    }

    private void writeTrailer() throws IOException {
        this.out.write(END_MARK);
        if (this.params.withContentChecksum) {
            ByteUtils.toLittleEndian(this.out, this.contentHash.getValue(), 4);
        }
    }

    public static class Parameters {
        public static final Parameters DEFAULT = new Parameters(BlockSize.M4, true, false, false);
        private final BlockSize blockSize;
        private final boolean withContentChecksum;
        private final boolean withBlockChecksum;
        private final boolean withBlockDependency;
        private final org.apache.commons.compress.compressors.lz77support.Parameters lz77params;

        public Parameters(BlockSize blockSize) {
            this(blockSize, true, false, false);
        }

        public Parameters(BlockSize blockSize, boolean withContentChecksum, boolean withBlockChecksum, boolean withBlockDependency) {
            this(blockSize, withContentChecksum, withBlockChecksum, withBlockDependency, BlockLZ4CompressorOutputStream.createParameterBuilder().build());
        }

        public Parameters(BlockSize blockSize, boolean withContentChecksum, boolean withBlockChecksum, boolean withBlockDependency, org.apache.commons.compress.compressors.lz77support.Parameters lz77params) {
            this.blockSize = blockSize;
            this.withContentChecksum = withContentChecksum;
            this.withBlockChecksum = withBlockChecksum;
            this.withBlockDependency = withBlockDependency;
            this.lz77params = lz77params;
        }

        public Parameters(BlockSize blockSize, org.apache.commons.compress.compressors.lz77support.Parameters lz77params) {
            this(blockSize, true, false, false, lz77params);
        }

        public String toString() {
            return "LZ4 Parameters with BlockSize " + (Object)((Object)this.blockSize) + ", withContentChecksum " + this.withContentChecksum + ", withBlockChecksum " + this.withBlockChecksum + ", withBlockDependency " + this.withBlockDependency;
        }
    }

    public static enum BlockSize {
        K64(65536, 4),
        K256(262144, 5),
        M1(0x100000, 6),
        M4(0x400000, 7);

        private final int size;
        private final int index;

        private BlockSize(int size, int index) {
            this.size = size;
            this.index = index;
        }

        int getIndex() {
            return this.index;
        }

        int getSize() {
            return this.size;
        }
    }
}

