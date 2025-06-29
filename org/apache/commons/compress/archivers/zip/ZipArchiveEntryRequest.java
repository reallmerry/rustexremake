/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.io.InputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.parallel.InputStreamSupplier;

public class ZipArchiveEntryRequest {
    private final ZipArchiveEntry zipArchiveEntry;
    private final InputStreamSupplier payloadSupplier;
    private final int method;

    public static ZipArchiveEntryRequest createZipArchiveEntryRequest(ZipArchiveEntry zipArchiveEntry, InputStreamSupplier payloadSupplier) {
        return new ZipArchiveEntryRequest(zipArchiveEntry, payloadSupplier);
    }

    private ZipArchiveEntryRequest(ZipArchiveEntry zipArchiveEntry, InputStreamSupplier payloadSupplier) {
        this.zipArchiveEntry = zipArchiveEntry;
        this.payloadSupplier = payloadSupplier;
        this.method = zipArchiveEntry.getMethod();
    }

    public int getMethod() {
        return this.method;
    }

    public InputStream getPayloadStream() {
        return this.payloadSupplier.get();
    }

    ZipArchiveEntry getZipArchiveEntry() {
        return this.zipArchiveEntry;
    }
}

