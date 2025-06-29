/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.sevenz;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.compress.PasswordRequiredException;
import org.apache.commons.compress.archivers.sevenz.AES256Options;
import org.apache.commons.compress.archivers.sevenz.AbstractCoder;
import org.apache.commons.compress.archivers.sevenz.Coder;

class AES256SHA256Decoder
extends AbstractCoder {
    static byte[] sha256Password(byte[] password, int numCyclesPower, byte[] salt) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
            throw new IllegalStateException("SHA-256 is unsupported by your Java implementation", noSuchAlgorithmException);
        }
        byte[] extra = new byte[8];
        block2: for (long j2 = 0L; j2 < 1L << numCyclesPower; ++j2) {
            digest.update(salt);
            digest.update(password);
            digest.update(extra);
            for (int k2 = 0; k2 < extra.length; ++k2) {
                int n2 = k2;
                extra[n2] = (byte)(extra[n2] + 1);
                if (extra[k2] != 0) continue block2;
            }
        }
        return digest.digest();
    }

    static byte[] sha256Password(char[] password, int numCyclesPower, byte[] salt) {
        return AES256SHA256Decoder.sha256Password(AES256SHA256Decoder.utf16Decode(password), numCyclesPower, salt);
    }

    static byte[] utf16Decode(char[] chars) {
        if (chars == null) {
            return null;
        }
        ByteBuffer encoded = StandardCharsets.UTF_16LE.encode(CharBuffer.wrap(chars));
        if (encoded.hasArray()) {
            return encoded.array();
        }
        byte[] e2 = new byte[encoded.remaining()];
        encoded.get(e2);
        return e2;
    }

    AES256SHA256Decoder() {
        super(AES256Options.class);
    }

    @Override
    InputStream decode(final String archiveName, final InputStream in, long uncompressedLength, final Coder coder, final byte[] passwordBytes, int maxMemoryLimitInKb) {
        return new InputStream(){
            private boolean isInitialized;
            private CipherInputStream cipherInputStream;

            @Override
            public void close() throws IOException {
                if (this.cipherInputStream != null) {
                    this.cipherInputStream.close();
                }
            }

            private CipherInputStream init() throws IOException {
                byte[] aesKeyBytes;
                if (this.isInitialized) {
                    return this.cipherInputStream;
                }
                if (coder.properties == null) {
                    throw new IOException("Missing AES256 properties in " + archiveName);
                }
                if (coder.properties.length < 2) {
                    throw new IOException("AES256 properties too short in " + archiveName);
                }
                int byte0 = 0xFF & coder.properties[0];
                int numCyclesPower = byte0 & 0x3F;
                int byte1 = 0xFF & coder.properties[1];
                int saltSize = (byte0 >> 7 & 1) + (byte1 >> 4);
                int ivSize = (byte0 >> 6 & 1) + (byte1 & 0xF);
                if (2 + saltSize + ivSize > coder.properties.length) {
                    throw new IOException("Salt size + IV size too long in " + archiveName);
                }
                byte[] salt = new byte[saltSize];
                System.arraycopy(coder.properties, 2, salt, 0, saltSize);
                byte[] iv = new byte[16];
                System.arraycopy(coder.properties, 2 + saltSize, iv, 0, ivSize);
                if (passwordBytes == null) {
                    throw new PasswordRequiredException(archiveName);
                }
                if (numCyclesPower == 63) {
                    aesKeyBytes = new byte[32];
                    System.arraycopy(salt, 0, aesKeyBytes, 0, saltSize);
                    System.arraycopy(passwordBytes, 0, aesKeyBytes, saltSize, Math.min(passwordBytes.length, aesKeyBytes.length - saltSize));
                } else {
                    aesKeyBytes = AES256SHA256Decoder.sha256Password(passwordBytes, numCyclesPower, salt);
                }
                SecretKeySpec aesKey = AES256Options.newSecretKeySpec(aesKeyBytes);
                try {
                    Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
                    cipher.init(2, (Key)aesKey, new IvParameterSpec(iv));
                    this.cipherInputStream = new CipherInputStream(in, cipher);
                    this.isInitialized = true;
                    return this.cipherInputStream;
                } catch (GeneralSecurityException generalSecurityException) {
                    throw new IllegalStateException("Decryption error (do you have the JCE Unlimited Strength Jurisdiction Policy Files installed?)", generalSecurityException);
                }
            }

            @Override
            public int read() throws IOException {
                return this.init().read();
            }

            @Override
            public int read(byte[] b2, int off, int len) throws IOException {
                return this.init().read(b2, off, len);
            }
        };
    }

    @Override
    OutputStream encode(final OutputStream out, Object options) throws IOException {
        final AES256Options opts = (AES256Options)options;
        return new OutputStream(){
            private final CipherOutputStream cipherOutputStream;
            private final int cipherBlockSize;
            private final byte[] cipherBlockBuffer;
            private int count;
            {
                this.cipherOutputStream = new CipherOutputStream(out, opts.getCipher());
                this.cipherBlockSize = opts.getCipher().getBlockSize();
                this.cipherBlockBuffer = new byte[this.cipherBlockSize];
                this.count = 0;
            }

            @Override
            public void close() throws IOException {
                if (this.count > 0) {
                    this.cipherOutputStream.write(this.cipherBlockBuffer);
                }
                this.cipherOutputStream.close();
            }

            @Override
            public void flush() throws IOException {
                this.cipherOutputStream.flush();
            }

            private void flushBuffer() throws IOException {
                this.cipherOutputStream.write(this.cipherBlockBuffer);
                this.count = 0;
                Arrays.fill(this.cipherBlockBuffer, (byte)0);
            }

            @Override
            public void write(byte[] b2, int off, int len) throws IOException {
                int gap = len + this.count > this.cipherBlockSize ? this.cipherBlockSize - this.count : len;
                System.arraycopy(b2, off, this.cipherBlockBuffer, this.count, gap);
                this.count += gap;
                if (this.count == this.cipherBlockSize) {
                    this.flushBuffer();
                    if (len - gap >= this.cipherBlockSize) {
                        int multipleCipherBlockSizeLen = (len - gap) / this.cipherBlockSize * this.cipherBlockSize;
                        this.cipherOutputStream.write(b2, off + gap, multipleCipherBlockSizeLen);
                        gap += multipleCipherBlockSizeLen;
                    }
                    System.arraycopy(b2, off + gap, this.cipherBlockBuffer, 0, len - gap);
                    this.count = len - gap;
                }
            }

            @Override
            public void write(int b2) throws IOException {
                this.cipherBlockBuffer[this.count++] = (byte)b2;
                if (this.count == this.cipherBlockSize) {
                    this.flushBuffer();
                }
            }
        };
    }

    @Override
    byte[] getOptionsAsProperties(Object options) throws IOException {
        AES256Options opts = (AES256Options)options;
        byte[] props = new byte[2 + opts.getSalt().length + opts.getIv().length];
        props[0] = (byte)(opts.getNumCyclesPower() | (opts.getSalt().length == 0 ? 0 : 128) | (opts.getIv().length == 0 ? 0 : 64));
        if (opts.getSalt().length != 0 || opts.getIv().length != 0) {
            props[1] = (byte)((opts.getSalt().length == 0 ? 0 : opts.getSalt().length - 1) << 4 | (opts.getIv().length == 0 ? 0 : opts.getIv().length - 1));
            System.arraycopy(opts.getSalt(), 0, props, 2, opts.getSalt().length);
            System.arraycopy(opts.getIv(), 0, props, 2 + opts.getSalt().length, opts.getIv().length);
        }
        return props;
    }
}

