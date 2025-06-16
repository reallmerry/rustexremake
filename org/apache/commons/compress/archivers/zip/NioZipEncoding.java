/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import org.apache.commons.compress.archivers.zip.CharsetAccessor;
import org.apache.commons.compress.archivers.zip.ZipEncoding;
import org.apache.commons.compress.archivers.zip.ZipEncodingHelper;

class NioZipEncoding
implements ZipEncoding,
CharsetAccessor {
    private static final char REPLACEMENT = '?';
    private static final byte[] REPLACEMENT_BYTES = new byte[]{63};
    private static final String REPLACEMENT_STRING = String.valueOf('?');
    private static final char[] HEX_CHARS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private final Charset charset;
    private final boolean useReplacement;

    private static ByteBuffer encodeFully(CharsetEncoder enc, CharBuffer cb, ByteBuffer out) {
        ByteBuffer o2 = out;
        while (cb.hasRemaining()) {
            CoderResult result = enc.encode(cb, o2, false);
            if (!result.isOverflow()) continue;
            int increment = NioZipEncoding.estimateIncrementalEncodingSize(enc, cb.remaining());
            o2 = ZipEncodingHelper.growBufferBy(o2, increment);
        }
        return o2;
    }

    private static CharBuffer encodeSurrogate(CharBuffer cb, char c2) {
        cb.position(0).limit(6);
        cb.put('%');
        cb.put('U');
        cb.put(HEX_CHARS[c2 >> 12 & 0xF]);
        cb.put(HEX_CHARS[c2 >> 8 & 0xF]);
        cb.put(HEX_CHARS[c2 >> 4 & 0xF]);
        cb.put(HEX_CHARS[c2 & 0xF]);
        cb.flip();
        return cb;
    }

    private static int estimateIncrementalEncodingSize(CharsetEncoder enc, int charCount) {
        return (int)Math.ceil((float)charCount * enc.averageBytesPerChar());
    }

    private static int estimateInitialBufferSize(CharsetEncoder enc, int charChount) {
        float first = enc.maxBytesPerChar();
        float rest = (float)(charChount - 1) * enc.averageBytesPerChar();
        return (int)Math.ceil(first + rest);
    }

    NioZipEncoding(Charset charset, boolean useReplacement) {
        this.charset = charset;
        this.useReplacement = useReplacement;
    }

    @Override
    public boolean canEncode(String name) {
        CharsetEncoder enc = this.newEncoder();
        return enc.canEncode(name);
    }

    @Override
    public String decode(byte[] data) throws IOException {
        return this.newDecoder().decode(ByteBuffer.wrap(data)).toString();
    }

    @Override
    public ByteBuffer encode(String name) {
        CharsetEncoder enc = this.newEncoder();
        CharBuffer cb = CharBuffer.wrap(name);
        CharBuffer tmp = null;
        ByteBuffer out = ByteBuffer.allocate(NioZipEncoding.estimateInitialBufferSize(enc, cb.remaining()));
        while (cb.hasRemaining()) {
            CoderResult res = enc.encode(cb, out, false);
            if (res.isUnmappable() || res.isMalformed()) {
                int spaceForSurrogate = NioZipEncoding.estimateIncrementalEncodingSize(enc, 6 * res.length());
                if (spaceForSurrogate > out.remaining()) {
                    int charCount = 0;
                    for (int i2 = cb.position(); i2 < cb.limit(); ++i2) {
                        charCount += !enc.canEncode(cb.get(i2)) ? 6 : 1;
                    }
                    int totalExtraSpace = NioZipEncoding.estimateIncrementalEncodingSize(enc, charCount);
                    out = ZipEncodingHelper.growBufferBy(out, totalExtraSpace - out.remaining());
                }
                if (tmp == null) {
                    tmp = CharBuffer.allocate(6);
                }
                for (int i3 = 0; i3 < res.length(); ++i3) {
                    out = NioZipEncoding.encodeFully(enc, NioZipEncoding.encodeSurrogate(tmp, cb.get()), out);
                }
                continue;
            }
            if (res.isOverflow()) {
                int increment = NioZipEncoding.estimateIncrementalEncodingSize(enc, cb.remaining());
                out = ZipEncodingHelper.growBufferBy(out, increment);
                continue;
            }
            if (!res.isUnderflow() && !res.isError()) continue;
            break;
        }
        enc.encode(cb, out, true);
        out.limit(out.position());
        out.rewind();
        return out;
    }

    @Override
    public Charset getCharset() {
        return this.charset;
    }

    private CharsetDecoder newDecoder() {
        if (!this.useReplacement) {
            return this.charset.newDecoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT);
        }
        return this.charset.newDecoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE).replaceWith(REPLACEMENT_STRING);
    }

    private CharsetEncoder newEncoder() {
        if (this.useReplacement) {
            return this.charset.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE).replaceWith(REPLACEMENT_BYTES);
        }
        return this.charset.newEncoder().onMalformedInput(CodingErrorAction.REPORT).onUnmappableCharacter(CodingErrorAction.REPORT);
    }
}

