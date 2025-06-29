/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.compressors.gzip;

public class GzipParameters {
    private int compressionLevel = -1;
    private long modificationTime;
    private String filename;
    private String comment;
    private int operatingSystem = 255;
    private int bufferSize = 512;
    private int deflateStrategy = 0;

    public int getBufferSize() {
        return this.bufferSize;
    }

    public String getComment() {
        return this.comment;
    }

    public int getCompressionLevel() {
        return this.compressionLevel;
    }

    public int getDeflateStrategy() {
        return this.deflateStrategy;
    }

    public String getFilename() {
        return this.filename;
    }

    public long getModificationTime() {
        return this.modificationTime;
    }

    public int getOperatingSystem() {
        return this.operatingSystem;
    }

    public void setBufferSize(int bufferSize) {
        if (bufferSize <= 0) {
            throw new IllegalArgumentException("invalid buffer size: " + bufferSize);
        }
        this.bufferSize = bufferSize;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setCompressionLevel(int compressionLevel) {
        if (compressionLevel < -1 || compressionLevel > 9) {
            throw new IllegalArgumentException("Invalid gzip compression level: " + compressionLevel);
        }
        this.compressionLevel = compressionLevel;
    }

    public void setDeflateStrategy(int deflateStrategy) {
        this.deflateStrategy = deflateStrategy;
    }

    public void setFilename(String fileName) {
        this.filename = fileName;
    }

    public void setModificationTime(long modificationTime) {
        this.modificationTime = modificationTime;
    }

    public void setOperatingSystem(int operatingSystem) {
        this.operatingSystem = operatingSystem;
    }
}

