/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import org.apache.commons.compress.archivers.zip.BitStream;
import org.apache.commons.compress.utils.IOUtils;

class BinaryTree {
    private static final int UNDEFINED = -1;
    private static final int NODE = -2;
    private final int[] tree;

    static BinaryTree decode(InputStream inputStream, int totalNumberOfValues) throws IOException {
        if (totalNumberOfValues < 0) {
            throw new IllegalArgumentException("totalNumberOfValues must be bigger than 0, is " + totalNumberOfValues);
        }
        int size = inputStream.read() + 1;
        if (size == 0) {
            throw new IOException("Cannot read the size of the encoded tree, unexpected end of stream");
        }
        byte[] encodedTree = IOUtils.readRange(inputStream, size);
        if (encodedTree.length != size) {
            throw new EOFException();
        }
        int maxLength = 0;
        int[] originalBitLengths = new int[totalNumberOfValues];
        int pos = 0;
        for (byte b2 : encodedTree) {
            int numberOfValues = ((b2 & 0xF0) >> 4) + 1;
            if (pos + numberOfValues > totalNumberOfValues) {
                throw new IOException("Number of values exceeds given total number of values");
            }
            int bitLength = (b2 & 0xF) + 1;
            for (int j2 = 0; j2 < numberOfValues; ++j2) {
                originalBitLengths[pos++] = bitLength;
            }
            maxLength = Math.max(maxLength, bitLength);
        }
        int oBitLengths = originalBitLengths.length;
        int[] permutation = new int[oBitLengths];
        for (int k2 = 0; k2 < permutation.length; ++k2) {
            permutation[k2] = k2;
        }
        int c2 = 0;
        int[] sortedBitLengths = new int[oBitLengths];
        for (int k3 = 0; k3 < oBitLengths; ++k3) {
            for (int l2 = 0; l2 < oBitLengths; ++l2) {
                if (originalBitLengths[l2] != k3) continue;
                sortedBitLengths[c2] = k3;
                permutation[c2] = l2;
                ++c2;
            }
        }
        int code = 0;
        int codeIncrement = 0;
        int lastBitLength = 0;
        int[] codes = new int[totalNumberOfValues];
        for (int i2 = totalNumberOfValues - 1; i2 >= 0; --i2) {
            code += codeIncrement;
            if (sortedBitLengths[i2] != lastBitLength) {
                lastBitLength = sortedBitLengths[i2];
                codeIncrement = 1 << 16 - lastBitLength;
            }
            codes[permutation[i2]] = code;
        }
        BinaryTree tree = new BinaryTree(maxLength);
        for (int k4 = 0; k4 < codes.length; ++k4) {
            int bitLength = originalBitLengths[k4];
            if (bitLength <= 0) continue;
            tree.addLeaf(0, Integer.reverse(codes[k4] << 16), bitLength, k4);
        }
        return tree;
    }

    public BinaryTree(int depth) {
        if (depth < 0 || depth > 30) {
            throw new IllegalArgumentException("depth must be bigger than 0 and not bigger than 30 but is " + depth);
        }
        this.tree = new int[(int)((1L << depth + 1) - 1L)];
        Arrays.fill(this.tree, -1);
    }

    public void addLeaf(int node, int path, int depth, int value) {
        if (depth == 0) {
            if (this.tree[node] != -1) {
                throw new IllegalArgumentException("Tree value at index " + node + " has already been assigned (" + this.tree[node] + ")");
            }
            this.tree[node] = value;
        } else {
            this.tree[node] = -2;
            int nextChild = 2 * node + 1 + (path & 1);
            this.addLeaf(nextChild, path >>> 1, depth - 1, value);
        }
    }

    public int read(BitStream stream) throws IOException {
        int value;
        int bit;
        int currentIndex = 0;
        while (true) {
            if ((bit = stream.nextBit()) == -1) {
                return -1;
            }
            int childIndex = 2 * currentIndex + 1 + bit;
            value = this.tree[childIndex];
            if (value != -2) break;
            currentIndex = childIndex;
        }
        if (value != -1) {
            return value;
        }
        throw new IOException("The child " + bit + " of node at index " + currentIndex + " is not defined");
    }
}

