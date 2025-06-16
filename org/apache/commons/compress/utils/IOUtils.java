/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.utils;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;

public final class IOUtils {
    private static final int COPY_BUF_SIZE = 8024;
    private static final int SKIP_BUF_SIZE = 4096;
    public static final LinkOption[] EMPTY_LINK_OPTIONS = new LinkOption[0];
    private static final byte[] SKIP_BUF = new byte[4096];

    public static void closeQuietly(Closeable c2) {
        if (c2 != null) {
            try {
                c2.close();
            } catch (IOException iOException) {
                // empty catch block
            }
        }
    }

    public static void copy(File sourceFile, OutputStream outputStream) throws IOException {
        Files.copy(sourceFile.toPath(), outputStream);
    }

    public static long copy(InputStream input, OutputStream output) throws IOException {
        return IOUtils.copy(input, output, 8024);
    }

    public static long copy(InputStream input, OutputStream output, int buffersize) throws IOException {
        if (buffersize < 1) {
            throw new IllegalArgumentException("buffersize must be bigger than 0");
        }
        byte[] buffer = new byte[buffersize];
        int n2 = 0;
        long count = 0L;
        while (-1 != (n2 = input.read(buffer))) {
            if (output != null) {
                output.write(buffer, 0, n2);
            }
            count += (long)n2;
        }
        return count;
    }

    public static long copyRange(InputStream input, long len, OutputStream output) throws IOException {
        return IOUtils.copyRange(input, len, output, 8024);
    }

    public static long copyRange(InputStream input, long len, OutputStream output, int buffersize) throws IOException {
        long count;
        if (buffersize < 1) {
            throw new IllegalArgumentException("buffersize must be bigger than 0");
        }
        byte[] buffer = new byte[(int)Math.min((long)buffersize, len)];
        int n2 = 0;
        for (count = 0L; count < len && -1 != (n2 = input.read(buffer, 0, (int)Math.min(len - count, (long)buffer.length))); count += (long)n2) {
            if (output == null) continue;
            output.write(buffer, 0, n2);
        }
        return count;
    }

    public static int read(File file, byte[] array) throws IOException {
        try (InputStream inputStream = Files.newInputStream(file.toPath(), new OpenOption[0]);){
            int n2 = IOUtils.readFully(inputStream, array, 0, array.length);
            return n2;
        }
    }

    public static int readFully(InputStream input, byte[] array) throws IOException {
        return IOUtils.readFully(input, array, 0, array.length);
    }

    public static int readFully(InputStream input, byte[] array, int offset, int len) throws IOException {
        int count;
        if (len < 0 || offset < 0 || len + offset > array.length || len + offset < 0) {
            throw new IndexOutOfBoundsException();
        }
        int x2 = 0;
        for (count = 0; count != len && (x2 = input.read(array, offset + count, len - count)) != -1; count += x2) {
        }
        return count;
    }

    public static void readFully(ReadableByteChannel channel, ByteBuffer byteBuffer) throws IOException {
        int read;
        int readNow;
        int expectedLength = byteBuffer.remaining();
        for (read = 0; read < expectedLength && (readNow = channel.read(byteBuffer)) > 0; read += readNow) {
        }
        if (read < expectedLength) {
            throw new EOFException();
        }
    }

    public static byte[] readRange(InputStream input, int len) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        IOUtils.copyRange(input, len, output);
        return output.toByteArray();
    }

    public static byte[] readRange(ReadableByteChannel input, int len) throws IOException {
        int readNow;
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ByteBuffer b2 = ByteBuffer.allocate(Math.min(len, 8024));
        for (int read = 0; read < len; read += readNow) {
            b2.limit(Math.min(len - read, b2.capacity()));
            readNow = input.read(b2);
            if (readNow <= 0) break;
            output.write(b2.array(), 0, readNow);
            b2.rewind();
        }
        return output.toByteArray();
    }

    public static long skip(InputStream input, long numToSkip) throws IOException {
        int read;
        long skipped;
        long available = numToSkip;
        while (numToSkip > 0L && (skipped = input.skip(numToSkip)) != 0L) {
            numToSkip -= skipped;
        }
        while (numToSkip > 0L && (read = IOUtils.readFully(input, SKIP_BUF, 0, (int)Math.min(numToSkip, 4096L))) >= 1) {
            numToSkip -= (long)read;
        }
        return available - numToSkip;
    }

    public static byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        IOUtils.copy(input, (OutputStream)output);
        return output.toByteArray();
    }

    private IOUtils() {
    }
}

