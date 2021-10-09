package com.license.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class LicenseKeyGenerator {

    private static LicenseConstants licenseConstants = new LicenseConstants();

    public static byte[] getRandomNonce(int numBytes) {
        byte[] nonce = new byte[numBytes];
        new SecureRandom().nextBytes(nonce);
        return nonce;
    }

    public static String hex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    // Password derived AES 256 bits secret key
    public static SecretKey getAESKeyFromPassword(char[] password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        KeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
        SecretKey secret = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
        return secret;
    }

    // Return a base64 encoded AES encrypted text
    public static String encrypt(byte[] pText, String password) throws Exception {
        byte[] salt = getRandomNonce(licenseConstants.SALT_LENGTH_BYTE);
        byte[] iv = getRandomNonce(licenseConstants.IV_LENGTH_BYTE);

        SecretKey aesKeyFromPassword = getAESKeyFromPassword(password.toCharArray(), salt);
        Cipher cipher = Cipher.getInstance(licenseConstants.ENCRYPT_ALGO);
        cipher.init(Cipher.ENCRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(licenseConstants.TAG_LENGTH_BIT, iv));

        byte[] cipherText = cipher.doFinal(pText);
        byte[] cipherTextWithIvSalt = ByteBuffer.allocate(iv.length + salt.length + cipherText.length).put(iv).put(salt).put(cipherText).array();

        return Base64.getEncoder().encodeToString(cipherTextWithIvSalt);
    }

    // We need the same password, salt and iv to decrypt it
    public static String decrypt(String cText, String password) throws Exception {
        byte[] decode = Base64.getDecoder().decode(cText.getBytes(licenseConstants.UTF_8));

        ByteBuffer bb = ByteBuffer.wrap(decode);
        byte[] iv = new byte[licenseConstants.IV_LENGTH_BYTE];
        bb.get(iv);

        byte[] salt = new byte[licenseConstants.SALT_LENGTH_BYTE];
        bb.get(salt);

        byte[] cipherText = new byte[bb.remaining()];
        bb.get(cipherText);

        SecretKey aesKeyFromPassword = getAESKeyFromPassword(password.toCharArray(), salt);
        Cipher cipher = Cipher.getInstance(licenseConstants.ENCRYPT_ALGO);
        cipher.init(Cipher.DECRYPT_MODE, aesKeyFromPassword, new GCMParameterSpec(licenseConstants.TAG_LENGTH_BIT, iv));
        byte[] plainText = cipher.doFinal(cipherText);

        return new String(plainText, licenseConstants.UTF_8);
    }

    // AES secret key
    public SecretKey getAESKey(int keysize) throws NoSuchAlgorithmException {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(keysize, SecureRandom.getInstanceStrong());
        return keyGen.generateKey();
    }
}
