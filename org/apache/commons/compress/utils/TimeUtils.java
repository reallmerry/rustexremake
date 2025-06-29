/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.utils;

import java.nio.file.attribute.FileTime;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public final class TimeUtils {
    static final long HUNDRED_NANOS_PER_MILLISECOND = TimeUnit.MILLISECONDS.toNanos(1L) / 100L;
    static final long HUNDRED_NANOS_PER_SECOND = TimeUnit.SECONDS.toNanos(1L) / 100L;
    static final long WINDOWS_EPOCH_OFFSET = -116444736000000000L;

    public static boolean isUnixTime(FileTime time) {
        return time == null ? true : TimeUtils.isUnixTime(TimeUtils.toUnixTime(time));
    }

    public static boolean isUnixTime(long seconds) {
        return Integer.MIN_VALUE <= seconds && seconds <= Integer.MAX_VALUE;
    }

    public static Date ntfsTimeToDate(long ntfsTime) {
        long javaHundredNanos = Math.addExact(ntfsTime, -116444736000000000L);
        long javaMillis = Math.floorDiv(javaHundredNanos, HUNDRED_NANOS_PER_MILLISECOND);
        return new Date(javaMillis);
    }

    public static FileTime ntfsTimeToFileTime(long ntfsTime) {
        long javaHundredsNanos = Math.addExact(ntfsTime, -116444736000000000L);
        long javaSeconds = Math.floorDiv(javaHundredsNanos, HUNDRED_NANOS_PER_SECOND);
        long javaNanos = Math.floorMod(javaHundredsNanos, HUNDRED_NANOS_PER_SECOND) * 100L;
        return FileTime.from(Instant.ofEpochSecond(javaSeconds, javaNanos));
    }

    public static Date toDate(FileTime fileTime) {
        return fileTime != null ? new Date(fileTime.toMillis()) : null;
    }

    public static FileTime toFileTime(Date date) {
        return date != null ? FileTime.fromMillis(date.getTime()) : null;
    }

    public static long toNtfsTime(Date date) {
        return TimeUtils.toNtfsTime(date.getTime());
    }

    public static long toNtfsTime(FileTime fileTime) {
        Instant instant = fileTime.toInstant();
        long javaHundredNanos = instant.getEpochSecond() * HUNDRED_NANOS_PER_SECOND + (long)(instant.getNano() / 100);
        return Math.subtractExact(javaHundredNanos, -116444736000000000L);
    }

    public static long toNtfsTime(long javaTime) {
        long javaHundredNanos = javaTime * HUNDRED_NANOS_PER_MILLISECOND;
        return Math.subtractExact(javaHundredNanos, -116444736000000000L);
    }

    public static long toUnixTime(FileTime fileTime) {
        return fileTime.to(TimeUnit.SECONDS);
    }

    public static FileTime truncateToHundredNanos(FileTime fileTime) {
        Instant instant = fileTime.toInstant();
        return FileTime.from(Instant.ofEpochSecond(instant.getEpochSecond(), instant.getNano() / 100 * 100));
    }

    public static FileTime unixTimeToFileTime(long time) {
        return FileTime.from(time, TimeUnit.SECONDS);
    }

    private TimeUtils() {
    }
}

