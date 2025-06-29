/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.lz4;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.lz4.BlockLZ4CompressorInputStream;
import org.apache.commons.compress.compressors.lz4.XXHash32;
import org.apache.commons.compress.utils.BoundedInputStream;
import org.apache.commons.compress.utils.ByteUtils;
import org.apache.commons.compress.utils.ChecksumCalculatingInputStream;
import org.apache.commons.compress.utils.CountingInputStream;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.compress.utils.InputStreamStatistics;

public class FramedLZ4CompressorInputStream
extends CompressorInputStream
implements InputStreamStatistics {
    static final byte[] LZ4_SIGNATURE = new byte[]{4, 34, 77, 24};
    private static final byte[] SKIPPABLE_FRAME_TRAILER = new byte[]{42, 77, 24};
    private static final byte SKIPPABLE_FRAME_PREFIX_BYTE_MASK = 80;
    static final int VERSION_MASK = 192;
    static final int SUPPORTED_VERSION = 64;
    static final int BLOCK_INDEPENDENCE_MASK = 32;
    static final int BLOCK_CHECKSUM_MASK = 16;
    static final int CONTENT_SIZE_MASK = 8;
    static final int CONTENT_CHECKSUM_MASK = 4;
    static final int BLOCK_MAX_SIZE_MASK = 112;
    static final int UNCOMPRESSED_FLAG_MASK = Integer.MIN_VALUE;
    private final byte[] oneByte = new byte[1];
    private final ByteUtils.ByteSupplier supplier = this::readOneByte;
    private final CountingInputStream inputStream;
    private final boolean decompressConcatenated;
    private boolean expectBlockChecksum;
    private boolean expectBlockDependency;
    private boolean expectContentSize;
    private boolean expectContentChecksum;
    private InputStream currentBlock;
    private boolean endReached;
    private boolean inUncompressed;
    private final XXHash32 contentHash = new XXHash32();
    private final XXHash32 blockHash = new XXHash32();
    private byte[] blockDependencyBuffer;

    private static boolean isSkippableFrameSignature(byte[] b2) {
        if ((b2[0] & 0x50) != 80) {
            return false;
        }
        for (int i2 = 1; i2 < 4; ++i2) {
            if (b2[i2] == SKIPPABLE_FRAME_TRAILER[i2 - 1]) continue;
            return false;
        }
        return true;
    }

    public static boolean matches(byte[] signature, int length) {
        if (length < LZ4_SIGNATURE.length) {
            return false;
        }
        byte[] shortenedSig = signature;
        if (signature.length > LZ4_SIGNATURE.length) {
            shortenedSig = Arrays.copyOf(signature, LZ4_SIGNATURE.length);
        }
        return Arrays.equals(shortenedSig, LZ4_SIGNATURE);
    }

    public FramedLZ4CompressorInputStream(InputStream in) throws IOException {
        this(in, false);
    }

    public FramedLZ4CompressorInputStream(InputStream in, boolean decompressConcatenated) throws IOException {
        this.inputStream = new CountingInputStream(in);
        this.decompressConcatenated = decompressConcatenated;
        this.init(true);
    }

    private void appendToBlockDependencyBuffer(byte[] b2, int off, int len) {
        if ((len = Math.min(len, this.blockDependencyBuffer.length)) > 0) {
            int keep = this.blockDependencyBuffer.length - len;
            if (keep > 0) {
                System.arraycopy(this.blockDependencyBuffer, len, this.blockDependencyBuffer, 0, keep);
            }
            System.arraycopy(b2, off, this.blockDependencyBuffer, keep, len);
        }
    }

    @Override
    public void close() throws IOException {
        try {
            if (this.currentBlock != null) {
                this.currentBlock.close();
                this.currentBlock = null;
            }
        } finally {
            this.inputStream.close();
        }
    }

    @Override
    public long getCompressedCount() {
        return this.inputStream.getBytesRead();
    }

    private void init(boolean firstFrame) throws IOException {
        if (this.readSignature(firstFrame)) {
            this.readFrameDescriptor();
            this.nextBlock();
        }
    }

    private void maybeFinishCurrentBlock() throws IOException {
        if (this.currentBlock != null) {
            this.currentBlock.close();
            this.currentBlock = null;
            if (this.expectBlockChecksum) {
                this.verifyChecksum(this.blockHash, "block");
                this.blockHash.reset();
            }
        }
    }

    private void nextBlock() throws IOException {
        this.maybeFinishCurrentBlock();
        long len = ByteUtils.fromLittleEndian(this.supplier, 4);
        boolean uncompressed = (len & Integer.MIN_VALUE) != 0L;
        int realLen = (int)(len & Integer.MAX_VALUE);
        if (realLen == 0) {
            this.verifyContentChecksum();
            if (!this.decompressConcatenated) {
                this.endReached = true;
            } else {
                this.init(false);
            }
            return;
        }
        FilterInputStream capped = new BoundedInputStream(this.inputStream, realLen);
        if (this.expectBlockChecksum) {
            capped = new ChecksumCalculatingInputStream(this.blockHash, capped);
        }
        if (uncompressed) {
            this.inUncompressed = true;
            this.currentBlock = capped;
        } else {
            this.inUncompressed = false;
            BlockLZ4CompressorInputStream s2 = new BlockLZ4CompressorInputStream(capped);
            if (this.expectBlockDependency) {
                s2.prefill(this.blockDependencyBuffer);
            }
            this.currentBlock = s2;
        }
    }

    @Override
    public int read() throws IOException {
        return this.read(this.oneByte, 0, 1) == -1 ? -1 : this.oneByte[0] & 0xFF;
    }

    @Override
    public int read(byte[] b2, int off, int len) throws IOException {
        if (len == 0) {
            return 0;
        }
        if (this.endReached) {
            return -1;
        }
        int r2 = this.readOnce(b2, off, len);
        if (r2 == -1) {
            this.nextBlock();
            if (!this.endReached) {
                r2 = this.readOnce(b2, off, len);
            }
        }
        if (r2 != -1) {
            if (this.expectBlockDependency) {
                this.appendToBlockDependencyBuffer(b2, off, r2);
            }
            if (this.expectContentChecksum) {
                this.contentHash.update(b2, off, r2);
            }
        }
        return r2;
    }

    private void readFrameDescriptor() throws IOException {
        int headerHash;
        int flags = this.readOneByte();
        if (flags == -1) {
            throw new IOException("Premature end of stream while reading frame flags");
        }
        this.contentHash.update(flags);
        if ((flags & 0xC0) != 64) {
            throw new IOException("Unsupported version " + (flags >> 6));
        }
        boolean bl = this.expectBlockDependency = (flags & 0x20) == 0;
        if (this.expectBlockDependency) {
            if (this.blockDependencyBuffer == null) {
                this.blockDependencyBuffer = new byte[65536];
            }
        } else {
            this.blockDependencyBuffer = null;
        }
        this.expectBlockChecksum = (flags & 0x10) != 0;
        this.expectContentSize = (flags & 8) != 0;
        this.expectContentChecksum = (flags & 4) != 0;
        int bdByte = this.readOneByte();
        if (bdByte == -1) {
            throw new IOException("Premature end of stream while reading frame BD byte");
        }
        this.contentHash.update(bdByte);
        if (this.expectContentSize) {
            byte[] contentSize = new byte[8];
            int skipped = IOUtils.readFully(this.inputStream, contentSize);
            this.count(skipped);
            if (8 != skipped) {
                throw new IOException("Premature end of stream while reading content size");
            }
            this.contentHash.update(contentSize, 0, contentSize.length);
        }
        if ((headerHash = this.readOneByte()) == -1) {
            throw new IOException("Premature end of stream while reading frame header checksum");
        }
        int expectedHash = (int)(this.contentHash.getValue() >> 8 & 0xFFL);
        this.contentHash.reset();
        if (headerHash != expectedHash) {
            throw new IOException("Frame header checksum mismatch");
        }
    }

    private int readOnce(byte[] b2, int off, int len) throws IOException {
        if (this.inUncompressed) {
            int cnt = this.currentBlock.read(b2, off, len);
            this.count(cnt);
            return cnt;
        }
        BlockLZ4CompressorInputStream l2 = (BlockLZ4CompressorInputStream)this.currentBlock;
        long before = l2.getBytesRead();
        int cnt = this.currentBlock.read(b2, off, len);
        this.count(l2.getBytesRead() - before);
        return cnt;
    }

    private int readOneByte() throws IOException {
        int b2 = this.inputStream.read();
        if (b2 != -1) {
            this.count(1);
            return b2 & 0xFF;
        }
        return -1;
    }

    private boolean readSignature(boolean firstFrame) throws IOException {
        String garbageMessage = firstFrame ? "Not a LZ4 frame stream" : "LZ4 frame stream followed by garbage";
        byte[] b2 = new byte[4];
        int read = IOUtils.readFully(this.inputStream, b2);
        this.count(read);
        if (0 == read && !firstFrame) {
            this.endReached = true;
            return false;
        }
        if (4 != read) {
            throw new IOException(garbageMessage);
        }
        read = this.skipSkippableFrame(b2);
        if (0 == read && !firstFrame) {
            this.endReached = true;
            return false;
        }
        if (4 != read || !FramedLZ4CompressorInputStream.matches(b2, 4)) {
            throw new IOException(garbageMessage);
        }
        return true;
    }

    private int skipSkippableFrame(byte[] b2) throws IOException {
        int read = 4;
        while (read == 4 && FramedLZ4CompressorInputStream.isSkippableFrameSignature(b2)) {
            long len = ByteUtils.fromLittleEndian(this.supplier, 4);
            if (len < 0L) {
                throw new IOException("Found illegal skippable frame with negative size");
            }
            long skipped = IOUtils.skip(this.inputStream, len);
            this.count(skipped);
            if (len != skipped) {
                throw new IOException("Premature end of stream while skipping frame");
            }
            read = IOUtils.readFully(this.inputStream, b2);
            this.count(read);
        }
        return read;
    }

    private void verifyChecksum(XXHash32 hash, String kind) throws IOException {
        byte[] checksum = new byte[4];
        int read = IOUtils.readFully(this.inputStream, checksum);
        this.count(read);
        if (4 != read) {
            throw new IOException("Premature end of stream while reading " + kind + " checksum");
        }
        long expectedHash = hash.getValue();
        if (expectedHash != ByteUtils.fromLittleEndian(checksum)) {
            throw new IOException(kind + " checksum mismatch.");
        }
    }

    private void verifyContentChecksum() throws IOException {
        if (this.expectContentChecksum) {
            this.verifyChecksum(this.contentHash, "content");
        }
        this.contentHash.reset();
    }
}

