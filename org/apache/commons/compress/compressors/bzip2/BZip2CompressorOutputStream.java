/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.bzip2;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import org.apache.commons.compress.compressors.CompressorOutputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2Constants;
import org.apache.commons.compress.compressors.bzip2.BlockSort;
import org.apache.commons.compress.compressors.bzip2.CRC;

public class BZip2CompressorOutputStream
extends CompressorOutputStream
implements BZip2Constants {
    public static final int MIN_BLOCKSIZE = 1;
    public static final int MAX_BLOCKSIZE = 9;
    private static final int GREATER_ICOST = 15;
    private static final int LESSER_ICOST = 0;
    private int last;
    private final int blockSize100k;
    private int bsBuff;
    private int bsLive;
    private final CRC crc = new CRC();
    private int nInUse;
    private int nMTF;
    private int currentChar = -1;
    private int runLength;
    private int blockCRC;
    private int combinedCRC;
    private final int allowableBlockSize;
    private Data data;
    private BlockSort blockSorter;
    private OutputStream out;
    private volatile boolean closed;

    public static int chooseBlockSize(long inputLength) {
        return inputLength > 0L ? (int)Math.min(inputLength / 132000L + 1L, 9L) : 9;
    }

    private static void hbAssignCodes(int[] code, byte[] length, int minLen, int maxLen, int alphaSize) {
        int vec = 0;
        for (int n2 = minLen; n2 <= maxLen; ++n2) {
            for (int i2 = 0; i2 < alphaSize; ++i2) {
                if ((length[i2] & 0xFF) != n2) continue;
                code[i2] = vec++;
            }
            vec <<= 1;
        }
    }

    private static void hbMakeCodeLengths(byte[] len, int[] freq, Data dat, int alphaSize, int maxLen) {
        int[] heap = dat.heap;
        int[] weight = dat.weight;
        int[] parent = dat.parent;
        int i2 = alphaSize;
        while (--i2 >= 0) {
            weight[i2 + 1] = (freq[i2] == 0 ? 1 : freq[i2]) << 8;
        }
        boolean tooLong = true;
        while (tooLong) {
            int j2;
            int i3;
            tooLong = false;
            int nNodes = alphaSize;
            int nHeap = 0;
            heap[0] = 0;
            weight[0] = 0;
            parent[0] = -2;
            for (i3 = 1; i3 <= alphaSize; ++i3) {
                parent[i3] = -1;
                heap[++nHeap] = i3;
                int zz = nHeap;
                int tmp = heap[zz];
                while (weight[tmp] < weight[heap[zz >> 1]]) {
                    heap[zz] = heap[zz >> 1];
                    zz >>= 1;
                }
                heap[zz] = tmp;
            }
            while (nHeap > 1) {
                int n1 = heap[1];
                heap[1] = heap[nHeap];
                --nHeap;
                int yy = 0;
                int zz = 1;
                int tmp = heap[1];
                while ((yy = zz << 1) <= nHeap) {
                    if (yy < nHeap && weight[heap[yy + 1]] < weight[heap[yy]]) {
                        ++yy;
                    }
                    if (weight[tmp] < weight[heap[yy]]) break;
                    heap[zz] = heap[yy];
                    zz = yy;
                }
                heap[zz] = tmp;
                int n2 = heap[1];
                heap[1] = heap[nHeap];
                --nHeap;
                yy = 0;
                zz = 1;
                tmp = heap[1];
                while ((yy = zz << 1) <= nHeap) {
                    if (yy < nHeap && weight[heap[yy + 1]] < weight[heap[yy]]) {
                        ++yy;
                    }
                    if (weight[tmp] < weight[heap[yy]]) break;
                    heap[zz] = heap[yy];
                    zz = yy;
                }
                heap[zz] = tmp;
                parent[n1] = parent[n2] = ++nNodes;
                int weight_n1 = weight[n1];
                int weight_n2 = weight[n2];
                weight[nNodes] = (weight_n1 & 0xFFFFFF00) + (weight_n2 & 0xFFFFFF00) | 1 + Math.max(weight_n1 & 0xFF, weight_n2 & 0xFF);
                parent[nNodes] = -1;
                heap[++nHeap] = nNodes;
                tmp = 0;
                zz = nHeap;
                tmp = heap[zz];
                int weight_tmp = weight[tmp];
                while (weight_tmp < weight[heap[zz >> 1]]) {
                    heap[zz] = heap[zz >> 1];
                    zz >>= 1;
                }
                heap[zz] = tmp;
            }
            for (i3 = 1; i3 <= alphaSize; ++i3) {
                int parent_k;
                j2 = 0;
                int k2 = i3;
                while ((parent_k = parent[k2]) >= 0) {
                    k2 = parent_k;
                    ++j2;
                }
                len[i3 - 1] = (byte)j2;
                if (j2 <= maxLen) continue;
                tooLong = true;
            }
            if (!tooLong) continue;
            for (i3 = 1; i3 < alphaSize; ++i3) {
                j2 = weight[i3] >> 8;
                j2 = 1 + (j2 >> 1);
                weight[i3] = j2 << 8;
            }
        }
    }

    public BZip2CompressorOutputStream(OutputStream out) throws IOException {
        this(out, 9);
    }

    public BZip2CompressorOutputStream(OutputStream out, int blockSize) throws IOException {
        if (blockSize < 1) {
            throw new IllegalArgumentException("blockSize(" + blockSize + ") < 1");
        }
        if (blockSize > 9) {
            throw new IllegalArgumentException("blockSize(" + blockSize + ") > 9");
        }
        this.blockSize100k = blockSize;
        this.out = out;
        this.allowableBlockSize = this.blockSize100k * 100000 - 20;
        this.init();
    }

    private void blockSort() {
        this.blockSorter.blockSort(this.data, this.last);
    }

    private void bsFinishedWithStream() throws IOException {
        while (this.bsLive > 0) {
            int ch = this.bsBuff >> 24;
            this.out.write(ch);
            this.bsBuff <<= 8;
            this.bsLive -= 8;
        }
    }

    private void bsPutInt(int u2) throws IOException {
        this.bsW(8, u2 >> 24 & 0xFF);
        this.bsW(8, u2 >> 16 & 0xFF);
        this.bsW(8, u2 >> 8 & 0xFF);
        this.bsW(8, u2 & 0xFF);
    }

    private void bsPutUByte(int c2) throws IOException {
        this.bsW(8, c2);
    }

    private void bsW(int n2, int v2) throws IOException {
        int bsLiveShadow;
        OutputStream outShadow = this.out;
        int bsBuffShadow = this.bsBuff;
        for (bsLiveShadow = this.bsLive; bsLiveShadow >= 8; bsLiveShadow -= 8) {
            outShadow.write(bsBuffShadow >> 24);
            bsBuffShadow <<= 8;
        }
        this.bsBuff = bsBuffShadow | v2 << 32 - bsLiveShadow - n2;
        this.bsLive = bsLiveShadow + n2;
    }

    @Override
    public void close() throws IOException {
        if (!this.closed) {
            try (OutputStream outShadow = this.out;){
                this.finish();
            }
        }
    }

    private void endBlock() throws IOException {
        this.blockCRC = this.crc.getFinalCRC();
        this.combinedCRC = this.combinedCRC << 1 | this.combinedCRC >>> 31;
        this.combinedCRC ^= this.blockCRC;
        if (this.last == -1) {
            return;
        }
        this.blockSort();
        this.bsPutUByte(49);
        this.bsPutUByte(65);
        this.bsPutUByte(89);
        this.bsPutUByte(38);
        this.bsPutUByte(83);
        this.bsPutUByte(89);
        this.bsPutInt(this.blockCRC);
        this.bsW(1, 0);
        this.moveToFrontCodeAndSend();
    }

    private void endCompression() throws IOException {
        this.bsPutUByte(23);
        this.bsPutUByte(114);
        this.bsPutUByte(69);
        this.bsPutUByte(56);
        this.bsPutUByte(80);
        this.bsPutUByte(144);
        this.bsPutInt(this.combinedCRC);
        this.bsFinishedWithStream();
    }

    public void finish() throws IOException {
        if (!this.closed) {
            this.closed = true;
            try {
                if (this.runLength > 0) {
                    this.writeRun();
                }
                this.currentChar = -1;
                this.endBlock();
                this.endCompression();
            } finally {
                this.out = null;
                this.blockSorter = null;
                this.data = null;
            }
        }
    }

    @Override
    public void flush() throws IOException {
        OutputStream outShadow = this.out;
        if (outShadow != null) {
            outShadow.flush();
        }
    }

    private void generateMTFValues() {
        int lastShadow = this.last;
        Data dataShadow = this.data;
        boolean[] inUse = dataShadow.inUse;
        byte[] block = dataShadow.block;
        int[] fmap = dataShadow.fmap;
        char[] sfmap = dataShadow.sfmap;
        int[] mtfFreq = dataShadow.mtfFreq;
        byte[] unseqToSeq = dataShadow.unseqToSeq;
        byte[] yy = dataShadow.generateMTFValues_yy;
        int nInUseShadow = 0;
        for (int i2 = 0; i2 < 256; ++i2) {
            if (!inUse[i2]) continue;
            unseqToSeq[i2] = (byte)nInUseShadow;
            ++nInUseShadow;
        }
        this.nInUse = nInUseShadow;
        int eob = nInUseShadow + 1;
        Arrays.fill(mtfFreq, 0, eob + 1, 0);
        int i3 = nInUseShadow;
        while (--i3 >= 0) {
            yy[i3] = (byte)i3;
        }
        int wr = 0;
        int zPend = 0;
        for (int i4 = 0; i4 <= lastShadow; ++i4) {
            byte ll_i = unseqToSeq[block[fmap[i4]] & 0xFF];
            byte tmp = yy[0];
            int j2 = 0;
            while (ll_i != tmp) {
                byte tmp2 = tmp;
                tmp = yy[++j2];
                yy[j2] = tmp2;
            }
            yy[0] = tmp;
            if (j2 == 0) {
                ++zPend;
                continue;
            }
            if (zPend > 0) {
                --zPend;
                while (true) {
                    if ((zPend & 1) == 0) {
                        sfmap[wr] = '\u0000';
                        ++wr;
                        mtfFreq[0] = mtfFreq[0] + 1;
                    } else {
                        sfmap[wr] = '\u0001';
                        ++wr;
                        mtfFreq[1] = mtfFreq[1] + 1;
                    }
                    if (zPend < 2) break;
                    zPend = zPend - 2 >> 1;
                }
                zPend = 0;
            }
            sfmap[wr] = (char)(j2 + 1);
            ++wr;
            int n2 = j2 + 1;
            mtfFreq[n2] = mtfFreq[n2] + 1;
        }
        if (zPend > 0) {
            --zPend;
            while (true) {
                if ((zPend & 1) == 0) {
                    sfmap[wr] = '\u0000';
                    ++wr;
                    mtfFreq[0] = mtfFreq[0] + 1;
                } else {
                    sfmap[wr] = '\u0001';
                    ++wr;
                    mtfFreq[1] = mtfFreq[1] + 1;
                }
                if (zPend < 2) break;
                zPend = zPend - 2 >> 1;
            }
        }
        sfmap[wr] = (char)eob;
        int n3 = eob;
        mtfFreq[n3] = mtfFreq[n3] + 1;
        this.nMTF = wr + 1;
    }

    public final int getBlockSize() {
        return this.blockSize100k;
    }

    private void init() throws IOException {
        this.bsPutUByte(66);
        this.bsPutUByte(90);
        this.data = new Data(this.blockSize100k);
        this.blockSorter = new BlockSort(this.data);
        this.bsPutUByte(104);
        this.bsPutUByte(48 + this.blockSize100k);
        this.combinedCRC = 0;
        this.initBlock();
    }

    private void initBlock() {
        this.crc.initializeCRC();
        this.last = -1;
        boolean[] inUse = this.data.inUse;
        int i2 = 256;
        while (--i2 >= 0) {
            inUse[i2] = false;
        }
    }

    private void moveToFrontCodeAndSend() throws IOException {
        this.bsW(24, this.data.origPtr);
        this.generateMTFValues();
        this.sendMTFValues();
    }

    private void sendMTFValues() throws IOException {
        byte[][] len = this.data.sendMTFValues_len;
        int alphaSize = this.nInUse + 2;
        int t2 = 6;
        while (--t2 >= 0) {
            byte[] len_t = len[t2];
            int v2 = alphaSize;
            while (--v2 >= 0) {
                len_t[v2] = 15;
            }
        }
        int nGroups = this.nMTF < 200 ? 2 : (this.nMTF < 600 ? 3 : (this.nMTF < 1200 ? 4 : (this.nMTF < 2400 ? 5 : 6)));
        this.sendMTFValues0(nGroups, alphaSize);
        int nSelectors = this.sendMTFValues1(nGroups, alphaSize);
        this.sendMTFValues2(nGroups, nSelectors);
        this.sendMTFValues3(nGroups, alphaSize);
        this.sendMTFValues4();
        this.sendMTFValues5(nGroups, nSelectors);
        this.sendMTFValues6(nGroups, alphaSize);
        this.sendMTFValues7();
    }

    private void sendMTFValues0(int nGroups, int alphaSize) {
        byte[][] len = this.data.sendMTFValues_len;
        int[] mtfFreq = this.data.mtfFreq;
        int remF = this.nMTF;
        int gs = 0;
        for (int nPart = nGroups; nPart > 0; --nPart) {
            int aFreq;
            int tFreq = remF / nPart;
            int ge = gs - 1;
            int a2 = alphaSize - 1;
            for (aFreq = 0; aFreq < tFreq && ge < a2; aFreq += mtfFreq[++ge]) {
            }
            if (ge > gs && nPart != nGroups && nPart != 1 && (nGroups - nPart & 1) != 0) {
                aFreq -= mtfFreq[ge--];
            }
            byte[] len_np = len[nPart - 1];
            int v2 = alphaSize;
            while (--v2 >= 0) {
                if (v2 >= gs && v2 <= ge) {
                    len_np[v2] = 0;
                    continue;
                }
                len_np[v2] = 15;
            }
            gs = ge + 1;
            remF -= aFreq;
        }
    }

    private int sendMTFValues1(int nGroups, int alphaSize) {
        Data dataShadow = this.data;
        int[][] rfreq = dataShadow.sendMTFValues_rfreq;
        int[] fave = dataShadow.sendMTFValues_fave;
        short[] cost = dataShadow.sendMTFValues_cost;
        char[] sfmap = dataShadow.sfmap;
        byte[] selector = dataShadow.selector;
        byte[][] len = dataShadow.sendMTFValues_len;
        byte[] len_0 = len[0];
        byte[] len_1 = len[1];
        byte[] len_2 = len[2];
        byte[] len_3 = len[3];
        byte[] len_4 = len[4];
        byte[] len_5 = len[5];
        int nMTFShadow = this.nMTF;
        int nSelectors = 0;
        for (int iter = 0; iter < 4; ++iter) {
            int t2 = nGroups;
            while (--t2 >= 0) {
                fave[t2] = 0;
                int[] rfreqt = rfreq[t2];
                int i2 = alphaSize;
                while (--i2 >= 0) {
                    rfreqt[i2] = 0;
                }
            }
            nSelectors = 0;
            int gs = 0;
            while (gs < this.nMTF) {
                int n2;
                int ge = Math.min(gs + 50 - 1, nMTFShadow - 1);
                int mask = -1;
                if (nGroups == 6) {
                    short cost0 = 0;
                    short cost1 = 0;
                    n2 = 0;
                    short cost3 = 0;
                    short cost4 = 0;
                    short cost5 = 0;
                    for (int i3 = gs; i3 <= ge; ++i3) {
                        char icv = sfmap[i3];
                        cost0 = (short)(cost0 + (short)(len_0[icv] & 0xFFFFFFFF));
                        cost1 = (short)(cost1 + (short)(len_1[icv] & 0xFFFFFFFF));
                        n2 = (short)(n2 + (short)(len_2[icv] & 0xFFFFFFFF));
                        cost3 = (short)(cost3 + (short)(len_3[icv] & 0xFFFFFFFF));
                        cost4 = (short)(cost4 + (short)(len_4[icv] & 0xFFFFFFFF));
                        cost5 = (short)(cost5 + (short)(len_5[icv] & 0xFFFFFFFF));
                    }
                    cost[0] = cost0;
                    cost[1] = cost1;
                    cost[2] = n2;
                    cost[3] = cost3;
                    cost[4] = cost4;
                    cost[5] = cost5;
                } else {
                    int t3 = nGroups;
                    while (--t3 >= 0) {
                        cost[t3] = 0;
                    }
                    for (int i4 = gs; i4 <= ge; ++i4) {
                        char icv = sfmap[i4];
                        n2 = nGroups;
                        while (--n2 >= 0) {
                            int n3 = n2;
                            cost[n3] = (short)(cost[n3] + (short)(len[n2][icv] & 0xFFFFFFFF));
                        }
                    }
                }
                int bt = -1;
                int t5 = nGroups;
                n2 = 999999999;
                while (--t5 >= 0) {
                    int cost_t = cost[t5];
                    if (cost_t >= n2) continue;
                    n2 = cost_t;
                    bt = t5;
                }
                int n4 = bt;
                fave[n4] = fave[n4] + 1;
                selector[nSelectors] = (byte)bt;
                ++nSelectors;
                int[] rfreq_bt = rfreq[bt];
                for (n2 = gs; n2 <= ge; ++n2) {
                    char c2 = sfmap[n2];
                    rfreq_bt[c2] = rfreq_bt[c2] + 1;
                }
                gs = ge + 1;
            }
            for (int t3 = 0; t3 < nGroups; ++t3) {
                BZip2CompressorOutputStream.hbMakeCodeLengths(len[t3], rfreq[t3], this.data, alphaSize, 20);
            }
        }
        return nSelectors;
    }

    private void sendMTFValues2(int nGroups, int nSelectors) {
        Data dataShadow = this.data;
        byte[] pos = dataShadow.sendMTFValues2_pos;
        int i2 = nGroups;
        while (--i2 >= 0) {
            pos[i2] = (byte)i2;
        }
        for (i2 = 0; i2 < nSelectors; ++i2) {
            byte ll_i = dataShadow.selector[i2];
            byte tmp = pos[0];
            int j2 = 0;
            while (ll_i != tmp) {
                byte tmp2 = tmp;
                tmp = pos[++j2];
                pos[j2] = tmp2;
            }
            pos[0] = tmp;
            dataShadow.selectorMtf[i2] = (byte)j2;
        }
    }

    private void sendMTFValues3(int nGroups, int alphaSize) {
        int[][] code = this.data.sendMTFValues_code;
        byte[][] len = this.data.sendMTFValues_len;
        for (int t2 = 0; t2 < nGroups; ++t2) {
            int minLen = 32;
            int maxLen = 0;
            byte[] len_t = len[t2];
            int i2 = alphaSize;
            while (--i2 >= 0) {
                int l2 = len_t[i2] & 0xFF;
                if (l2 > maxLen) {
                    maxLen = l2;
                }
                if (l2 >= minLen) continue;
                minLen = l2;
            }
            BZip2CompressorOutputStream.hbAssignCodes(code[t2], len[t2], minLen, maxLen, alphaSize);
        }
    }

    private void sendMTFValues4() throws IOException {
        boolean[] inUse = this.data.inUse;
        boolean[] inUse16 = this.data.sentMTFValues4_inUse16;
        int i2 = 16;
        block0: while (--i2 >= 0) {
            inUse16[i2] = false;
            int i16 = i2 * 16;
            int j2 = 16;
            while (--j2 >= 0) {
                if (!inUse[i16 + j2]) continue;
                inUse16[i2] = true;
                continue block0;
            }
        }
        for (i2 = 0; i2 < 16; ++i2) {
            this.bsW(1, inUse16[i2] ? 1 : 0);
        }
        OutputStream outShadow = this.out;
        int bsLiveShadow = this.bsLive;
        int bsBuffShadow = this.bsBuff;
        for (int i3 = 0; i3 < 16; ++i3) {
            if (!inUse16[i3]) continue;
            int i16 = i3 * 16;
            for (int j3 = 0; j3 < 16; ++j3) {
                while (bsLiveShadow >= 8) {
                    outShadow.write(bsBuffShadow >> 24);
                    bsBuffShadow <<= 8;
                    bsLiveShadow -= 8;
                }
                if (inUse[i16 + j3]) {
                    bsBuffShadow |= 1 << 32 - bsLiveShadow - 1;
                }
                ++bsLiveShadow;
            }
        }
        this.bsBuff = bsBuffShadow;
        this.bsLive = bsLiveShadow;
    }

    private void sendMTFValues5(int nGroups, int nSelectors) throws IOException {
        this.bsW(3, nGroups);
        this.bsW(15, nSelectors);
        OutputStream outShadow = this.out;
        byte[] selectorMtf = this.data.selectorMtf;
        int bsLiveShadow = this.bsLive;
        int bsBuffShadow = this.bsBuff;
        for (int i2 = 0; i2 < nSelectors; ++i2) {
            int hj = selectorMtf[i2] & 0xFF;
            for (int j2 = 0; j2 < hj; ++j2) {
                while (bsLiveShadow >= 8) {
                    outShadow.write(bsBuffShadow >> 24);
                    bsBuffShadow <<= 8;
                    bsLiveShadow -= 8;
                }
                bsBuffShadow |= 1 << 32 - bsLiveShadow - 1;
                ++bsLiveShadow;
            }
            while (bsLiveShadow >= 8) {
                outShadow.write(bsBuffShadow >> 24);
                bsBuffShadow <<= 8;
                bsLiveShadow -= 8;
            }
            ++bsLiveShadow;
        }
        this.bsBuff = bsBuffShadow;
        this.bsLive = bsLiveShadow;
    }

    private void sendMTFValues6(int nGroups, int alphaSize) throws IOException {
        byte[][] len = this.data.sendMTFValues_len;
        OutputStream outShadow = this.out;
        int bsLiveShadow = this.bsLive;
        int bsBuffShadow = this.bsBuff;
        for (int t2 = 0; t2 < nGroups; ++t2) {
            byte[] len_t = len[t2];
            int curr = len_t[0] & 0xFF;
            while (bsLiveShadow >= 8) {
                outShadow.write(bsBuffShadow >> 24);
                bsBuffShadow <<= 8;
                bsLiveShadow -= 8;
            }
            bsBuffShadow |= curr << 32 - bsLiveShadow - 5;
            bsLiveShadow += 5;
            for (int i2 = 0; i2 < alphaSize; ++i2) {
                int lti = len_t[i2] & 0xFF;
                while (curr < lti) {
                    while (bsLiveShadow >= 8) {
                        outShadow.write(bsBuffShadow >> 24);
                        bsBuffShadow <<= 8;
                        bsLiveShadow -= 8;
                    }
                    bsBuffShadow |= 2 << 32 - bsLiveShadow - 2;
                    bsLiveShadow += 2;
                    ++curr;
                }
                while (curr > lti) {
                    while (bsLiveShadow >= 8) {
                        outShadow.write(bsBuffShadow >> 24);
                        bsBuffShadow <<= 8;
                        bsLiveShadow -= 8;
                    }
                    bsBuffShadow |= 3 << 32 - bsLiveShadow - 2;
                    bsLiveShadow += 2;
                    --curr;
                }
                while (bsLiveShadow >= 8) {
                    outShadow.write(bsBuffShadow >> 24);
                    bsBuffShadow <<= 8;
                    bsLiveShadow -= 8;
                }
                ++bsLiveShadow;
            }
        }
        this.bsBuff = bsBuffShadow;
        this.bsLive = bsLiveShadow;
    }

    private void sendMTFValues7() throws IOException {
        Data dataShadow = this.data;
        byte[][] len = dataShadow.sendMTFValues_len;
        int[][] code = dataShadow.sendMTFValues_code;
        OutputStream outShadow = this.out;
        byte[] selector = dataShadow.selector;
        char[] sfmap = dataShadow.sfmap;
        int nMTFShadow = this.nMTF;
        int selCtr = 0;
        int bsLiveShadow = this.bsLive;
        int bsBuffShadow = this.bsBuff;
        int gs = 0;
        while (gs < nMTFShadow) {
            int ge = Math.min(gs + 50 - 1, nMTFShadow - 1);
            int selector_selCtr = selector[selCtr] & 0xFF;
            int[] code_selCtr = code[selector_selCtr];
            byte[] len_selCtr = len[selector_selCtr];
            while (gs <= ge) {
                char sfmap_i = sfmap[gs];
                while (bsLiveShadow >= 8) {
                    outShadow.write(bsBuffShadow >> 24);
                    bsBuffShadow <<= 8;
                    bsLiveShadow -= 8;
                }
                int n2 = len_selCtr[sfmap_i] & 0xFF;
                bsBuffShadow |= code_selCtr[sfmap_i] << 32 - bsLiveShadow - n2;
                bsLiveShadow += n2;
                ++gs;
            }
            gs = ge + 1;
            ++selCtr;
        }
        this.bsBuff = bsBuffShadow;
        this.bsLive = bsLiveShadow;
    }

    @Override
    public void write(byte[] buf, int offs, int len) throws IOException {
        if (offs < 0) {
            throw new IndexOutOfBoundsException("offs(" + offs + ") < 0.");
        }
        if (len < 0) {
            throw new IndexOutOfBoundsException("len(" + len + ") < 0.");
        }
        if (offs + len > buf.length) {
            throw new IndexOutOfBoundsException("offs(" + offs + ") + len(" + len + ") > buf.length(" + buf.length + ").");
        }
        if (this.closed) {
            throw new IOException("Stream closed");
        }
        int hi = offs + len;
        while (offs < hi) {
            this.write0(buf[offs++]);
        }
    }

    @Override
    public void write(int b2) throws IOException {
        if (this.closed) {
            throw new IOException("Closed");
        }
        this.write0(b2);
    }

    private void write0(int b2) throws IOException {
        if (this.currentChar != -1) {
            if (this.currentChar == (b2 &= 0xFF)) {
                if (++this.runLength > 254) {
                    this.writeRun();
                    this.currentChar = -1;
                    this.runLength = 0;
                }
            } else {
                this.writeRun();
                this.runLength = 1;
                this.currentChar = b2;
            }
        } else {
            this.currentChar = b2 & 0xFF;
            ++this.runLength;
        }
    }

    private void writeRun() throws IOException {
        int lastShadow = this.last;
        if (lastShadow < this.allowableBlockSize) {
            int currentCharShadow = this.currentChar;
            Data dataShadow = this.data;
            dataShadow.inUse[currentCharShadow] = true;
            byte ch = (byte)currentCharShadow;
            int runLengthShadow = this.runLength;
            this.crc.updateCRC(currentCharShadow, runLengthShadow);
            switch (runLengthShadow) {
                case 1: {
                    dataShadow.block[lastShadow + 2] = ch;
                    this.last = lastShadow + 1;
                    break;
                }
                case 2: {
                    dataShadow.block[lastShadow + 2] = ch;
                    dataShadow.block[lastShadow + 3] = ch;
                    this.last = lastShadow + 2;
                    break;
                }
                case 3: {
                    byte[] block = dataShadow.block;
                    block[lastShadow + 2] = ch;
                    block[lastShadow + 3] = ch;
                    block[lastShadow + 4] = ch;
                    this.last = lastShadow + 3;
                    break;
                }
                default: {
                    dataShadow.inUse[runLengthShadow -= 4] = true;
                    byte[] block = dataShadow.block;
                    block[lastShadow + 2] = ch;
                    block[lastShadow + 3] = ch;
                    block[lastShadow + 4] = ch;
                    block[lastShadow + 5] = ch;
                    block[lastShadow + 6] = (byte)runLengthShadow;
                    this.last = lastShadow + 5;
                    break;
                }
            }
        } else {
            this.endBlock();
            this.initBlock();
            this.writeRun();
        }
    }

    static final class Data {
        final boolean[] inUse = new boolean[256];
        final byte[] unseqToSeq = new byte[256];
        final int[] mtfFreq = new int[258];
        final byte[] selector = new byte[18002];
        final byte[] selectorMtf = new byte[18002];
        final byte[] generateMTFValues_yy = new byte[256];
        final byte[][] sendMTFValues_len = new byte[6][258];
        final int[][] sendMTFValues_rfreq = new int[6][258];
        final int[] sendMTFValues_fave = new int[6];
        final short[] sendMTFValues_cost = new short[6];
        final int[][] sendMTFValues_code = new int[6][258];
        final byte[] sendMTFValues2_pos = new byte[6];
        final boolean[] sentMTFValues4_inUse16 = new boolean[16];
        final int[] heap = new int[260];
        final int[] weight = new int[516];
        final int[] parent = new int[516];
        final byte[] block;
        final int[] fmap;
        final char[] sfmap;
        int origPtr;

        Data(int blockSize100k) {
            int n2 = blockSize100k * 100000;
            this.block = new byte[n2 + 1 + 20];
            this.fmap = new int[n2];
            this.sfmap = new char[2 * n2];
        }
    }
}

