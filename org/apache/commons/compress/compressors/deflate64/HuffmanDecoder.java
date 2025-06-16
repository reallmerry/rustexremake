/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.deflate64;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.util.Arrays;
import org.apache.commons.compress.compressors.deflate64.HuffmanState;
import org.apache.commons.compress.utils.BitInputStream;
import org.apache.commons.compress.utils.ByteUtils;
import org.apache.commons.compress.utils.ExactMath;

class HuffmanDecoder
implements Closeable {
    private static final short[] RUN_LENGTH_TABLE = new short[]{96, 128, 160, 192, 224, 256, 288, 320, 353, 417, 481, 545, 610, 738, 866, 994, 1123, 1379, 1635, 1891, 2148, 2660, 3172, 3684, 4197, 5221, 6245, 7269, 112};
    private static final int[] DISTANCE_TABLE = new int[]{16, 32, 48, 64, 81, 113, 146, 210, 275, 403, 532, 788, 1045, 1557, 2070, 3094, 4119, 6167, 8216, 12312, 16409, 24601, 32794, 49178, 65563, 98331, 131100, 196636, 262173, 393245, 524318, 786462};
    private static final int[] CODE_LENGTHS_ORDER = new int[]{16, 17, 18, 0, 8, 7, 9, 6, 10, 5, 11, 4, 12, 3, 13, 2, 14, 1, 15};
    private static final int[] FIXED_LITERALS = new int[288];
    private static final int[] FIXED_DISTANCE;
    private boolean finalBlock;
    private DecoderState state;
    private BitInputStream reader;
    private final InputStream in;
    private final DecodingMemory memory = new DecodingMemory();

    private static BinaryTreeNode buildTree(int[] litTable) {
        int[] literalCodes = HuffmanDecoder.getCodes(litTable);
        BinaryTreeNode root = new BinaryTreeNode(0);
        for (int i2 = 0; i2 < litTable.length; ++i2) {
            int len = litTable[i2];
            if (len == 0) continue;
            BinaryTreeNode node = root;
            int lit = literalCodes[len - 1];
            for (int p2 = len - 1; p2 >= 0; --p2) {
                int bit = lit & 1 << p2;
                BinaryTreeNode binaryTreeNode = node = bit == 0 ? node.left() : node.right();
                if (node != null) continue;
                throw new IllegalStateException("node doesn't exist in Huffman tree");
            }
            node.leaf(i2);
            int n2 = len - 1;
            literalCodes[n2] = literalCodes[n2] + 1;
        }
        return root;
    }

    private static int[] getCodes(int[] litTable) {
        int max = 0;
        int[] blCount = new int[65];
        for (int aLitTable : litTable) {
            if (aLitTable < 0 || aLitTable > 64) {
                throw new IllegalArgumentException("Invalid code " + aLitTable + " in literal table");
            }
            max = Math.max(max, aLitTable);
            int n2 = aLitTable;
            blCount[n2] = blCount[n2] + 1;
        }
        blCount = Arrays.copyOf(blCount, max + 1);
        int code = 0;
        int[] nextCode = new int[max + 1];
        for (int i2 = 0; i2 <= max; ++i2) {
            nextCode[i2] = code = code + blCount[i2] << 1;
        }
        return nextCode;
    }

    private static int nextSymbol(BitInputStream reader, BinaryTreeNode tree) throws IOException {
        BinaryTreeNode node = tree;
        while (node != null && node.literal == -1) {
            long bit = HuffmanDecoder.readBits(reader, 1);
            node = bit == 0L ? node.leftNode : node.rightNode;
        }
        return node != null ? node.literal : -1;
    }

    private static void populateDynamicTables(BitInputStream reader, int[] literals, int[] distances) throws IOException {
        int codeLengths = (int)(HuffmanDecoder.readBits(reader, 4) + 4L);
        int[] codeLengthValues = new int[19];
        for (int cLen = 0; cLen < codeLengths; ++cLen) {
            codeLengthValues[HuffmanDecoder.CODE_LENGTHS_ORDER[cLen]] = (int)HuffmanDecoder.readBits(reader, 3);
        }
        BinaryTreeNode codeLengthTree = HuffmanDecoder.buildTree(codeLengthValues);
        int[] auxBuffer = new int[literals.length + distances.length];
        int value = -1;
        int length = 0;
        int off = 0;
        while (off < auxBuffer.length) {
            if (length > 0) {
                auxBuffer[off++] = value;
                --length;
                continue;
            }
            int symbol = HuffmanDecoder.nextSymbol(reader, codeLengthTree);
            if (symbol < 16) {
                value = symbol;
                auxBuffer[off++] = value;
                continue;
            }
            switch (symbol) {
                case 16: {
                    length = (int)(HuffmanDecoder.readBits(reader, 2) + 3L);
                    break;
                }
                case 17: {
                    value = 0;
                    length = (int)(HuffmanDecoder.readBits(reader, 3) + 3L);
                    break;
                }
                case 18: {
                    value = 0;
                    length = (int)(HuffmanDecoder.readBits(reader, 7) + 11L);
                    break;
                }
            }
        }
        System.arraycopy(auxBuffer, 0, literals, 0, literals.length);
        System.arraycopy(auxBuffer, literals.length, distances, 0, distances.length);
    }

    private static long readBits(BitInputStream reader, int numBits) throws IOException {
        long r2 = reader.readBits(numBits);
        if (r2 == -1L) {
            throw new EOFException("Truncated Deflate64 Stream");
        }
        return r2;
    }

    HuffmanDecoder(InputStream in) {
        this.reader = new BitInputStream(in, ByteOrder.LITTLE_ENDIAN);
        this.in = in;
        this.state = new InitialState();
    }

    int available() throws IOException {
        return this.state.available();
    }

    @Override
    public void close() {
        this.state = new InitialState();
        this.reader = null;
    }

    public int decode(byte[] b2) throws IOException {
        return this.decode(b2, 0, b2.length);
    }

    public int decode(byte[] b2, int off, int len) throws IOException {
        block5: while (!this.finalBlock || this.state.hasData()) {
            if (this.state.state() == HuffmanState.INITIAL) {
                this.finalBlock = this.readBits(1) == 1L;
                int mode = (int)this.readBits(2);
                switch (mode) {
                    case 0: {
                        this.switchToUncompressedState();
                        continue block5;
                    }
                    case 1: {
                        this.state = new HuffmanCodes(HuffmanState.FIXED_CODES, FIXED_LITERALS, FIXED_DISTANCE);
                        continue block5;
                    }
                    case 2: {
                        int[][] tables = this.readDynamicTables();
                        this.state = new HuffmanCodes(HuffmanState.DYNAMIC_CODES, tables[0], tables[1]);
                        continue block5;
                    }
                }
                throw new IllegalStateException("Unsupported compression: " + mode);
            }
            int r2 = this.state.read(b2, off, len);
            if (r2 == 0) continue;
            return r2;
        }
        return -1;
    }

    long getBytesRead() {
        return this.reader.getBytesRead();
    }

    private long readBits(int numBits) throws IOException {
        return HuffmanDecoder.readBits(this.reader, numBits);
    }

    private int[][] readDynamicTables() throws IOException {
        int[][] result = new int[2][];
        int literals = (int)(this.readBits(5) + 257L);
        result[0] = new int[literals];
        int distances = (int)(this.readBits(5) + 1L);
        result[1] = new int[distances];
        HuffmanDecoder.populateDynamicTables(this.reader, result[0], result[1]);
        return result;
    }

    private void switchToUncompressedState() throws IOException {
        this.reader.alignWithByteBoundary();
        long bLen = this.readBits(16);
        long bNLen = this.readBits(16);
        if (((bLen ^ 0xFFFFL) & 0xFFFFL) != bNLen) {
            throw new IllegalStateException("Illegal LEN / NLEN values");
        }
        this.state = new UncompressedState(bLen);
    }

    static {
        Arrays.fill(FIXED_LITERALS, 0, 144, 8);
        Arrays.fill(FIXED_LITERALS, 144, 256, 9);
        Arrays.fill(FIXED_LITERALS, 256, 280, 7);
        Arrays.fill(FIXED_LITERALS, 280, 288, 8);
        FIXED_DISTANCE = new int[32];
        Arrays.fill(FIXED_DISTANCE, 5);
    }

    private static class DecodingMemory {
        private final byte[] memory;
        private final int mask;
        private int wHead;
        private boolean wrappedAround;

        private DecodingMemory() {
            this(16);
        }

        private DecodingMemory(int bits) {
            this.memory = new byte[1 << bits];
            this.mask = this.memory.length - 1;
        }

        byte add(byte b2) {
            this.memory[this.wHead] = b2;
            this.wHead = this.incCounter(this.wHead);
            return b2;
        }

        void add(byte[] b2, int off, int len) {
            for (int i2 = off; i2 < off + len; ++i2) {
                this.add(b2[i2]);
            }
        }

        private int incCounter(int counter) {
            int newCounter = counter + 1 & this.mask;
            if (!this.wrappedAround && newCounter < counter) {
                this.wrappedAround = true;
            }
            return newCounter;
        }

        void recordToBuffer(int distance, int length, byte[] buff) {
            if (distance > this.memory.length) {
                throw new IllegalStateException("Illegal distance parameter: " + distance);
            }
            int start = this.wHead - distance & this.mask;
            if (!this.wrappedAround && start >= this.wHead) {
                throw new IllegalStateException("Attempt to read beyond memory: dist=" + distance);
            }
            int pos = start;
            for (int i2 = 0; i2 < length; ++i2) {
                buff[i2] = this.add(this.memory[pos]);
                pos = this.incCounter(pos);
            }
        }
    }

    private static class BinaryTreeNode {
        private final int bits;
        int literal = -1;
        BinaryTreeNode leftNode;
        BinaryTreeNode rightNode;

        private BinaryTreeNode(int bits) {
            this.bits = bits;
        }

        void leaf(int symbol) {
            this.literal = symbol;
            this.leftNode = null;
            this.rightNode = null;
        }

        BinaryTreeNode left() {
            if (this.leftNode == null && this.literal == -1) {
                this.leftNode = new BinaryTreeNode(this.bits + 1);
            }
            return this.leftNode;
        }

        BinaryTreeNode right() {
            if (this.rightNode == null && this.literal == -1) {
                this.rightNode = new BinaryTreeNode(this.bits + 1);
            }
            return this.rightNode;
        }
    }

    private static class InitialState
    extends DecoderState {
        private InitialState() {
        }

        @Override
        int available() {
            return 0;
        }

        @Override
        boolean hasData() {
            return false;
        }

        @Override
        int read(byte[] b2, int off, int len) throws IOException {
            if (len == 0) {
                return 0;
            }
            throw new IllegalStateException("Cannot read in this state");
        }

        @Override
        HuffmanState state() {
            return HuffmanState.INITIAL;
        }
    }

    private static abstract class DecoderState {
        private DecoderState() {
        }

        abstract int available() throws IOException;

        abstract boolean hasData();

        abstract int read(byte[] var1, int var2, int var3) throws IOException;

        abstract HuffmanState state();
    }

    private class HuffmanCodes
    extends DecoderState {
        private boolean endOfBlock;
        private final HuffmanState state;
        private final BinaryTreeNode lengthTree;
        private final BinaryTreeNode distanceTree;
        private int runBufferPos;
        private byte[] runBuffer = ByteUtils.EMPTY_BYTE_ARRAY;
        private int runBufferLength;

        HuffmanCodes(HuffmanState state, int[] lengths, int[] distance) {
            this.state = state;
            this.lengthTree = HuffmanDecoder.buildTree(lengths);
            this.distanceTree = HuffmanDecoder.buildTree(distance);
        }

        @Override
        int available() {
            return this.runBufferLength - this.runBufferPos;
        }

        private int copyFromRunBuffer(byte[] b2, int off, int len) {
            int bytesInBuffer = this.runBufferLength - this.runBufferPos;
            int copiedBytes = 0;
            if (bytesInBuffer > 0) {
                copiedBytes = Math.min(len, bytesInBuffer);
                System.arraycopy(this.runBuffer, this.runBufferPos, b2, off, copiedBytes);
                this.runBufferPos += copiedBytes;
            }
            return copiedBytes;
        }

        private int decodeNext(byte[] b2, int off, int len) throws IOException {
            if (this.endOfBlock) {
                return -1;
            }
            int result = this.copyFromRunBuffer(b2, off, len);
            while (result < len) {
                int symbol = HuffmanDecoder.nextSymbol(HuffmanDecoder.this.reader, this.lengthTree);
                if (symbol < 256) {
                    b2[off + result++] = HuffmanDecoder.this.memory.add((byte)symbol);
                    continue;
                }
                if (symbol > 256) {
                    short runMask = RUN_LENGTH_TABLE[symbol - 257];
                    int run = runMask >>> 5;
                    int runXtra = runMask & 0x1F;
                    run = ExactMath.add(run, HuffmanDecoder.this.readBits(runXtra));
                    int distSym = HuffmanDecoder.nextSymbol(HuffmanDecoder.this.reader, this.distanceTree);
                    int distMask = DISTANCE_TABLE[distSym];
                    int dist = distMask >>> 4;
                    int distXtra = distMask & 0xF;
                    dist = ExactMath.add(dist, HuffmanDecoder.this.readBits(distXtra));
                    if (this.runBuffer.length < run) {
                        this.runBuffer = new byte[run];
                    }
                    this.runBufferLength = run;
                    this.runBufferPos = 0;
                    HuffmanDecoder.this.memory.recordToBuffer(dist, run, this.runBuffer);
                    result += this.copyFromRunBuffer(b2, off + result, len - result);
                    continue;
                }
                this.endOfBlock = true;
                return result;
            }
            return result;
        }

        @Override
        boolean hasData() {
            return !this.endOfBlock;
        }

        @Override
        int read(byte[] b2, int off, int len) throws IOException {
            if (len == 0) {
                return 0;
            }
            return this.decodeNext(b2, off, len);
        }

        @Override
        HuffmanState state() {
            return this.endOfBlock ? HuffmanState.INITIAL : this.state;
        }
    }

    private class UncompressedState
    extends DecoderState {
        private final long blockLength;
        private long read;

        private UncompressedState(long blockLength) {
            this.blockLength = blockLength;
        }

        @Override
        int available() throws IOException {
            return (int)Math.min(this.blockLength - this.read, HuffmanDecoder.this.reader.bitsAvailable() / 8L);
        }

        @Override
        boolean hasData() {
            return this.read < this.blockLength;
        }

        @Override
        int read(byte[] b2, int off, int len) throws IOException {
            int readNow;
            if (len == 0) {
                return 0;
            }
            int max = (int)Math.min(this.blockLength - this.read, (long)len);
            for (int readSoFar = 0; readSoFar < max; readSoFar += readNow) {
                if (HuffmanDecoder.this.reader.bitsCached() > 0) {
                    byte next = (byte)HuffmanDecoder.this.readBits(8);
                    b2[off + readSoFar] = HuffmanDecoder.this.memory.add(next);
                    readNow = 1;
                } else {
                    readNow = HuffmanDecoder.this.in.read(b2, off + readSoFar, max - readSoFar);
                    if (readNow == -1) {
                        throw new EOFException("Truncated Deflate64 Stream");
                    }
                    HuffmanDecoder.this.memory.add(b2, off + readSoFar, readNow);
                }
                this.read += (long)readNow;
            }
            return max;
        }

        @Override
        HuffmanState state() {
            return this.read < this.blockLength ? HuffmanState.STORED : HuffmanState.INITIAL;
        }
    }
}

