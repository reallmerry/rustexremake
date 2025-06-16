/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.CRC32;
import org.apache.commons.compress.archivers.zip.AbstractUnicodeExtraField;
import org.apache.commons.compress.archivers.zip.UnicodeCommentExtraField;
import org.apache.commons.compress.archivers.zip.UnicodePathExtraField;
import org.apache.commons.compress.archivers.zip.UnsupportedZipFeatureException;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;
import org.apache.commons.compress.archivers.zip.ZipExtraField;
import org.apache.commons.compress.archivers.zip.ZipLong;
import org.apache.commons.compress.archivers.zip.ZipMethod;

public abstract class ZipUtil {
    private static final long DOSTIME_BEFORE_1980 = 0x210000L;
    private static final long UPPER_DOSTIME_BOUND = 4036608000000L;

    public static long adjustToLong(int i2) {
        if (i2 < 0) {
            return 0x100000000L + (long)i2;
        }
        return i2;
    }

    static long bigToLong(BigInteger big) {
        if (big.bitLength() <= 63) {
            return big.longValue();
        }
        throw new NumberFormatException("The BigInteger cannot fit inside a 64 bit java long: [" + big + "]");
    }

    static boolean canHandleEntryData(ZipArchiveEntry entry) {
        return ZipUtil.supportsEncryptionOf(entry) && ZipUtil.supportsMethodOf(entry);
    }

    static void checkRequestedFeatures(ZipArchiveEntry ze) throws UnsupportedZipFeatureException {
        if (!ZipUtil.supportsEncryptionOf(ze)) {
            throw new UnsupportedZipFeatureException(UnsupportedZipFeatureException.Feature.ENCRYPTION, ze);
        }
        if (!ZipUtil.supportsMethodOf(ze)) {
            ZipMethod m2 = ZipMethod.getMethodByCode(ze.getMethod());
            if (m2 == null) {
                throw new UnsupportedZipFeatureException(UnsupportedZipFeatureException.Feature.METHOD, ze);
            }
            throw new UnsupportedZipFeatureException(m2, ze);
        }
    }

    static byte[] copy(byte[] from) {
        if (from != null) {
            return Arrays.copyOf(from, from.length);
        }
        return null;
    }

    static void copy(byte[] from, byte[] to, int offset) {
        if (from != null) {
            System.arraycopy(from, 0, to, offset, from.length);
        }
    }

    private static Date dosToJavaDate(long dosTime) {
        Calendar cal = Calendar.getInstance();
        cal.set(1, (int)(dosTime >> 25 & 0x7FL) + 1980);
        cal.set(2, (int)(dosTime >> 21 & 0xFL) - 1);
        cal.set(5, (int)(dosTime >> 16) & 0x1F);
        cal.set(11, (int)(dosTime >> 11) & 0x1F);
        cal.set(12, (int)(dosTime >> 5) & 0x3F);
        cal.set(13, (int)(dosTime << 1) & 0x3E);
        cal.set(14, 0);
        return cal.getTime();
    }

    public static long dosToJavaTime(long dosTime) {
        return ZipUtil.dosToJavaDate(dosTime).getTime();
    }

    public static Date fromDosTime(ZipLong zipDosTime) {
        long dosTime = zipDosTime.getValue();
        return ZipUtil.dosToJavaDate(dosTime);
    }

    private static String getUnicodeStringIfOriginalMatches(AbstractUnicodeExtraField f2, byte[] orig) {
        if (f2 != null) {
            CRC32 crc32 = new CRC32();
            crc32.update(orig);
            long origCRC32 = crc32.getValue();
            if (origCRC32 == f2.getNameCRC32()) {
                try {
                    return ZipEncodingHelper.UTF8_ZIP_ENCODING.decode(f2.getUnicodeName());
                } catch (IOException iOException) {
                    // empty catch block
                }
            }
        }
        return null;
    }

    public static boolean isDosTime(long time) {
        return time <= 4036608000000L && ZipUtil.javaToDosTime(time) != 0x210000L;
    }

    private static LocalDateTime javaEpochToLocalDateTime(long time) {
        Instant instant = Instant.ofEpochMilli(time);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    private static long javaToDosTime(long t2) {
        LocalDateTime ldt = ZipUtil.javaEpochToLocalDateTime(t2);
        if (ldt.getYear() < 1980) {
            return 0x210000L;
        }
        return (long)(ldt.getYear() - 1980 << 25 | ldt.getMonthValue() << 21 | ldt.getDayOfMonth() << 16 | ldt.getHour() << 11 | ldt.getMinute() << 5 | ldt.getSecond() >> 1) & 0xFFFFFFFFL;
    }

    static BigInteger longToBig(long l2) {
        if (l2 < Integer.MIN_VALUE) {
            throw new IllegalArgumentException("Negative longs < -2^31 not permitted: [" + l2 + "]");
        }
        if (l2 < 0L && l2 >= Integer.MIN_VALUE) {
            l2 = ZipUtil.adjustToLong((int)l2);
        }
        return BigInteger.valueOf(l2);
    }

    public static byte[] reverse(byte[] array) {
        int z2 = array.length - 1;
        for (int i2 = 0; i2 < array.length / 2; ++i2) {
            byte x2 = array[i2];
            array[i2] = array[z2 - i2];
            array[z2 - i2] = x2;
        }
        return array;
    }

    static void setNameAndCommentFromExtraFields(ZipArchiveEntry ze, byte[] originalNameBytes, byte[] commentBytes) {
        ZipExtraField cmtCandidate;
        UnicodeCommentExtraField cmt;
        String newComment;
        ZipExtraField nameCandidate = ze.getExtraField(UnicodePathExtraField.UPATH_ID);
        UnicodePathExtraField name = nameCandidate instanceof UnicodePathExtraField ? (UnicodePathExtraField)nameCandidate : null;
        String newName = ZipUtil.getUnicodeStringIfOriginalMatches(name, originalNameBytes);
        if (newName != null) {
            ze.setName(newName);
            ze.setNameSource(ZipArchiveEntry.NameSource.UNICODE_EXTRA_FIELD);
        }
        if (commentBytes != null && commentBytes.length > 0 && (newComment = ZipUtil.getUnicodeStringIfOriginalMatches(cmt = (cmtCandidate = ze.getExtraField(UnicodeCommentExtraField.UCOM_ID)) instanceof UnicodeCommentExtraField ? (UnicodeCommentExtraField)cmtCandidate : null, commentBytes)) != null) {
            ze.setComment(newComment);
            ze.setCommentSource(ZipArchiveEntry.CommentSource.UNICODE_EXTRA_FIELD);
        }
    }

    public static int signedByteToUnsignedInt(byte b2) {
        if (b2 >= 0) {
            return b2;
        }
        return 256 + b2;
    }

    private static boolean supportsEncryptionOf(ZipArchiveEntry entry) {
        return !entry.getGeneralPurposeBit().usesEncryption();
    }

    private static boolean supportsMethodOf(ZipArchiveEntry entry) {
        return entry.getMethod() == 0 || entry.getMethod() == ZipMethod.UNSHRINKING.getCode() || entry.getMethod() == ZipMethod.IMPLODING.getCode() || entry.getMethod() == 8 || entry.getMethod() == ZipMethod.ENHANCED_DEFLATED.getCode() || entry.getMethod() == ZipMethod.BZIP2.getCode();
    }

    public static ZipLong toDosTime(Date time) {
        return new ZipLong(ZipUtil.toDosTime(time.getTime()));
    }

    public static byte[] toDosTime(long t2) {
        byte[] result = new byte[4];
        ZipUtil.toDosTime(t2, result, 0);
        return result;
    }

    public static void toDosTime(long t2, byte[] buf, int offset) {
        ZipLong.putLong(ZipUtil.javaToDosTime(t2), buf, offset);
    }

    public static byte unsignedIntToSignedByte(int i2) {
        if (i2 > 255 || i2 < 0) {
            throw new IllegalArgumentException("Can only convert non-negative integers between [0,255] to byte: [" + i2 + "]");
        }
        if (i2 < 128) {
            return (byte)i2;
        }
        return (byte)(i2 - 256);
    }
}

