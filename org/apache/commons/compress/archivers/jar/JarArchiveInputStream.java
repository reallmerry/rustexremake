/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.jar;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.jar.JarArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;

public class JarArchiveInputStream
extends ZipArchiveInputStream {
    public static boolean matches(byte[] signature, int length) {
        return ZipArchiveInputStream.matches(signature, length);
    }

    public JarArchiveInputStream(InputStream inputStream) {
        super(inputStream);
    }

    public JarArchiveInputStream(InputStream inputStream, String encoding) {
        super(inputStream, encoding);
    }

    @Override
    public ArchiveEntry getNextEntry() throws IOException {
        return this.getNextJarEntry();
    }

    public JarArchiveEntry getNextJarEntry() throws IOException {
        ZipArchiveEntry entry = this.getNextZipEntry();
        return entry == null ? null : new JarArchiveEntry(entry);
    }
}

