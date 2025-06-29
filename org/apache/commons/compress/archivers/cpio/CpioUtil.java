/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.cpio;

import java.util.Arrays;

class CpioUtil {
    CpioUtil() {
    }

    static long byteArray2long(byte[] number, boolean swapHalfWord) {
        if (number.length % 2 != 0) {
            throw new UnsupportedOperationException();
        }
        int pos = 0;
        byte[] tmpNumber = Arrays.copyOf(number, number.length);
        if (!swapHalfWord) {
            byte tmp = 0;
            for (pos = 0; pos < tmpNumber.length; ++pos) {
                tmp = tmpNumber[pos];
                tmpNumber[pos++] = tmpNumber[pos];
                tmpNumber[pos] = tmp;
            }
        }
        long ret = tmpNumber[0] & 0xFF;
        for (pos = 1; pos < tmpNumber.length; ++pos) {
            ret <<= 8;
            ret |= (long)(tmpNumber[pos] & 0xFF);
        }
        return ret;
    }

    static long fileType(long mode) {
        return mode & 0xF000L;
    }

    static byte[] long2byteArray(long number, int length, boolean swapHalfWord) {
        byte[] ret = new byte[length];
        int pos = 0;
        if (length % 2 != 0 || length < 2) {
            throw new UnsupportedOperationException();
        }
        long tmp_number = number;
        for (pos = length - 1; pos >= 0; --pos) {
            ret[pos] = (byte)(tmp_number & 0xFFL);
            tmp_number >>= 8;
        }
        if (!swapHalfWord) {
            byte tmp = 0;
            for (pos = 0; pos < length; ++pos) {
                tmp = ret[pos];
                ret[pos++] = ret[pos];
                ret[pos] = tmp;
            }
        }
        return ret;
    }
}

