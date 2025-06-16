/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.xz;

import java.util.HashMap;
import org.apache.commons.compress.compressors.FileNameUtil;
import org.apache.commons.compress.compressors.xz.XZCompressorInputStream;
import org.apache.commons.compress.utils.OsgiUtils;

public class XZUtils {
    private static final FileNameUtil fileNameUtil;
    private static final byte[] HEADER_MAGIC;
    private static volatile CachedAvailability cachedXZAvailability;

    static CachedAvailability getCachedXZAvailability() {
        return cachedXZAvailability;
    }

    public static String getCompressedFilename(String fileName) {
        return fileNameUtil.getCompressedFilename(fileName);
    }

    public static String getUncompressedFilename(String fileName) {
        return fileNameUtil.getUncompressedFilename(fileName);
    }

    private static boolean internalIsXZCompressionAvailable() {
        try {
            XZCompressorInputStream.matches(null, 0);
            return true;
        } catch (NoClassDefFoundError error) {
            return false;
        }
    }

    public static boolean isCompressedFilename(String fileName) {
        return fileNameUtil.isCompressedFilename(fileName);
    }

    public static boolean isXZCompressionAvailable() {
        CachedAvailability cachedResult = cachedXZAvailability;
        if (cachedResult != CachedAvailability.DONT_CACHE) {
            return cachedResult == CachedAvailability.CACHED_AVAILABLE;
        }
        return XZUtils.internalIsXZCompressionAvailable();
    }

    public static boolean matches(byte[] signature, int length) {
        if (length < HEADER_MAGIC.length) {
            return false;
        }
        for (int i2 = 0; i2 < HEADER_MAGIC.length; ++i2) {
            if (signature[i2] == HEADER_MAGIC[i2]) continue;
            return false;
        }
        return true;
    }

    public static void setCacheXZAvailablity(boolean doCache) {
        if (!doCache) {
            cachedXZAvailability = CachedAvailability.DONT_CACHE;
        } else if (cachedXZAvailability == CachedAvailability.DONT_CACHE) {
            boolean hasXz = XZUtils.internalIsXZCompressionAvailable();
            cachedXZAvailability = hasXz ? CachedAvailability.CACHED_AVAILABLE : CachedAvailability.CACHED_UNAVAILABLE;
        }
    }

    private XZUtils() {
    }

    static {
        HEADER_MAGIC = new byte[]{-3, 55, 122, 88, 90, 0};
        HashMap<String, String> uncompressSuffix = new HashMap<String, String>();
        uncompressSuffix.put(".txz", ".tar");
        uncompressSuffix.put(".xz", "");
        uncompressSuffix.put("-xz", "");
        fileNameUtil = new FileNameUtil(uncompressSuffix, ".xz");
        cachedXZAvailability = CachedAvailability.DONT_CACHE;
        XZUtils.setCacheXZAvailablity(!OsgiUtils.isRunningInOsgiEnvironment());
    }

    static enum CachedAvailability {
        DONT_CACHE,
        CACHED_AVAILABLE,
        CACHED_UNAVAILABLE;

    }
}

