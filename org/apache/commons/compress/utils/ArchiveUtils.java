/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.utils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.apache.commons.compress.archivers.ArchiveEntry;

public class ArchiveUtils {
    private static final int MAX_SANITIZED_NAME_LENGTH = 255;

    public static boolean isArrayZero(byte[] a2, int size) {
        for (int i2 = 0; i2 < size; ++i2) {
            if (a2[i2] == 0) continue;
            return false;
        }
        return true;
    }

    public static boolean isEqual(byte[] buffer1, byte[] buffer2) {
        return ArchiveUtils.isEqual(buffer1, 0, buffer1.length, buffer2, 0, buffer2.length, false);
    }

    public static boolean isEqual(byte[] buffer1, byte[] buffer2, boolean ignoreTrailingNulls) {
        return ArchiveUtils.isEqual(buffer1, 0, buffer1.length, buffer2, 0, buffer2.length, ignoreTrailingNulls);
    }

    public static boolean isEqual(byte[] buffer1, int offset1, int length1, byte[] buffer2, int offset2, int length2) {
        return ArchiveUtils.isEqual(buffer1, offset1, length1, buffer2, offset2, length2, false);
    }

    public static boolean isEqual(byte[] buffer1, int offset1, int length1, byte[] buffer2, int offset2, int length2, boolean ignoreTrailingNulls) {
        int i2;
        int minLen = Math.min(length1, length2);
        for (i2 = 0; i2 < minLen; ++i2) {
            if (buffer1[offset1 + i2] == buffer2[offset2 + i2]) continue;
            return false;
        }
        if (length1 == length2) {
            return true;
        }
        if (ignoreTrailingNulls) {
            if (length1 > length2) {
                for (i2 = length2; i2 < length1; ++i2) {
                    if (buffer1[offset1 + i2] == 0) continue;
                    return false;
                }
            } else {
                for (i2 = length1; i2 < length2; ++i2) {
                    if (buffer2[offset2 + i2] == 0) continue;
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isEqualWithNull(byte[] buffer1, int offset1, int length1, byte[] buffer2, int offset2, int length2) {
        return ArchiveUtils.isEqual(buffer1, offset1, length1, buffer2, offset2, length2, true);
    }

    public static boolean matchAsciiBuffer(String expected, byte[] buffer) {
        return ArchiveUtils.matchAsciiBuffer(expected, buffer, 0, buffer.length);
    }

    public static boolean matchAsciiBuffer(String expected, byte[] buffer, int offset, int length) {
        byte[] buffer1 = expected.getBytes(StandardCharsets.US_ASCII);
        return ArchiveUtils.isEqual(buffer1, 0, buffer1.length, buffer, offset, length, false);
    }

    public static String sanitize(String s2) {
        char[] chars;
        char[] cs = s2.toCharArray();
        char[] cArray = chars = cs.length <= 255 ? cs : Arrays.copyOf(cs, 255);
        if (cs.length > 255) {
            Arrays.fill(chars, 252, 255, '.');
        }
        StringBuilder sb = new StringBuilder();
        for (char c2 : chars) {
            Character.UnicodeBlock block;
            if (!Character.isISOControl(c2) && (block = Character.UnicodeBlock.of(c2)) != null && block != Character.UnicodeBlock.SPECIALS) {
                sb.append(c2);
                continue;
            }
            sb.append('?');
        }
        return sb.toString();
    }

    public static byte[] toAsciiBytes(String inputString) {
        return inputString.getBytes(StandardCharsets.US_ASCII);
    }

    public static String toAsciiString(byte[] inputBytes) {
        return new String(inputBytes, StandardCharsets.US_ASCII);
    }

    public static String toAsciiString(byte[] inputBytes, int offset, int length) {
        return new String(inputBytes, offset, length, StandardCharsets.US_ASCII);
    }

    public static String toString(ArchiveEntry entry) {
        StringBuilder sb = new StringBuilder();
        sb.append(entry.isDirectory() ? (char)'d' : '-');
        String size = Long.toString(entry.getSize());
        sb.append(' ');
        for (int i2 = 7; i2 > size.length(); --i2) {
            sb.append(' ');
        }
        sb.append(size);
        sb.append(' ').append(entry.getName());
        return sb.toString();
    }

    private ArchiveUtils() {
    }
}

