/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.bzip2;

import java.util.LinkedHashMap;
import org.apache.commons.compress.compressors.FileNameUtil;

public abstract class BZip2Utils {
    private static final FileNameUtil fileNameUtil;

    public static String getCompressedFilename(String fileName) {
        return fileNameUtil.getCompressedFilename(fileName);
    }

    public static String getUncompressedFilename(String fileName) {
        return fileNameUtil.getUncompressedFilename(fileName);
    }

    public static boolean isCompressedFilename(String fileName) {
        return fileNameUtil.isCompressedFilename(fileName);
    }

    private BZip2Utils() {
    }

    static {
        LinkedHashMap<String, String> uncompressSuffix = new LinkedHashMap<String, String>();
        uncompressSuffix.put(".tar.bz2", ".tar");
        uncompressSuffix.put(".tbz2", ".tar");
        uncompressSuffix.put(".tbz", ".tar");
        uncompressSuffix.put(".bz2", "");
        uncompressSuffix.put(".bz", "");
        fileNameUtil = new FileNameUtil(uncompressSuffix, ".bz2");
    }
}

