/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.io.Closeable;
import java.io.DataOutput;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.util.zip.CRC32;
import java.util.zip.Deflater;
import org.apache.commons.compress.parallel.ScatterGatherBackingStore;

public abstract class StreamCompressor
implements Closeable {
    private static final int DEFLATER_BLOCK_SIZE = 8192;
    private static final int BUFFER_SIZE = 4096;
    private final Deflater def;
    private final CRC32 crc = new CRC32();
    private long writtenToOutputStreamForLastEntry;
    private long sourcePayloadLength;
    private long totalWrittenToOutputStream;
    private final byte[] outputBuffer = new byte[4096];
    private final byte[] readerBuf = new byte[4096];

    static StreamCompressor create(DataOutput os, Deflater deflater) {
        return new DataOutputCompressor(deflater, os);
    }

    public static StreamCompressor create(int compressionLevel, ScatterGatherBackingStore bs) {
        Deflater deflater = new Deflater(compressionLevel, true);
        return new ScatterGatherBackingStoreCompressor(deflater, bs);
    }

    static StreamCompressor create(OutputStream os) {
        return StreamCompressor.create(os, new Deflater(-1, true));
    }

    static StreamCompressor create(OutputStream os, Deflater deflater) {
        return new OutputStreamCompressor(deflater, os);
    }

    public static StreamCompressor create(ScatterGatherBackingStore bs) {
        return StreamCompressor.create(-1, bs);
    }

    static StreamCompressor create(SeekableByteChannel os, Deflater deflater) {
        return new SeekableByteChannelCompressor(deflater, os);
    }

    StreamCompressor(Deflater deflater) {
        this.def = deflater;
    }

    @Override
    public void close() throws IOException {
        this.def.end();
    }

    void deflate() throws IOException {
        int len = this.def.deflate(this.outputBuffer, 0, this.outputBuffer.length);
        if (len > 0) {
            this.writeCounted(this.outputBuffer, 0, len);
        }
    }

    public void deflate(InputStream source, int method) throws IOException {
        int length;
        this.reset();
        while ((length = source.read(this.readerBuf, 0, this.readerBuf.length)) >= 0) {
            this.write(this.readerBuf, 0, length, method);
        }
        if (method == 8) {
            this.flushDeflater();
        }
    }

    private void deflateUntilInputIsNeeded() throws IOException {
        while (!this.def.needsInput()) {
            this.deflate();
        }
    }

    void flushDeflater() throws IOException {
        this.def.finish();
        while (!this.def.finished()) {
            this.deflate();
        }
    }

    public long getBytesRead() {
        return this.sourcePayloadLength;
    }

    public long getBytesWrittenForLastEntry() {
        return this.writtenToOutputStreamForLastEntry;
    }

    public long getCrc32() {
        return this.crc.getValue();
    }

    public long getTotalBytesWritten() {
        return this.totalWrittenToOutputStream;
    }

    void reset() {
        this.crc.reset();
        this.def.reset();
        this.sourcePayloadLength = 0L;
        this.writtenToOutputStreamForLastEntry = 0L;
    }

    long write(byte[] b2, int offset, int length, int method) throws IOException {
        long current = this.writtenToOutputStreamForLastEntry;
        this.crc.update(b2, offset, length);
        if (method == 8) {
            this.writeDeflated(b2, offset, length);
        } else {
            this.writeCounted(b2, offset, length);
        }
        this.sourcePayloadLength += (long)length;
        return this.writtenToOutputStreamForLastEntry - current;
    }

    public void writeCounted(byte[] data) throws IOException {
        this.writeCounted(data, 0, data.length);
    }

    public void writeCounted(byte[] data, int offset, int length) throws IOException {
        this.writeOut(data, offset, length);
        this.writtenToOutputStreamForLastEntry += (long)length;
        this.totalWrittenToOutputStream += (long)length;
    }

    private void writeDeflated(byte[] b2, int offset, int length) throws IOException {
        if (length > 0 && !this.def.finished()) {
            if (length <= 8192) {
                this.def.setInput(b2, offset, length);
                this.deflateUntilInputIsNeeded();
            } else {
                int fullblocks = length / 8192;
                for (int i2 = 0; i2 < fullblocks; ++i2) {
                    this.def.setInput(b2, offset + i2 * 8192, 8192);
                    this.deflateUntilInputIsNeeded();
                }
                int done = fullblocks * 8192;
                if (done < length) {
                    this.def.setInput(b2, offset + done, length - done);
                    this.deflateUntilInputIsNeeded();
                }
            }
        }
    }

    protected abstract void writeOut(byte[] var1, int var2, int var3) throws IOException;

    private static final class DataOutputCompressor
    extends StreamCompressor {
        private final DataOutput raf;

        public DataOutputCompressor(Deflater deflater, DataOutput raf) {
            super(deflater);
            this.raf = raf;
        }

        @Override
        protected void writeOut(byte[] data, int offset, int length) throws IOException {
            this.raf.write(data, offset, length);
        }
    }

    private static final class ScatterGatherBackingStoreCompressor
    extends StreamCompressor {
        private final ScatterGatherBackingStore bs;

        public ScatterGatherBackingStoreCompressor(Deflater deflater, ScatterGatherBackingStore bs) {
            super(deflater);
            this.bs = bs;
        }

        @Override
        protected void writeOut(byte[] data, int offset, int length) throws IOException {
            this.bs.writeOut(data, offset, length);
        }
    }

    private static final class OutputStreamCompressor
    extends StreamCompressor {
        private final OutputStream os;

        public OutputStreamCompressor(Deflater deflater, OutputStream os) {
            super(deflater);
            this.os = os;
        }

        @Override
        protected void writeOut(byte[] data, int offset, int length) throws IOException {
            this.os.write(data, offset, length);
        }
    }

    private static final class SeekableByteChannelCompressor
    extends StreamCompressor {
        private final SeekableByteChannel channel;

        public SeekableByteChannelCompressor(Deflater deflater, SeekableByteChannel channel) {
            super(deflater);
            this.channel = channel;
        }

        @Override
        protected void writeOut(byte[] data, int offset, int length) throws IOException {
            this.channel.write(ByteBuffer.wrap(data, offset, length));
        }
    }
}

