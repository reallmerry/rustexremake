/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.jar;

import java.security.cert.Certificate;
import java.util.Arrays;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;

public class JarArchiveEntry
extends ZipArchiveEntry {
    private final Attributes manifestAttributes = null;
    private final Certificate[] certificates = null;

    public JarArchiveEntry(JarEntry entry) throws ZipException {
        super(entry);
    }

    public JarArchiveEntry(String name) {
        super(name);
    }

    public JarArchiveEntry(ZipArchiveEntry entry) throws ZipException {
        super(entry);
    }

    public JarArchiveEntry(ZipEntry entry) throws ZipException {
        super(entry);
    }

    @Deprecated
    public Certificate[] getCertificates() {
        if (this.certificates != null) {
            return Arrays.copyOf(this.certificates, this.certificates.length);
        }
        return null;
    }

    @Deprecated
    public Attributes getManifestAttributes() {
        return this.manifestAttributes;
    }
}

