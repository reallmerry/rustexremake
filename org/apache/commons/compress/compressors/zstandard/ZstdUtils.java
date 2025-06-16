/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.zstandard;

import org.apache.commons.compress.utils.OsgiUtils;

public class ZstdUtils {
    private static final byte[] ZSTANDARD_FRAME_MAGIC = new byte[]{40, -75, 47, -3};
    private static final byte[] SKIPPABLE_FRAME_MAGIC = new byte[]{42, 77, 24};
    private static volatile CachedAvailability cachedZstdAvailability = CachedAvailability.DONT_CACHE;

    static CachedAvailability getCachedZstdAvailability() {
        return cachedZstdAvailability;
    }

    private static boolean internalIsZstdCompressionAvailable() {
        try {
            Class.forName("com.github.luben.zstd.ZstdInputStream");
            return true;
        } catch (Exception | NoClassDefFoundError error) {
            return false;
        }
    }

    public static boolean isZstdCompressionAvailable() {
        CachedAvailability cachedResult = cachedZstdAvailability;
        if (cachedResult != CachedAvailability.DONT_CACHE) {
            return cachedResult == CachedAvailability.CACHED_AVAILABLE;
        }
        return ZstdUtils.internalIsZstdCompressionAvailable();
    }

    public static boolean matches(byte[] signature, int length) {
        int i2;
        if (length < ZSTANDARD_FRAME_MAGIC.length) {
            return false;
        }
        boolean isZstandard = true;
        for (i2 = 0; i2 < ZSTANDARD_FRAME_MAGIC.length; ++i2) {
            if (signature[i2] == ZSTANDARD_FRAME_MAGIC[i2]) continue;
            isZstandard = false;
            break;
        }
        if (isZstandard) {
            return true;
        }
        if (80 == (signature[0] & 0xF0)) {
            for (i2 = 0; i2 < SKIPPABLE_FRAME_MAGIC.length; ++i2) {
                if (signature[i2 + 1] == SKIPPABLE_FRAME_MAGIC[i2]) continue;
                return false;
            }
            return true;
        }
        return false;
    }

    public static void setCacheZstdAvailablity(boolean doCache) {
        if (!doCache) {
            cachedZstdAvailability = CachedAvailability.DONT_CACHE;
        } else if (cachedZstdAvailability == CachedAvailability.DONT_CACHE) {
            boolean hasZstd = ZstdUtils.internalIsZstdCompressionAvailable();
            cachedZstdAvailability = hasZstd ? CachedAvailability.CACHED_AVAILABLE : CachedAvailability.CACHED_UNAVAILABLE;
        }
    }

    private ZstdUtils() {
    }

    static {
        ZstdUtils.setCacheZstdAvailablity(!OsgiUtils.isRunningInOsgiEnvironment());
    }

    static enum CachedAvailability {
        DONT_CACHE,
        CACHED_AVAILABLE,
        CACHED_UNAVAILABLE;

    }
}

