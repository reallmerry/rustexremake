/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.utils.FileNameUtils;

class ZipSplitOutputStream
extends OutputStream {
    private static final long ZIP_SEGMENT_MIN_SIZE = 65536L;
    private static final long ZIP_SEGMENT_MAX_SIZE = 0xFFFFFFFFL;
    private OutputStream outputStream;
    private Path zipFile;
    private final long splitSize;
    private int currentSplitSegmentIndex;
    private long currentSplitSegmentBytesWritten;
    private boolean finished;
    private final byte[] singleByte = new byte[1];

    public ZipSplitOutputStream(File zipFile, long splitSize) throws IllegalArgumentException, IOException {
        this(zipFile.toPath(), splitSize);
    }

    public ZipSplitOutputStream(Path zipFile, long splitSize) throws IllegalArgumentException, IOException {
        if (splitSize < 65536L || splitSize > 0xFFFFFFFFL) {
            throw new IllegalArgumentException("Zip split segment size should between 64K and 4,294,967,295");
        }
        this.zipFile = zipFile;
        this.splitSize = splitSize;
        this.outputStream = Files.newOutputStream(zipFile, new OpenOption[0]);
        this.writeZipSplitSignature();
    }

    @Override
    public void close() throws IOException {
        if (!this.finished) {
            this.finish();
        }
    }

    private Path createNewSplitSegmentFile(Integer zipSplitSegmentSuffixIndex) throws IOException {
        int newZipSplitSegmentSuffixIndex = zipSplitSegmentSuffixIndex == null ? this.currentSplitSegmentIndex + 2 : zipSplitSegmentSuffixIndex;
        String baseName = FileNameUtils.getBaseName(this.zipFile);
        String extension = ".z";
        extension = newZipSplitSegmentSuffixIndex <= 9 ? extension + "0" + newZipSplitSegmentSuffixIndex : extension + newZipSplitSegmentSuffixIndex;
        Path parent = this.zipFile.getParent();
        String dir = Objects.nonNull(parent) ? parent.toAbsolutePath().toString() : ".";
        Path newFile = this.zipFile.getFileSystem().getPath(dir, baseName + extension);
        if (Files.exists(newFile, new LinkOption[0])) {
            throw new IOException("split ZIP segment " + baseName + extension + " already exists");
        }
        return newFile;
    }

    private void finish() throws IOException {
        if (this.finished) {
            throw new IOException("This archive has already been finished");
        }
        String zipFileBaseName = FileNameUtils.getBaseName(this.zipFile);
        this.outputStream.close();
        Files.move(this.zipFile, this.zipFile.resolveSibling(zipFileBaseName + ".zip"), StandardCopyOption.ATOMIC_MOVE);
        this.finished = true;
    }

    public long getCurrentSplitSegmentBytesWritten() {
        return this.currentSplitSegmentBytesWritten;
    }

    public int getCurrentSplitSegmentIndex() {
        return this.currentSplitSegmentIndex;
    }

    private void openNewSplitSegment() throws IOException {
        Path newFile;
        if (this.currentSplitSegmentIndex == 0) {
            this.outputStream.close();
            newFile = this.createNewSplitSegmentFile(1);
            Files.move(this.zipFile, newFile, StandardCopyOption.ATOMIC_MOVE);
        }
        newFile = this.createNewSplitSegmentFile(null);
        this.outputStream.close();
        this.outputStream = Files.newOutputStream(newFile, new OpenOption[0]);
        this.currentSplitSegmentBytesWritten = 0L;
        this.zipFile = newFile;
        ++this.currentSplitSegmentIndex;
    }

    public void prepareToWriteUnsplittableContent(long unsplittableContentSize) throws IllegalArgumentException, IOException {
        if (unsplittableContentSize > this.splitSize) {
            throw new IllegalArgumentException("The unsplittable content size is bigger than the split segment size");
        }
        long bytesRemainingInThisSegment = this.splitSize - this.currentSplitSegmentBytesWritten;
        if (bytesRemainingInThisSegment < unsplittableContentSize) {
            this.openNewSplitSegment();
        }
    }

    @Override
    public void write(byte[] b2) throws IOException {
        this.write(b2, 0, b2.length);
    }

    @Override
    public void write(byte[] b2, int off, int len) throws IOException {
        if (len <= 0) {
            return;
        }
        if (this.currentSplitSegmentBytesWritten >= this.splitSize) {
            this.openNewSplitSegment();
            this.write(b2, off, len);
        } else if (this.currentSplitSegmentBytesWritten + (long)len > this.splitSize) {
            int bytesToWriteForThisSegment = (int)this.splitSize - (int)this.currentSplitSegmentBytesWritten;
            this.write(b2, off, bytesToWriteForThisSegment);
            this.openNewSplitSegment();
            this.write(b2, off + bytesToWriteForThisSegment, len - bytesToWriteForThisSegment);
        } else {
            this.outputStream.write(b2, off, len);
            this.currentSplitSegmentBytesWritten += (long)len;
        }
    }

    @Override
    public void write(int i2) throws IOException {
        this.singleByte[0] = (byte)(i2 & 0xFF);
        this.write(this.singleByte);
    }

    private void writeZipSplitSignature() throws IOException {
        this.outputStream.write(ZipArchiveOutputStream.DD_SIG);
        this.currentSplitSegmentBytesWritten += (long)ZipArchiveOutputStream.DD_SIG.length;
    }
}

