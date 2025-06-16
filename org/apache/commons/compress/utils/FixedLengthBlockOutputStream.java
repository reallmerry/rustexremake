/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.WritableByteChannel;
import java.util.concurrent.atomic.AtomicBoolean;

public class FixedLengthBlockOutputStream
extends OutputStream
implements WritableByteChannel {
    private final WritableByteChannel out;
    private final int blockSize;
    private final ByteBuffer buffer;
    private final AtomicBoolean closed = new AtomicBoolean(false);

    public FixedLengthBlockOutputStream(OutputStream os, int blockSize) {
        if (os instanceof FileOutputStream) {
            FileOutputStream fileOutputStream = (FileOutputStream)os;
            this.out = fileOutputStream.getChannel();
            this.buffer = ByteBuffer.allocateDirect(blockSize);
        } else {
            this.out = new BufferAtATimeOutputChannel(os);
            this.buffer = ByteBuffer.allocate(blockSize);
        }
        this.blockSize = blockSize;
    }

    public FixedLengthBlockOutputStream(WritableByteChannel out, int blockSize) {
        this.out = out;
        this.blockSize = blockSize;
        this.buffer = ByteBuffer.allocateDirect(blockSize);
    }

    @Override
    public void close() throws IOException {
        if (this.closed.compareAndSet(false, true)) {
            try {
                this.flushBlock();
            } finally {
                this.out.close();
            }
        }
    }

    public void flushBlock() throws IOException {
        if (this.buffer.position() != 0) {
            this.padBlock();
            this.writeBlock();
        }
    }

    @Override
    public boolean isOpen() {
        if (!this.out.isOpen()) {
            this.closed.set(true);
        }
        return !this.closed.get();
    }

    private void maybeFlush() throws IOException {
        if (!this.buffer.hasRemaining()) {
            this.writeBlock();
        }
    }

    private void padBlock() {
        this.buffer.order(ByteOrder.nativeOrder());
        int bytesToWrite = this.buffer.remaining();
        if (bytesToWrite > 8) {
            int align = this.buffer.position() & 7;
            if (align != 0) {
                int limit = 8 - align;
                for (int i2 = 0; i2 < limit; ++i2) {
                    this.buffer.put((byte)0);
                }
                bytesToWrite -= limit;
            }
            while (bytesToWrite >= 8) {
                this.buffer.putLong(0L);
                bytesToWrite -= 8;
            }
        }
        while (this.buffer.hasRemaining()) {
            this.buffer.put((byte)0);
        }
    }

    @Override
    public void write(byte[] b2, int offset, int length) throws IOException {
        if (!this.isOpen()) {
            throw new ClosedChannelException();
        }
        int off = offset;
        int len = length;
        while (len > 0) {
            int n2 = Math.min(len, this.buffer.remaining());
            this.buffer.put(b2, off, n2);
            this.maybeFlush();
            len -= n2;
            off += n2;
        }
    }

    @Override
    public int write(ByteBuffer src) throws IOException {
        if (!this.isOpen()) {
            throw new ClosedChannelException();
        }
        int srcRemaining = src.remaining();
        if (srcRemaining < this.buffer.remaining()) {
            this.buffer.put(src);
        } else {
            int srcLeft = srcRemaining;
            int savedLimit = src.limit();
            if (this.buffer.position() != 0) {
                int n2 = this.buffer.remaining();
                src.limit(src.position() + n2);
                this.buffer.put(src);
                this.writeBlock();
                srcLeft -= n2;
            }
            while (srcLeft >= this.blockSize) {
                src.limit(src.position() + this.blockSize);
                this.out.write(src);
                srcLeft -= this.blockSize;
            }
            src.limit(savedLimit);
            this.buffer.put(src);
        }
        return srcRemaining;
    }

    @Override
    public void write(int b2) throws IOException {
        if (!this.isOpen()) {
            throw new ClosedChannelException();
        }
        this.buffer.put((byte)b2);
        this.maybeFlush();
    }

    private void writeBlock() throws IOException {
        this.buffer.flip();
        int i2 = this.out.write(this.buffer);
        boolean hasRemaining = this.buffer.hasRemaining();
        if (i2 != this.blockSize || hasRemaining) {
            String msg = String.format("Failed to write %,d bytes atomically. Only wrote  %,d", this.blockSize, i2);
            throw new IOException(msg);
        }
        this.buffer.clear();
    }

    private static class BufferAtATimeOutputChannel
    implements WritableByteChannel {
        private final OutputStream out;
        private final AtomicBoolean closed = new AtomicBoolean(false);

        private BufferAtATimeOutputChannel(OutputStream out) {
            this.out = out;
        }

        @Override
        public void close() throws IOException {
            if (this.closed.compareAndSet(false, true)) {
                this.out.close();
            }
        }

        @Override
        public boolean isOpen() {
            return !this.closed.get();
        }

        @Override
        public int write(ByteBuffer buffer) throws IOException {
            if (!this.isOpen()) {
                throw new ClosedChannelException();
            }
            if (!buffer.hasArray()) {
                throw new IOException("Direct buffer somehow written to BufferAtATimeOutputChannel");
            }
            try {
                int pos = buffer.position();
                int len = buffer.limit() - pos;
                this.out.write(buffer.array(), buffer.arrayOffset() + pos, len);
                buffer.position(buffer.limit());
                return len;
            } catch (IOException e2) {
                try {
                    this.close();
                } catch (IOException iOException) {
                    // empty catch block
                }
                throw e2;
            }
        }
    }
}

