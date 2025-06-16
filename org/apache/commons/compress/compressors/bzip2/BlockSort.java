/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.bzip2;

import java.util.Arrays;
import java.util.BitSet;
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream;

class BlockSort {
    private static final int FTAB_LENGTH = 65537;
    private static final int QSORT_STACK_SIZE = 1000;
    private static final int FALLBACK_QSORT_STACK_SIZE = 100;
    private static final int STACK_SIZE = Math.max(1000, 100);
    private static final int FALLBACK_QSORT_SMALL_THRESH = 10;
    private static final int[] INCS = new int[]{1, 4, 13, 40, 121, 364, 1093, 3280, 9841, 29524, 88573, 265720, 797161, 2391484};
    private static final int SMALL_THRESH = 20;
    private static final int DEPTH_THRESH = 10;
    private static final int WORK_FACTOR = 30;
    private static final int SETMASK = 0x200000;
    private static final int CLEARMASK = -2097153;
    private int workDone;
    private int workLimit;
    private boolean firstAttempt;
    private final int[] stack_ll = new int[STACK_SIZE];
    private final int[] stack_hh = new int[STACK_SIZE];
    private final int[] stack_dd = new int[1000];
    private final int[] mainSort_runningOrder = new int[256];
    private final int[] mainSort_copy = new int[256];
    private final boolean[] mainSort_bigDone = new boolean[256];
    private final int[] ftab = new int[65537];
    private final char[] quadrant;
    private int[] eclass;

    private static int med3(int a2, int b2, int c2) {
        return a2 < b2 ? (b2 < c2 ? b2 : (a2 < c2 ? c2 : a2)) : (b2 > c2 ? b2 : (a2 > c2 ? c2 : a2));
    }

    private static void vswap(int[] fmap, int p1, int p2, int n2) {
        n2 += p1;
        while (p1 < n2) {
            int t2 = fmap[p1];
            fmap[p1++] = fmap[p2];
            fmap[p2++] = t2;
        }
    }

    BlockSort(BZip2CompressorOutputStream.Data data) {
        this.quadrant = data.sfmap;
    }

    void blockSort(BZip2CompressorOutputStream.Data data, int last) {
        this.workLimit = 30 * last;
        this.workDone = 0;
        this.firstAttempt = true;
        if (last + 1 < 10000) {
            this.fallbackSort(data, last);
        } else {
            this.mainSort(data, last);
            if (this.firstAttempt && this.workDone > this.workLimit) {
                this.fallbackSort(data, last);
            }
        }
        int[] fmap = data.fmap;
        data.origPtr = -1;
        for (int i2 = 0; i2 <= last; ++i2) {
            if (fmap[i2] != 0) continue;
            data.origPtr = i2;
            break;
        }
    }

    private void fallbackQSort3(int[] fmap, int[] eclass, int loSt, int hiSt) {
        long r2 = 0L;
        int sp = 0;
        this.fpush(sp++, loSt, hiSt);
        while (sp > 0) {
            int n2;
            int gtHi;
            int ltLo;
            int lo;
            int[] s2;
            int hi;
            if ((hi = (s2 = this.fpop(--sp))[1]) - (lo = s2[0]) < 10) {
                this.fallbackSimpleSort(fmap, eclass, lo, hi);
                continue;
            }
            long r3 = (r2 = (r2 * 7621L + 1L) % 32768L) % 3L;
            long med = r3 == 0L ? (long)eclass[fmap[lo]] : (r3 == 1L ? (long)eclass[fmap[lo + hi >>> 1]] : (long)eclass[fmap[hi]]);
            int unLo = ltLo = lo;
            int unHi = gtHi = hi;
            while (true) {
                if (unLo <= unHi) {
                    n2 = eclass[fmap[unLo]] - (int)med;
                    if (n2 == 0) {
                        this.fswap(fmap, unLo, ltLo);
                        ++ltLo;
                        ++unLo;
                        continue;
                    }
                    if (n2 <= 0) {
                        ++unLo;
                        continue;
                    }
                }
                while (unLo <= unHi) {
                    n2 = eclass[fmap[unHi]] - (int)med;
                    if (n2 == 0) {
                        this.fswap(fmap, unHi, gtHi);
                        --gtHi;
                        --unHi;
                        continue;
                    }
                    if (n2 < 0) break;
                    --unHi;
                }
                if (unLo > unHi) break;
                this.fswap(fmap, unLo, unHi);
                ++unLo;
                --unHi;
            }
            if (gtHi < ltLo) continue;
            n2 = Math.min(ltLo - lo, unLo - ltLo);
            this.fvswap(fmap, lo, unLo - n2, n2);
            int m2 = Math.min(hi - gtHi, gtHi - unHi);
            this.fvswap(fmap, unHi + 1, hi - m2 + 1, m2);
            n2 = lo + unLo - ltLo - 1;
            m2 = hi - (gtHi - unHi) + 1;
            if (n2 - lo > hi - m2) {
                this.fpush(sp++, lo, n2);
                this.fpush(sp++, m2, hi);
                continue;
            }
            this.fpush(sp++, m2, hi);
            this.fpush(sp++, lo, n2);
        }
    }

    private void fallbackSimpleSort(int[] fmap, int[] eclass, int lo, int hi) {
        int j2;
        int ec_tmp;
        int tmp;
        int i2;
        if (lo == hi) {
            return;
        }
        if (hi - lo > 3) {
            for (i2 = hi - 4; i2 >= lo; --i2) {
                tmp = fmap[i2];
                ec_tmp = eclass[tmp];
                for (j2 = i2 + 4; j2 <= hi && ec_tmp > eclass[fmap[j2]]; j2 += 4) {
                    fmap[j2 - 4] = fmap[j2];
                }
                fmap[j2 - 4] = tmp;
            }
        }
        for (i2 = hi - 1; i2 >= lo; --i2) {
            tmp = fmap[i2];
            ec_tmp = eclass[tmp];
            for (j2 = i2 + 1; j2 <= hi && ec_tmp > eclass[fmap[j2]]; ++j2) {
                fmap[j2 - 1] = fmap[j2];
            }
            fmap[j2 - 1] = tmp;
        }
    }

    final void fallbackSort(BZip2CompressorOutputStream.Data data, int last) {
        data.block[0] = data.block[last + 1];
        this.fallbackSort(data.fmap, data.block, last + 1);
        int i2 = 0;
        while (i2 < last + 1) {
            int n2 = i2++;
            data.fmap[n2] = data.fmap[n2] - 1;
        }
        for (i2 = 0; i2 < last + 1; ++i2) {
            if (data.fmap[i2] != -1) continue;
            data.fmap[i2] = last;
            break;
        }
    }

    /*
     * Unable to fully structure code
     */
    final void fallbackSort(int[] fmap, byte[] block, int nblock) {
        ftab = new int[257];
        eclass = this.getEclass();
        for (i = 0; i < nblock; ++i) {
            eclass[i] = 0;
        }
        for (i = 0; i < nblock; ++i) {
            v0 = block[i] & 255;
            ftab[v0] = ftab[v0] + 1;
        }
        for (i = 1; i < 257; ++i) {
            v1 = i;
            ftab[v1] = ftab[v1] + ftab[i - 1];
        }
        i = 0;
        while (i < nblock) {
            j = block[i] & 255;
            ftab[j] = k = ftab[j] - 1;
            fmap[k] = i++;
        }
        nBhtab = 64 + nblock;
        bhtab = new BitSet(nBhtab);
        for (i = 0; i < 256; ++i) {
            bhtab.set(ftab[i]);
        }
        for (i = 0; i < 32; ++i) {
            bhtab.set(nblock + 2 * i);
            bhtab.clear(nblock + 2 * i + 1);
        }
        H = 1;
        block6: do {
            j = 0;
            for (i = 0; i < nblock; ++i) {
                if (bhtab.get(i)) {
                    j = i;
                }
                if ((k = fmap[i] - H) < 0) {
                    k += nblock;
                }
                eclass[k] = j;
            }
            nNotDone = 0;
            r = -1;
            block8: while (true) {
                k = r + 1;
                l = (k = bhtab.nextClearBit(k)) - 1;
                if (l >= nblock || (r = (k = bhtab.nextSetBit(k + 1)) - 1) >= nblock) continue block6;
                if (r <= l) continue;
                nNotDone += r - l + 1;
                this.fallbackQSort3(fmap, eclass, l, r);
                cc = -1;
                i = l;
                while (true) {
                    if (i <= r) ** break;
                    continue block8;
                    cc1 = eclass[fmap[i]];
                    if (cc != cc1) {
                        bhtab.set(i);
                        cc = cc1;
                    }
                    ++i;
                }
                break;
            }
        } while ((H *= 2) <= nblock && nNotDone != 0);
    }

    private int[] fpop(int sp) {
        return new int[]{this.stack_ll[sp], this.stack_hh[sp]};
    }

    private void fpush(int sp, int lz, int hz) {
        this.stack_ll[sp] = lz;
        this.stack_hh[sp] = hz;
    }

    private void fswap(int[] fmap, int zz1, int zz2) {
        int zztmp = fmap[zz1];
        fmap[zz1] = fmap[zz2];
        fmap[zz2] = zztmp;
    }

    private void fvswap(int[] fmap, int yyp1, int yyp2, int yyn) {
        while (yyn > 0) {
            this.fswap(fmap, yyp1, yyp2);
            ++yyp1;
            ++yyp2;
            --yyn;
        }
    }

    private int[] getEclass() {
        if (this.eclass == null) {
            this.eclass = new int[this.quadrant.length / 2];
        }
        return this.eclass;
    }

    private void mainQSort3(BZip2CompressorOutputStream.Data dataShadow, int loSt, int hiSt, int dSt, int last) {
        int[] stack_ll = this.stack_ll;
        int[] stack_hh = this.stack_hh;
        int[] stack_dd = this.stack_dd;
        int[] fmap = dataShadow.fmap;
        byte[] block = dataShadow.block;
        stack_ll[0] = loSt;
        stack_hh[0] = hiSt;
        stack_dd[0] = dSt;
        int sp = 1;
        while (--sp >= 0) {
            int n2;
            int lo = stack_ll[sp];
            int hi = stack_hh[sp];
            int d2 = stack_dd[sp];
            if (hi - lo < 20 || d2 > 10) {
                if (!this.mainSimpleSort(dataShadow, lo, hi, d2, last)) continue;
                return;
            }
            int d1 = d2 + 1;
            int med = BlockSort.med3(block[fmap[lo] + d1] & 0xFF, block[fmap[hi] + d1] & 0xFF, block[fmap[lo + hi >>> 1] + d1] & 0xFF);
            int unLo = lo;
            int unHi = hi;
            int ltLo = lo;
            int gtHi = hi;
            while (true) {
                int temp;
                if (unLo <= unHi) {
                    n2 = (block[fmap[unLo] + d1] & 0xFF) - med;
                    if (n2 == 0) {
                        temp = fmap[unLo];
                        fmap[unLo++] = fmap[ltLo];
                        fmap[ltLo++] = temp;
                        continue;
                    }
                    if (n2 < 0) {
                        ++unLo;
                        continue;
                    }
                }
                while (unLo <= unHi) {
                    n2 = (block[fmap[unHi] + d1] & 0xFF) - med;
                    if (n2 == 0) {
                        temp = fmap[unHi];
                        fmap[unHi--] = fmap[gtHi];
                        fmap[gtHi--] = temp;
                        continue;
                    }
                    if (n2 <= 0) break;
                    --unHi;
                }
                if (unLo > unHi) break;
                int temp2 = fmap[unLo];
                fmap[unLo++] = fmap[unHi];
                fmap[unHi--] = temp2;
            }
            if (gtHi < ltLo) {
                stack_ll[sp] = lo;
                stack_hh[sp] = hi;
                stack_dd[sp] = d1;
            } else {
                n2 = Math.min(ltLo - lo, unLo - ltLo);
                BlockSort.vswap(fmap, lo, unLo - n2, n2);
                int m2 = Math.min(hi - gtHi, gtHi - unHi);
                BlockSort.vswap(fmap, unLo, hi - m2 + 1, m2);
                n2 = lo + unLo - ltLo - 1;
                m2 = hi - (gtHi - unHi) + 1;
                stack_ll[sp] = lo;
                stack_hh[sp] = n2;
                stack_dd[sp] = d2;
                stack_ll[++sp] = n2 + 1;
                stack_hh[sp] = m2 - 1;
                stack_dd[sp] = d1;
                stack_ll[++sp] = m2;
                stack_hh[sp] = hi;
                stack_dd[sp] = d2;
            }
            ++sp;
        }
    }

    private boolean mainSimpleSort(BZip2CompressorOutputStream.Data dataShadow, int lo, int hi, int d2, int lastShadow) {
        int bigN = hi - lo + 1;
        if (bigN < 2) {
            return this.firstAttempt && this.workDone > this.workLimit;
        }
        int hp = 0;
        while (INCS[hp] < bigN) {
            ++hp;
        }
        int[] fmap = dataShadow.fmap;
        char[] quadrant = this.quadrant;
        byte[] block = dataShadow.block;
        int lastPlus1 = lastShadow + 1;
        boolean firstAttemptShadow = this.firstAttempt;
        int workLimitShadow = this.workLimit;
        int workDoneShadow = this.workDone;
        block1: while (--hp >= 0) {
            int h2 = INCS[hp];
            int mj = lo + h2 - 1;
            int i2 = lo + h2;
            while (i2 <= hi) {
                int k2 = 3;
                while (i2 <= hi && --k2 >= 0) {
                    int v2 = fmap[i2];
                    int vd = v2 + d2;
                    int j2 = i2;
                    boolean onceRunned = false;
                    int a2 = 0;
                    block4: while (true) {
                        int i22;
                        int i1;
                        if (onceRunned) {
                            fmap[j2] = a2;
                            if ((j2 -= h2) <= mj) {
                                break;
                            }
                        } else {
                            onceRunned = true;
                        }
                        if (block[(i1 = (a2 = fmap[j2 - h2]) + d2) + 1] == block[(i22 = vd) + 1]) {
                            if (block[i1 + 2] == block[i22 + 2]) {
                                if (block[i1 + 3] == block[i22 + 3]) {
                                    if (block[i1 + 4] == block[i22 + 4]) {
                                        if (block[i1 + 5] == block[i22 + 5]) {
                                            if (block[i1 += 6] == block[i22 += 6]) {
                                                int x2 = lastShadow;
                                                while (x2 > 0) {
                                                    x2 -= 4;
                                                    if (block[i1 + 1] == block[i22 + 1]) {
                                                        if (quadrant[i1] == quadrant[i22]) {
                                                            if (block[i1 + 2] == block[i22 + 2]) {
                                                                if (quadrant[i1 + 1] == quadrant[i22 + 1]) {
                                                                    if (block[i1 + 3] == block[i22 + 3]) {
                                                                        if (quadrant[i1 + 2] == quadrant[i22 + 2]) {
                                                                            if (block[i1 + 4] == block[i22 + 4]) {
                                                                                if (quadrant[i1 + 3] == quadrant[i22 + 3]) {
                                                                                    if ((i1 += 4) >= lastPlus1) {
                                                                                        i1 -= lastPlus1;
                                                                                    }
                                                                                    if ((i22 += 4) >= lastPlus1) {
                                                                                        i22 -= lastPlus1;
                                                                                    }
                                                                                    ++workDoneShadow;
                                                                                    continue;
                                                                                }
                                                                                if (quadrant[i1 + 3] <= quadrant[i22 + 3]) break block4;
                                                                                continue block4;
                                                                            }
                                                                            if ((block[i1 + 4] & 0xFF) <= (block[i22 + 4] & 0xFF)) break block4;
                                                                            continue block4;
                                                                        }
                                                                        if (quadrant[i1 + 2] <= quadrant[i22 + 2]) break block4;
                                                                        continue block4;
                                                                    }
                                                                    if ((block[i1 + 3] & 0xFF) <= (block[i22 + 3] & 0xFF)) break block4;
                                                                    continue block4;
                                                                }
                                                                if (quadrant[i1 + 1] <= quadrant[i22 + 1]) break block4;
                                                                continue block4;
                                                            }
                                                            if ((block[i1 + 2] & 0xFF) <= (block[i22 + 2] & 0xFF)) break block4;
                                                            continue block4;
                                                        }
                                                        if (quadrant[i1] <= quadrant[i22]) break block4;
                                                        continue block4;
                                                    }
                                                    if ((block[i1 + 1] & 0xFF) <= (block[i22 + 1] & 0xFF)) break block4;
                                                    continue block4;
                                                }
                                                break;
                                            }
                                            if ((block[i1] & 0xFF) <= (block[i22] & 0xFF)) break;
                                            continue;
                                        }
                                        if ((block[i1 + 5] & 0xFF) <= (block[i22 + 5] & 0xFF)) break;
                                        continue;
                                    }
                                    if ((block[i1 + 4] & 0xFF) <= (block[i22 + 4] & 0xFF)) break;
                                    continue;
                                }
                                if ((block[i1 + 3] & 0xFF) <= (block[i22 + 3] & 0xFF)) break;
                                continue;
                            }
                            if ((block[i1 + 2] & 0xFF) <= (block[i22 + 2] & 0xFF)) break;
                            continue;
                        }
                        if ((block[i1 + 1] & 0xFF) <= (block[i22 + 1] & 0xFF)) break;
                    }
                    fmap[j2] = v2;
                    ++i2;
                }
                if (!firstAttemptShadow || i2 > hi || workDoneShadow <= workLimitShadow) continue;
                break block1;
            }
        }
        this.workDone = workDoneShadow;
        return firstAttemptShadow && workDoneShadow > workLimitShadow;
    }

    final void mainSort(BZip2CompressorOutputStream.Data dataShadow, int lastShadow) {
        int j2;
        int c2;
        int i2;
        int i3;
        int[] runningOrder = this.mainSort_runningOrder;
        int[] copy = this.mainSort_copy;
        boolean[] bigDone = this.mainSort_bigDone;
        int[] ftab = this.ftab;
        byte[] block = dataShadow.block;
        int[] fmap = dataShadow.fmap;
        char[] quadrant = this.quadrant;
        int workLimitShadow = this.workLimit;
        boolean firstAttemptShadow = this.firstAttempt;
        Arrays.fill(ftab, 0);
        for (i3 = 0; i3 < 20; ++i3) {
            block[lastShadow + i3 + 2] = block[i3 % (lastShadow + 1) + 1];
        }
        i3 = lastShadow + 20 + 1;
        while (--i3 >= 0) {
            quadrant[i3] = '\u0000';
        }
        block[0] = block[lastShadow + 1];
        int c1 = block[0] & 0xFF;
        for (i2 = 0; i2 <= lastShadow; ++i2) {
            c2 = block[i2 + 1] & 0xFF;
            int n2 = (c1 << 8) + c2;
            ftab[n2] = ftab[n2] + 1;
            c1 = c2;
        }
        for (i2 = 1; i2 <= 65536; ++i2) {
            int n3 = i2;
            ftab[n3] = ftab[n3] + ftab[i2 - 1];
        }
        c1 = block[1] & 0xFF;
        i2 = 0;
        while (i2 < lastShadow) {
            c2 = block[i2 + 2] & 0xFF;
            int n4 = (c1 << 8) + c2;
            int n5 = ftab[n4] - 1;
            ftab[n4] = n5;
            fmap[n5] = i2++;
            c1 = c2;
        }
        int n6 = ((block[lastShadow + 1] & 0xFF) << 8) + (block[1] & 0xFF);
        int n7 = ftab[n6] - 1;
        ftab[n6] = n7;
        fmap[n7] = lastShadow;
        i2 = 256;
        while (--i2 >= 0) {
            bigDone[i2] = false;
            runningOrder[i2] = i2;
        }
        int h2 = 364;
        while (h2 != 1) {
            for (int i4 = h2 /= 3; i4 <= 255; ++i4) {
                int vv = runningOrder[i4];
                int a2 = ftab[vv + 1 << 8] - ftab[vv << 8];
                int b2 = h2 - 1;
                j2 = i4;
                int ro = runningOrder[j2 - h2];
                while (ftab[ro + 1 << 8] - ftab[ro << 8] > a2) {
                    runningOrder[j2] = ro;
                    if ((j2 -= h2) <= b2) break;
                    ro = runningOrder[j2 - h2];
                }
                runningOrder[j2] = vv;
            }
        }
        for (i2 = 0; i2 <= 255; ++i2) {
            int j3;
            int ss = runningOrder[i2];
            for (j3 = 0; j3 <= 255; ++j3) {
                int sb = (ss << 8) + j3;
                int ftab_sb = ftab[sb];
                if ((ftab_sb & 0x200000) == 0x200000) continue;
                int hi = (ftab[sb + 1] & 0xFFDFFFFF) - 1;
                int lo = ftab_sb & 0xFFDFFFFF;
                if (hi > lo) {
                    this.mainQSort3(dataShadow, lo, hi, 2, lastShadow);
                    if (firstAttemptShadow && this.workDone > workLimitShadow) {
                        return;
                    }
                }
                ftab[sb] = ftab_sb | 0x200000;
            }
            for (j3 = 0; j3 <= 255; ++j3) {
                copy[j3] = ftab[(j3 << 8) + ss] & 0xFFDFFFFF;
            }
            int hj = ftab[ss + 1 << 8] & 0xFFDFFFFF;
            for (j3 = ftab[ss << 8] & 0xFFDFFFFF; j3 < hj; ++j3) {
                int fmap_j = fmap[j3];
                c1 = block[fmap_j] & 0xFF;
                if (bigDone[c1]) continue;
                fmap[copy[c1]] = fmap_j == 0 ? lastShadow : fmap_j - 1;
                int n8 = c1;
                copy[n8] = copy[n8] + 1;
            }
            j3 = 256;
            while (--j3 >= 0) {
                int n9 = (j3 << 8) + ss;
                ftab[n9] = ftab[n9] | 0x200000;
            }
            bigDone[ss] = true;
            if (i2 >= 255) continue;
            int bbStart = ftab[ss << 8] & 0xFFDFFFFF;
            int bbSize = (ftab[ss + 1 << 8] & 0xFFDFFFFF) - bbStart;
            int shifts = 0;
            while (bbSize >> shifts > 65534) {
                ++shifts;
            }
            for (j2 = 0; j2 < bbSize; ++j2) {
                char qVal;
                int a2update = fmap[bbStart + j2];
                quadrant[a2update] = qVal = (char)(j2 >> shifts);
                if (a2update >= 20) continue;
                quadrant[a2update + lastShadow + 1] = qVal;
            }
        }
    }
}

