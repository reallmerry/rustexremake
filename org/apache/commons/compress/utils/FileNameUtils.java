/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.utils;

import java.io.File;
import java.nio.file.Path;

public class FileNameUtils {
    private static String fileNameToBaseName(String name) {
        int extensionIndex = name.lastIndexOf(46);
        return extensionIndex < 0 ? name : name.substring(0, extensionIndex);
    }

    private static String fileNameToExtension(String name) {
        int extensionIndex = name.lastIndexOf(46);
        return extensionIndex < 0 ? "" : name.substring(extensionIndex + 1);
    }

    public static String getBaseName(Path path) {
        if (path == null) {
            return null;
        }
        Path fileName = path.getFileName();
        return fileName != null ? FileNameUtils.fileNameToBaseName(fileName.toString()) : null;
    }

    public static String getBaseName(String filename) {
        if (filename == null) {
            return null;
        }
        return FileNameUtils.fileNameToBaseName(new File(filename).getName());
    }

    public static String getExtension(Path path) {
        if (path == null) {
            return null;
        }
        Path fileName = path.getFileName();
        return fileName != null ? FileNameUtils.fileNameToExtension(fileName.toString()) : null;
    }

    public static String getExtension(String filename) {
        if (filename == null) {
            return null;
        }
        return FileNameUtils.fileNameToExtension(new File(filename).getName());
    }
}

