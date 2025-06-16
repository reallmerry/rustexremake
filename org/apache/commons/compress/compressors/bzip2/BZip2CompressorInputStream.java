/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.bzip2;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.util.Arrays;
import org.apache.commons.compress.compressors.CompressorInputStream;
import org.apache.commons.compress.compressors.bzip2.BZip2Constants;
import org.apache.commons.compress.compressors.bzip2.CRC;
import org.apache.commons.compress.compressors.bzip2.Rand;
import org.apache.commons.compress.utils.BitInputStream;
import org.apache.commons.compress.utils.CloseShieldFilterInputStream;
import org.apache.commons.compress.utils.InputStreamStatistics;

public class BZip2CompressorInputStream
extends CompressorInputStream
implements BZip2Constants,
InputStreamStatistics {
    private static final int EOF = 0;
    private static final int START_BLOCK_STATE = 1;
    private static final int RAND_PART_A_STATE = 2;
    private static final int RAND_PART_B_STATE = 3;
    private static final int RAND_PART_C_STATE = 4;
    private static final int NO_RAND_PART_A_STATE = 5;
    private static final int NO_RAND_PART_B_STATE = 6;
    private static final int NO_RAND_PART_C_STATE = 7;
    private int last;
    private int origPtr;
    private int blockSize100k;
    private boolean blockRandomised;
    private final CRC crc = new CRC();
    private int nInUse;
    private BitInputStream bin;
    private final boolean decompressConcatenated;
    private int currentState = 1;
    private int storedBlockCRC;
    private int storedCombinedCRC;
    private int computedBlockCRC;
    private int computedCombinedCRC;
    private int su_count;
    private int su_ch2;
    private int su_chPrev;
    private int su_i2;
    private int su_j2;
    private int su_rNToGo;
    private int su_rTPos;
    private int su_tPos;
    private char su_z;
    private Data data;

    private static boolean bsGetBit(BitInputStream bin) throws IOException {
        return BZip2CompressorInputStream.bsR(bin, 1) != 0;
    }

    private static int bsGetInt(BitInputStream bin) throws IOException {
        return BZip2CompressorInputStream.bsR(bin, 32);
    }

    private static char bsGetUByte(BitInputStream bin) throws IOException {
        return (char)BZip2CompressorInputStream.bsR(bin, 8);
    }

    private static int bsR(BitInputStream bin, int n2) throws IOException {
        long thech = bin.readBits(n2);
        if (thech < 0L) {
            throw new IOException("Unexpected end of stream");
        }
        return (int)thech;
    }

    private static void checkBounds(int checkVal, int limitExclusive, String name) throws IOException {
        if (checkVal < 0) {
            throw new IOException("Corrupted input, " + name + " value negative");
        }
        if (checkVal >= limitExclusive) {
            throw new IOException("Corrupted input, " + name + " value too big");
        }
    }

    private static void hbCreateDecodeTables(int[] limit, int[] base, int[] perm, char[] length, int minLen, int maxLen, int alphaSize) throws IOException {
        int i2;
        int pp = 0;
        for (i2 = minLen; i2 <= maxLen; ++i2) {
            for (int j2 = 0; j2 < alphaSize; ++j2) {
                if (length[j2] != i2) continue;
                perm[pp++] = j2;
            }
        }
        i2 = 23;
        while (--i2 > 0) {
            base[i2] = 0;
            limit[i2] = 0;
        }
        for (i2 = 0; i2 < alphaSize; ++i2) {
            char l2 = length[i2];
            BZip2CompressorInputStream.checkBounds(l2, 258, "length");
            int n2 = l2 + '\u0001';
            base[n2] = base[n2] + 1;
        }
        int b2 = base[0];
        for (i2 = 1; i2 < 23; ++i2) {
            base[i2] = b2 += base[i2];
        }
        int vec = 0;
        int b3 = base[i2];
        for (i2 = minLen; i2 <= maxLen; ++i2) {
            int nb2 = base[i2 + 1];
            b3 = nb2;
            limit[i2] = (vec += nb2 - b3) - 1;
            vec <<= 1;
        }
        for (i2 = minLen + 1; i2 <= maxLen; ++i2) {
            base[i2] = (limit[i2 - 1] + 1 << 1) - base[i2];
        }
    }

    public static boolean matches(byte[] signature, int length) {
        return length >= 3 && signature[0] == 66 && signature[1] == 90 && signature[2] == 104;
    }

    public BZip2CompressorInputStream(InputStream in) throws IOException {
        this(in, false);
    }

    public BZip2CompressorInputStream(InputStream in, boolean decompressConcatenated) throws IOException {
        this.bin = new BitInputStream(in == System.in ? new CloseShieldFilterInputStream(in) : in, ByteOrder.BIG_ENDIAN);
        this.decompressConcatenated = decompressConcatenated;
        this.init(true);
        this.initBlock();
    }

    @Override
    public void close() throws IOException {
        BitInputStream inShadow = this.bin;
        if (inShadow != null) {
            try {
                inShadow.close();
            } finally {
                this.data = null;
                this.bin = null;
            }
        }
    }

    private boolean complete() throws IOException {
        this.storedCombinedCRC = BZip2CompressorInputStream.bsGetInt(this.bin);
        this.currentState = 0;
        this.data = null;
        if (this.storedCombinedCRC != this.computedCombinedCRC) {
            throw new IOException("BZip2 CRC error");
        }
        return !this.decompressConcatenated || !this.init(false);
    }

    private void createHuffmanDecodingTables(int alphaSize, int nGroups) throws IOException {
        Data dataShadow = this.data;
        char[][] len = dataShadow.temp_charArray2d;
        int[] minLens = dataShadow.minLens;
        int[][] limit = dataShadow.limit;
        int[][] base = dataShadow.base;
        int[][] perm = dataShadow.perm;
        for (int t2 = 0; t2 < nGroups; ++t2) {
            int minLen = 32;
            int maxLen = 0;
            char[] len_t = len[t2];
            int i2 = alphaSize;
            while (--i2 >= 0) {
                int lent = len_t[i2];
                if (lent > maxLen) {
                    maxLen = lent;
                }
                if (lent >= minLen) continue;
                minLen = lent;
            }
            BZip2CompressorInputStream.hbCreateDecodeTables(limit[t2], base[t2], perm[t2], len[t2], minLen, maxLen, alphaSize);
            minLens[t2] = minLen;
        }
    }

    private void endBlock() throws IOException {
        this.computedBlockCRC = this.crc.getFinalCRC();
        if (this.storedBlockCRC != this.computedBlockCRC) {
            this.computedCombinedCRC = this.storedCombinedCRC << 1 | this.storedCombinedCRC >>> 31;
            this.computedCombinedCRC ^= this.storedBlockCRC;
            throw new IOException("BZip2 CRC error");
        }
        this.computedCombinedCRC = this.computedCombinedCRC << 1 | this.computedCombinedCRC >>> 31;
        this.computedCombinedCRC ^= this.computedBlockCRC;
    }

    private void getAndMoveToFrontDecode() throws IOException {
        BitInputStream bin = this.bin;
        this.origPtr = BZip2CompressorInputStream.bsR(bin, 24);
        this.recvDecodingTables();
        Data dataShadow = this.data;
        byte[] ll8 = dataShadow.ll8;
        int[] unzftab = dataShadow.unzftab;
        byte[] selector = dataShadow.selector;
        byte[] seqToUnseq = dataShadow.seqToUnseq;
        char[] yy = dataShadow.getAndMoveToFrontDecode_yy;
        int[] minLens = dataShadow.minLens;
        int[][] limit = dataShadow.limit;
        int[][] base = dataShadow.base;
        int[][] perm = dataShadow.perm;
        int limitLast = this.blockSize100k * 100000;
        int i2 = 256;
        while (--i2 >= 0) {
            yy[i2] = (char)i2;
            unzftab[i2] = 0;
        }
        int groupNo = 0;
        int groupPos = 49;
        int eob = this.nInUse + 1;
        int nextSym = this.getAndMoveToFrontDecode0();
        int lastShadow = -1;
        int zt = selector[groupNo] & 0xFF;
        BZip2CompressorInputStream.checkBounds(zt, 6, "zt");
        int[] base_zt = base[zt];
        int[] limit_zt = limit[zt];
        int[] perm_zt = perm[zt];
        int minLens_zt = minLens[zt];
        while (nextSym != eob) {
            if (nextSym == 0 || nextSym == 1) {
                int s2 = -1;
                int n2 = 1;
                while (true) {
                    if (nextSym == 0) {
                        s2 += n2;
                    } else {
                        if (nextSym != 1) break;
                        s2 += n2 << 1;
                    }
                    if (groupPos == 0) {
                        groupPos = 49;
                        BZip2CompressorInputStream.checkBounds(++groupNo, 18002, "groupNo");
                        zt = selector[groupNo] & 0xFF;
                        BZip2CompressorInputStream.checkBounds(zt, 6, "zt");
                        base_zt = base[zt];
                        limit_zt = limit[zt];
                        perm_zt = perm[zt];
                        minLens_zt = minLens[zt];
                    } else {
                        --groupPos;
                    }
                    int zn = minLens_zt;
                    BZip2CompressorInputStream.checkBounds(zn, 258, "zn");
                    int zvec = BZip2CompressorInputStream.bsR(bin, zn);
                    while (zvec > limit_zt[zn]) {
                        BZip2CompressorInputStream.checkBounds(++zn, 258, "zn");
                        zvec = zvec << 1 | BZip2CompressorInputStream.bsR(bin, 1);
                    }
                    int tmp = zvec - base_zt[zn];
                    BZip2CompressorInputStream.checkBounds(tmp, 258, "zvec");
                    nextSym = perm_zt[tmp];
                    n2 <<= 1;
                }
                BZip2CompressorInputStream.checkBounds(s2, this.data.ll8.length, "s");
                char yy0 = yy[0];
                BZip2CompressorInputStream.checkBounds(yy0, 256, "yy");
                byte ch = seqToUnseq[yy0];
                int n3 = ch & 0xFF;
                unzftab[n3] = unzftab[n3] + (s2 + 1);
                int from = ++lastShadow;
                BZip2CompressorInputStream.checkBounds(lastShadow += s2, this.data.ll8.length, "lastShadow");
                Arrays.fill(ll8, from, lastShadow + 1, ch);
                if (lastShadow < limitLast) continue;
                throw new IOException("Block overrun while expanding RLE in MTF, " + lastShadow + " exceeds " + limitLast);
            }
            if (++lastShadow >= limitLast) {
                throw new IOException("Block overrun in MTF, " + lastShadow + " exceeds " + limitLast);
            }
            BZip2CompressorInputStream.checkBounds(nextSym, 257, "nextSym");
            char tmp = yy[nextSym - 1];
            BZip2CompressorInputStream.checkBounds(tmp, 256, "yy");
            int n4 = seqToUnseq[tmp] & 0xFF;
            unzftab[n4] = unzftab[n4] + 1;
            ll8[lastShadow] = seqToUnseq[tmp];
            if (nextSym <= 16) {
                int j2 = nextSym - 1;
                while (j2 > 0) {
                    yy[j2--] = yy[j2];
                }
            } else {
                System.arraycopy(yy, 0, yy, 1, nextSym - 1);
            }
            yy[0] = tmp;
            if (groupPos == 0) {
                groupPos = 49;
                BZip2CompressorInputStream.checkBounds(++groupNo, 18002, "groupNo");
                zt = selector[groupNo] & 0xFF;
                BZip2CompressorInputStream.checkBounds(zt, 6, "zt");
                base_zt = base[zt];
                limit_zt = limit[zt];
                perm_zt = perm[zt];
                minLens_zt = minLens[zt];
            } else {
                --groupPos;
            }
            int zn = minLens_zt;
            BZip2CompressorInputStream.checkBounds(zn, 258, "zn");
            int zvec = BZip2CompressorInputStream.bsR(bin, zn);
            while (zvec > limit_zt[zn]) {
                BZip2CompressorInputStream.checkBounds(++zn, 258, "zn");
                zvec = zvec << 1 | BZip2CompressorInputStream.bsR(bin, 1);
            }
            int idx = zvec - base_zt[zn];
            BZip2CompressorInputStream.checkBounds(idx, 258, "zvec");
            nextSym = perm_zt[idx];
        }
        this.last = lastShadow;
    }

    private int getAndMoveToFrontDecode0() throws IOException {
        Data dataShadow = this.data;
        int zt = dataShadow.selector[0] & 0xFF;
        BZip2CompressorInputStream.checkBounds(zt, 6, "zt");
        int[] limit_zt = dataShadow.limit[zt];
        int zn = dataShadow.minLens[zt];
        BZip2CompressorInputStream.checkBounds(zn, 258, "zn");
        int zvec = BZip2CompressorInputStream.bsR(this.bin, zn);
        while (zvec > limit_zt[zn]) {
            BZip2CompressorInputStream.checkBounds(++zn, 258, "zn");
            zvec = zvec << 1 | BZip2CompressorInputStream.bsR(this.bin, 1);
        }
        int tmp = zvec - dataShadow.base[zt][zn];
        BZip2CompressorInputStream.checkBounds(tmp, 258, "zvec");
        return dataShadow.perm[zt][tmp];
    }

    @Override
    public long getCompressedCount() {
        return this.bin.getBytesRead();
    }

    private boolean init(boolean isFirstStream) throws IOException {
        int magic0;
        if (null == this.bin) {
            throw new IOException("No InputStream");
        }
        if (!isFirstStream) {
            this.bin.clearBitCache();
        }
        if ((magic0 = this.readNextByte(this.bin)) == -1 && !isFirstStream) {
            return false;
        }
        int magic1 = this.readNextByte(this.bin);
        int magic2 = this.readNextByte(this.bin);
        if (magic0 != 66 || magic1 != 90 || magic2 != 104) {
            throw new IOException(isFirstStream ? "Stream is not in the BZip2 format" : "Garbage after a valid BZip2 stream");
        }
        int blockSize = this.readNextByte(this.bin);
        if (blockSize < 49 || blockSize > 57) {
            throw new IOException("BZip2 block size is invalid");
        }
        this.blockSize100k = blockSize - 48;
        this.computedCombinedCRC = 0;
        return true;
    }

    private void initBlock() throws IOException {
        char magic5;
        char magic4;
        char magic3;
        char magic2;
        char magic1;
        char magic0;
        BitInputStream bin;
        block3: {
            bin = this.bin;
            do {
                magic0 = BZip2CompressorInputStream.bsGetUByte(bin);
                magic1 = BZip2CompressorInputStream.bsGetUByte(bin);
                magic2 = BZip2CompressorInputStream.bsGetUByte(bin);
                magic3 = BZip2CompressorInputStream.bsGetUByte(bin);
                magic4 = BZip2CompressorInputStream.bsGetUByte(bin);
                magic5 = BZip2CompressorInputStream.bsGetUByte(bin);
                if (magic0 != '\u0017' || magic1 != 'r' || magic2 != 'E' || magic3 != '8' || magic4 != 'P' || magic5 != '\u0090') break block3;
            } while (!this.complete());
            return;
        }
        if (magic0 != '1' || magic1 != 'A' || magic2 != 'Y' || magic3 != '&' || magic4 != 'S' || magic5 != 'Y') {
            this.currentState = 0;
            throw new IOException("Bad block header");
        }
        this.storedBlockCRC = BZip2CompressorInputStream.bsGetInt(bin);
        boolean bl = this.blockRandomised = BZip2CompressorInputStream.bsR(bin, 1) == 1;
        if (this.data == null) {
            this.data = new Data(this.blockSize100k);
        }
        this.getAndMoveToFrontDecode();
        this.crc.initializeCRC();
        this.currentState = 1;
    }

    private void makeMaps() {
        boolean[] inUse = this.data.inUse;
        byte[] seqToUnseq = this.data.seqToUnseq;
        int nInUseShadow = 0;
        for (int i2 = 0; i2 < 256; ++i2) {
            if (!inUse[i2]) continue;
            seqToUnseq[nInUseShadow++] = (byte)i2;
        }
        this.nInUse = nInUseShadow;
    }

    @Override
    public int read() throws IOException {
        if (this.bin != null) {
            int r2 = this.read0();
            this.count(r2 < 0 ? -1 : 1);
            return r2;
        }
        throw new IOException("Stream closed");
    }

    @Override
    public int read(byte[] dest, int offs, int len) throws IOException {
        int b2;
        if (offs < 0) {
            throw new IndexOutOfBoundsException("offs(" + offs + ") < 0.");
        }
        if (len < 0) {
            throw new IndexOutOfBoundsException("len(" + len + ") < 0.");
        }
        if (offs + len > dest.length) {
            throw new IndexOutOfBoundsException("offs(" + offs + ") + len(" + len + ") > dest.length(" + dest.length + ").");
        }
        if (this.bin == null) {
            throw new IOException("Stream closed");
        }
        if (len == 0) {
            return 0;
        }
        int hi = offs + len;
        int destOffs = offs;
        while (destOffs < hi && (b2 = this.read0()) >= 0) {
            dest[destOffs++] = (byte)b2;
            this.count(1);
        }
        return destOffs == offs ? -1 : destOffs - offs;
    }

    private int read0() throws IOException {
        switch (this.currentState) {
            case 0: {
                return -1;
            }
            case 1: {
                return this.setupBlock();
            }
            case 2: {
                throw new IllegalStateException();
            }
            case 3: {
                return this.setupRandPartB();
            }
            case 4: {
                return this.setupRandPartC();
            }
            case 5: {
                throw new IllegalStateException();
            }
            case 6: {
                return this.setupNoRandPartB();
            }
            case 7: {
                return this.setupNoRandPartC();
            }
        }
        throw new IllegalStateException();
    }

    private int readNextByte(BitInputStream in) throws IOException {
        long b2 = in.readBits(8);
        return (int)b2;
    }

    private void recvDecodingTables() throws IOException {
        int i2;
        BitInputStream bin = this.bin;
        Data dataShadow = this.data;
        boolean[] inUse = dataShadow.inUse;
        byte[] pos = dataShadow.recvDecodingTables_pos;
        byte[] selector = dataShadow.selector;
        byte[] selectorMtf = dataShadow.selectorMtf;
        int inUse16 = 0;
        for (i2 = 0; i2 < 16; ++i2) {
            if (!BZip2CompressorInputStream.bsGetBit(bin)) continue;
            inUse16 |= 1 << i2;
        }
        Arrays.fill(inUse, false);
        for (i2 = 0; i2 < 16; ++i2) {
            if ((inUse16 & 1 << i2) == 0) continue;
            int i16 = i2 << 4;
            for (int j2 = 0; j2 < 16; ++j2) {
                if (!BZip2CompressorInputStream.bsGetBit(bin)) continue;
                inUse[i16 + j2] = true;
            }
        }
        this.makeMaps();
        int alphaSize = this.nInUse + 2;
        int nGroups = BZip2CompressorInputStream.bsR(bin, 3);
        int selectors = BZip2CompressorInputStream.bsR(bin, 15);
        if (selectors < 0) {
            throw new IOException("Corrupted input, nSelectors value negative");
        }
        BZip2CompressorInputStream.checkBounds(alphaSize, 259, "alphaSize");
        BZip2CompressorInputStream.checkBounds(nGroups, 7, "nGroups");
        for (int i3 = 0; i3 < selectors; ++i3) {
            int j3 = 0;
            while (BZip2CompressorInputStream.bsGetBit(bin)) {
                ++j3;
            }
            if (i3 >= 18002) continue;
            selectorMtf[i3] = (byte)j3;
        }
        int nSelectors = Math.min(selectors, 18002);
        int v2 = nGroups;
        while (--v2 >= 0) {
            pos[v2] = (byte)v2;
        }
        for (int i4 = 0; i4 < nSelectors; ++i4) {
            int v3;
            BZip2CompressorInputStream.checkBounds(v3, 6, "selectorMtf");
            byte tmp = pos[v3];
            for (v3 = selectorMtf[i4] & 0xFF; v3 > 0; --v3) {
                pos[v3] = pos[v3 - 1];
            }
            pos[0] = tmp;
            selector[i4] = tmp;
        }
        char[][] len = dataShadow.temp_charArray2d;
        for (int t2 = 0; t2 < nGroups; ++t2) {
            int curr = BZip2CompressorInputStream.bsR(bin, 5);
            char[] len_t = len[t2];
            for (int i5 = 0; i5 < alphaSize; ++i5) {
                while (BZip2CompressorInputStream.bsGetBit(bin)) {
                    curr += BZip2CompressorInputStream.bsGetBit(bin) ? -1 : 1;
                }
                len_t[i5] = (char)curr;
            }
        }
        this.createHuffmanDecodingTables(alphaSize, nGroups);
    }

    private int setupBlock() throws IOException {
        int i2;
        if (this.currentState == 0 || this.data == null) {
            return -1;
        }
        int[] cftab = this.data.cftab;
        int ttLen = this.last + 1;
        int[] tt = this.data.initTT(ttLen);
        byte[] ll8 = this.data.ll8;
        cftab[0] = 0;
        System.arraycopy(this.data.unzftab, 0, cftab, 1, 256);
        int c2 = cftab[0];
        for (i2 = 1; i2 <= 256; ++i2) {
            cftab[i2] = c2 += cftab[i2];
        }
        i2 = 0;
        int lastShadow = this.last;
        while (i2 <= lastShadow) {
            int tmp;
            int n2 = ll8[i2] & 0xFF;
            cftab[n2] = cftab[n2] + 1;
            BZip2CompressorInputStream.checkBounds(tmp, ttLen, "tt index");
            tt[tmp] = i2++;
        }
        if (this.origPtr < 0 || this.origPtr >= tt.length) {
            throw new IOException("Stream corrupted");
        }
        this.su_tPos = tt[this.origPtr];
        this.su_count = 0;
        this.su_i2 = 0;
        this.su_ch2 = 256;
        if (this.blockRandomised) {
            this.su_rNToGo = 0;
            this.su_rTPos = 0;
            return this.setupRandPartA();
        }
        return this.setupNoRandPartA();
    }

    private int setupNoRandPartA() throws IOException {
        if (this.su_i2 <= this.last) {
            int su_ch2Shadow;
            this.su_chPrev = this.su_ch2;
            this.su_ch2 = su_ch2Shadow = this.data.ll8[this.su_tPos] & 0xFF;
            BZip2CompressorInputStream.checkBounds(this.su_tPos, this.data.tt.length, "su_tPos");
            this.su_tPos = this.data.tt[this.su_tPos];
            ++this.su_i2;
            this.currentState = 6;
            this.crc.updateCRC(su_ch2Shadow);
            return su_ch2Shadow;
        }
        this.currentState = 5;
        this.endBlock();
        this.initBlock();
        return this.setupBlock();
    }

    private int setupNoRandPartB() throws IOException {
        if (this.su_ch2 != this.su_chPrev) {
            this.su_count = 1;
            return this.setupNoRandPartA();
        }
        if (++this.su_count >= 4) {
            BZip2CompressorInputStream.checkBounds(this.su_tPos, this.data.ll8.length, "su_tPos");
            this.su_z = (char)(this.data.ll8[this.su_tPos] & 0xFF);
            this.su_tPos = this.data.tt[this.su_tPos];
            this.su_j2 = 0;
            return this.setupNoRandPartC();
        }
        return this.setupNoRandPartA();
    }

    private int setupNoRandPartC() throws IOException {
        if (this.su_j2 < this.su_z) {
            int su_ch2Shadow = this.su_ch2;
            this.crc.updateCRC(su_ch2Shadow);
            ++this.su_j2;
            this.currentState = 7;
            return su_ch2Shadow;
        }
        ++this.su_i2;
        this.su_count = 0;
        return this.setupNoRandPartA();
    }

    private int setupRandPartA() throws IOException {
        if (this.su_i2 <= this.last) {
            this.su_chPrev = this.su_ch2;
            int su_ch2Shadow = this.data.ll8[this.su_tPos] & 0xFF;
            BZip2CompressorInputStream.checkBounds(this.su_tPos, this.data.tt.length, "su_tPos");
            this.su_tPos = this.data.tt[this.su_tPos];
            if (this.su_rNToGo == 0) {
                this.su_rNToGo = Rand.rNums(this.su_rTPos) - 1;
                if (++this.su_rTPos == 512) {
                    this.su_rTPos = 0;
                }
            } else {
                --this.su_rNToGo;
            }
            this.su_ch2 = su_ch2Shadow ^= this.su_rNToGo == 1 ? 1 : 0;
            ++this.su_i2;
            this.currentState = 3;
            this.crc.updateCRC(su_ch2Shadow);
            return su_ch2Shadow;
        }
        this.endBlock();
        this.initBlock();
        return this.setupBlock();
    }

    private int setupRandPartB() throws IOException {
        if (this.su_ch2 != this.su_chPrev) {
            this.currentState = 2;
            this.su_count = 1;
            return this.setupRandPartA();
        }
        if (++this.su_count < 4) {
            this.currentState = 2;
            return this.setupRandPartA();
        }
        this.su_z = (char)(this.data.ll8[this.su_tPos] & 0xFF);
        BZip2CompressorInputStream.checkBounds(this.su_tPos, this.data.tt.length, "su_tPos");
        this.su_tPos = this.data.tt[this.su_tPos];
        if (this.su_rNToGo == 0) {
            this.su_rNToGo = Rand.rNums(this.su_rTPos) - 1;
            if (++this.su_rTPos == 512) {
                this.su_rTPos = 0;
            }
        } else {
            --this.su_rNToGo;
        }
        this.su_j2 = 0;
        this.currentState = 4;
        if (this.su_rNToGo == 1) {
            this.su_z = (char)(this.su_z ^ '\u0001');
        }
        return this.setupRandPartC();
    }

    private int setupRandPartC() throws IOException {
        if (this.su_j2 < this.su_z) {
            this.crc.updateCRC(this.su_ch2);
            ++this.su_j2;
            return this.su_ch2;
        }
        this.currentState = 2;
        ++this.su_i2;
        this.su_count = 0;
        return this.setupRandPartA();
    }

    private static final class Data {
        final boolean[] inUse = new boolean[256];
        final byte[] seqToUnseq = new byte[256];
        final byte[] selector = new byte[18002];
        final byte[] selectorMtf = new byte[18002];
        final int[] unzftab = new int[256];
        final int[][] limit = new int[6][258];
        final int[][] base = new int[6][258];
        final int[][] perm = new int[6][258];
        final int[] minLens = new int[6];
        final int[] cftab = new int[257];
        final char[] getAndMoveToFrontDecode_yy = new char[256];
        final char[][] temp_charArray2d = new char[6][258];
        final byte[] recvDecodingTables_pos = new byte[6];
        int[] tt;
        final byte[] ll8;

        Data(int blockSize100k) {
            this.ll8 = new byte[blockSize100k * 100000];
        }

        int[] initTT(int length) {
            int[] ttShadow = this.tt;
            if (ttShadow == null || ttShadow.length < length) {
                this.tt = ttShadow = new int[length];
            }
            return ttShadow;
        }
    }
}

