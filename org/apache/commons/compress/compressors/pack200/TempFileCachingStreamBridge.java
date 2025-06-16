/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.pack200;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import org.apache.commons.compress.compressors.pack200.AbstractStreamBridge;

class TempFileCachingStreamBridge
extends AbstractStreamBridge {
    private final Path f = Files.createTempFile("commons-compress", "packtemp", new FileAttribute[0]);

    TempFileCachingStreamBridge() throws IOException {
        this.f.toFile().deleteOnExit();
        this.out = Files.newOutputStream(this.f, new OpenOption[0]);
    }

    @Override
    InputStream getInputView() throws IOException {
        this.out.close();
        return new FilterInputStream(Files.newInputStream(this.f, new OpenOption[0])){

            @Override
            public void close() throws IOException {
                try {
                    super.close();
                } finally {
                    try {
                        Files.deleteIfExists(TempFileCachingStreamBridge.this.f);
                    } catch (IOException iOException) {}
                }
            }
        };
    }
}

