/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.examples;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.Enumeration;
import java.util.Iterator;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.examples.CloseableConsumer;
import org.apache.commons.compress.archivers.examples.CloseableConsumerAdapter;
import org.apache.commons.compress.archivers.sevenz.SevenZFile;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarFile;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.compress.utils.IOUtils;

public class Expander {
    private <T extends ArchiveEntry> void expand(ArchiveEntrySupplier<T> supplier, ArchiveEntryBiConsumer<T> writer, Path targetDirectory) throws IOException {
        boolean nullTarget = targetDirectory == null;
        Path targetDirPath = nullTarget ? null : targetDirectory.normalize();
        T nextEntry = supplier.get();
        while (nextEntry != null) {
            Path targetPath;
            Path path = targetPath = nullTarget ? null : targetDirectory.resolve(nextEntry.getName());
            if (!(nullTarget || targetPath.normalize().startsWith(targetDirPath) || Files.isSameFile(targetDirectory, targetPath))) {
                throw new IOException("Expanding " + nextEntry.getName() + " would create file outside of " + targetDirectory);
            }
            if (nextEntry.isDirectory()) {
                if (!nullTarget && !Files.isDirectory(targetPath, new LinkOption[0]) && Files.createDirectories(targetPath, new FileAttribute[0]) == null) {
                    throw new IOException("Failed to create directory " + targetPath);
                }
            } else {
                Path parent;
                Path path2 = parent = nullTarget ? null : targetPath.getParent();
                if (!nullTarget && !Files.isDirectory(parent, new LinkOption[0]) && Files.createDirectories(parent, new FileAttribute[0]) == null) {
                    throw new IOException("Failed to create directory " + parent);
                }
                if (nullTarget) {
                    writer.accept(nextEntry, null);
                } else {
                    try (OutputStream outputStream = Files.newOutputStream(targetPath, new OpenOption[0]);){
                        writer.accept(nextEntry, outputStream);
                    }
                }
            }
            nextEntry = supplier.get();
        }
    }

    public void expand(ArchiveInputStream archive, File targetDirectory) throws IOException {
        this.expand(archive, this.toPath(targetDirectory));
    }

    public void expand(ArchiveInputStream archive, Path targetDirectory) throws IOException {
        this.expand(() -> {
            ArchiveEntry next = archive.getNextEntry();
            while (next != null && !archive.canReadEntryData(next)) {
                next = archive.getNextEntry();
            }
            return next;
        }, (T entry, OutputStream out) -> IOUtils.copy(archive, out), targetDirectory);
    }

    public void expand(File archive, File targetDirectory) throws IOException, ArchiveException {
        this.expand(archive.toPath(), this.toPath(targetDirectory));
    }

    @Deprecated
    public void expand(InputStream archive, File targetDirectory) throws IOException, ArchiveException {
        this.expand(archive, targetDirectory, CloseableConsumer.NULL_CONSUMER);
    }

    public void expand(InputStream archive, File targetDirectory, CloseableConsumer closeableConsumer) throws IOException, ArchiveException {
        try (CloseableConsumerAdapter c2 = new CloseableConsumerAdapter(closeableConsumer);){
            this.expand(c2.track(ArchiveStreamFactory.DEFAULT.createArchiveInputStream(archive)), targetDirectory);
        }
    }

    public void expand(Path archive, Path targetDirectory) throws IOException, ArchiveException {
        try (BufferedInputStream inputStream = new BufferedInputStream(Files.newInputStream(archive, new OpenOption[0]));){
            String format = ArchiveStreamFactory.detect(inputStream);
            this.expand(format, archive, targetDirectory);
        }
    }

    public void expand(SevenZFile archive, File targetDirectory) throws IOException {
        this.expand(archive, this.toPath(targetDirectory));
    }

    public void expand(SevenZFile archive, Path targetDirectory) throws IOException {
        this.expand(archive::getNextEntry, (T entry, OutputStream out) -> {
            int n2;
            byte[] buffer = new byte[8192];
            while (-1 != (n2 = archive.read(buffer))) {
                if (out == null) continue;
                out.write(buffer, 0, n2);
            }
        }, targetDirectory);
    }

    public void expand(String format, File archive, File targetDirectory) throws IOException, ArchiveException {
        this.expand(format, archive.toPath(), this.toPath(targetDirectory));
    }

    @Deprecated
    public void expand(String format, InputStream archive, File targetDirectory) throws IOException, ArchiveException {
        this.expand(format, archive, targetDirectory, CloseableConsumer.NULL_CONSUMER);
    }

    public void expand(String format, InputStream archive, File targetDirectory, CloseableConsumer closeableConsumer) throws IOException, ArchiveException {
        this.expand(format, archive, this.toPath(targetDirectory), closeableConsumer);
    }

    public void expand(String format, InputStream archive, Path targetDirectory, CloseableConsumer closeableConsumer) throws IOException, ArchiveException {
        try (CloseableConsumerAdapter c2 = new CloseableConsumerAdapter(closeableConsumer);){
            this.expand(c2.track(ArchiveStreamFactory.DEFAULT.createArchiveInputStream(format, archive)), targetDirectory);
        }
    }

    public void expand(String format, Path archive, Path targetDirectory) throws IOException, ArchiveException {
        if (this.prefersSeekableByteChannel(format)) {
            try (FileChannel channel = FileChannel.open(archive, StandardOpenOption.READ);){
                this.expand(format, (SeekableByteChannel)channel, targetDirectory, CloseableConsumer.CLOSING_CONSUMER);
            }
            return;
        }
        try (BufferedInputStream inputStream = new BufferedInputStream(Files.newInputStream(archive, new OpenOption[0]));){
            this.expand(format, (InputStream)inputStream, targetDirectory, CloseableConsumer.CLOSING_CONSUMER);
        }
    }

    @Deprecated
    public void expand(String format, SeekableByteChannel archive, File targetDirectory) throws IOException, ArchiveException {
        this.expand(format, archive, targetDirectory, CloseableConsumer.NULL_CONSUMER);
    }

    public void expand(String format, SeekableByteChannel archive, File targetDirectory, CloseableConsumer closeableConsumer) throws IOException, ArchiveException {
        this.expand(format, archive, this.toPath(targetDirectory), closeableConsumer);
    }

    public void expand(String format, SeekableByteChannel archive, Path targetDirectory, CloseableConsumer closeableConsumer) throws IOException, ArchiveException {
        block9: {
            try (CloseableConsumerAdapter c2 = new CloseableConsumerAdapter(closeableConsumer);){
                if (!this.prefersSeekableByteChannel(format)) {
                    this.expand(format, c2.track(Channels.newInputStream(archive)), targetDirectory, CloseableConsumer.NULL_CONSUMER);
                    break block9;
                }
                if ("tar".equalsIgnoreCase(format)) {
                    this.expand(c2.track(new TarFile(archive)), targetDirectory);
                    break block9;
                }
                if ("zip".equalsIgnoreCase(format)) {
                    this.expand(c2.track(new ZipFile(archive)), targetDirectory);
                    break block9;
                }
                if ("7z".equalsIgnoreCase(format)) {
                    this.expand(c2.track(new SevenZFile(archive)), targetDirectory);
                    break block9;
                }
                throw new ArchiveException("Don't know how to handle format " + format);
            }
        }
    }

    public void expand(TarFile archive, File targetDirectory) throws IOException {
        this.expand(archive, this.toPath(targetDirectory));
    }

    public void expand(TarFile archive, Path targetDirectory) throws IOException {
        Iterator<TarArchiveEntry> entryIterator = archive.getEntries().iterator();
        this.expand(() -> entryIterator.hasNext() ? (TarArchiveEntry)entryIterator.next() : null, (T entry, OutputStream out) -> {
            try (InputStream in = archive.getInputStream((TarArchiveEntry)entry);){
                IOUtils.copy(in, out);
            }
        }, targetDirectory);
    }

    public void expand(ZipFile archive, File targetDirectory) throws IOException {
        this.expand(archive, this.toPath(targetDirectory));
    }

    public void expand(ZipFile archive, Path targetDirectory) throws IOException {
        Enumeration<ZipArchiveEntry> entries = archive.getEntries();
        this.expand(() -> {
            ZipArchiveEntry next;
            ZipArchiveEntry zipArchiveEntry = next = entries.hasMoreElements() ? (ZipArchiveEntry)entries.nextElement() : null;
            while (next != null && !archive.canReadEntryData(next)) {
                next = entries.hasMoreElements() ? (ZipArchiveEntry)entries.nextElement() : null;
            }
            return next;
        }, (T entry, OutputStream out) -> {
            try (InputStream in = archive.getInputStream((ZipArchiveEntry)entry);){
                IOUtils.copy(in, out);
            }
        }, targetDirectory);
    }

    private boolean prefersSeekableByteChannel(String format) {
        return "tar".equalsIgnoreCase(format) || "zip".equalsIgnoreCase(format) || "7z".equalsIgnoreCase(format);
    }

    private Path toPath(File targetDirectory) {
        return targetDirectory != null ? targetDirectory.toPath() : null;
    }

    @FunctionalInterface
    private static interface ArchiveEntrySupplier<T extends ArchiveEntry> {
        public T get() throws IOException;
    }

    @FunctionalInterface
    private static interface ArchiveEntryBiConsumer<T extends ArchiveEntry> {
        public void accept(T var1, OutputStream var2) throws IOException;
    }
}

