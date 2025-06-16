/*
 * Decompiled with CFR 0.153-SNAPSHOT (d6f6758-dirty).
 */
package org.apache.commons.compress.archivers.sevenz;

import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.compress.archivers.sevenz.AES256SHA256Decoder;

class AES256Options {
    static final String ALGORITHM = "AES";
    static final String TRANSFORMATION = "AES/CBC/NoPadding";
    private final byte[] salt;
    private final byte[] iv;
    private final int numCyclesPower;
    private final Cipher cipher;

    static SecretKeySpec newSecretKeySpec(byte[] bytes) {
        return new SecretKeySpec(bytes, ALGORITHM);
    }

    private static byte[] randomBytes(int size) {
        byte[] bytes = new byte[size];
        try {
            SecureRandom.getInstanceStrong().nextBytes(bytes);
        } catch (NoSuchAlgorithmException e2) {
            throw new IllegalStateException("No strong secure random available to generate strong AES key", e2);
        }
        return bytes;
    }

    public AES256Options(char[] password) {
        this(password, new byte[0], AES256Options.randomBytes(16), 19);
    }

    public AES256Options(char[] password, byte[] salt, byte[] iv, int numCyclesPower) {
        this.salt = salt;
        this.iv = iv;
        this.numCyclesPower = numCyclesPower;
        byte[] aesKeyBytes = AES256SHA256Decoder.sha256Password(password, numCyclesPower, salt);
        SecretKeySpec aesKey = AES256Options.newSecretKeySpec(aesKeyBytes);
        try {
            this.cipher = Cipher.getInstance(TRANSFORMATION);
            this.cipher.init(1, (Key)aesKey, new IvParameterSpec(iv));
        } catch (GeneralSecurityException generalSecurityException) {
            throw new IllegalStateException("Encryption error (do you have the JCE Unlimited Strength Jurisdiction Policy Files installed?)", generalSecurityException);
        }
    }

    Cipher getCipher() {
        return this.cipher;
    }

    byte[] getIv() {
        return this.iv;
    }

    int getNumCyclesPower() {
        return this.numCyclesPower;
    }

    byte[] getSalt() {
        return this.salt;
    }
}

