package com.endava.util;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * Used for crypt/decrypt.
 * 
 */
public final class EncryptionUtil {
    private static final String ALGORITHM = "AES";
    private static final byte[] KEY_VALUE = new byte[] {'T', 'h', 'i', 's', 'I', 's', 'A', 'S', 'e', 'c', 'r', 'e',
            't', 'K', 'e', 'y' };

    private EncryptionUtil() {

    }

    /**
     * Encrypt a string.
     * 
     * @param valueToEnc
     *            String
     * @return encrypt String
     * @throws NoSuchPaddingException
     *             exception
     * @throws NoSuchAlgorithmException
     *             exception
     * @throws InvalidKeyException
     *             exception
     * @throws BadPaddingException
     *             exception
     * @throws IllegalBlockSizeException
     *             exception
     */
    public static String encrypt(String valueToEnc) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encValue = c.doFinal(valueToEnc.getBytes());
        String encryptedValue = new String(Base64.encodeBase64(encValue));
        return encryptedValue;
    }

    /**
     * Decrypt a String.
     * 
     * @param encryptedValue
     *            String
     * @return String decrypt
     * @throws NoSuchPaddingException
     *             exception
     * @throws NoSuchAlgorithmException
     *             exception
     * @throws InvalidKeyException
     *             exception
     * @throws BadPaddingException
     *             exception
     * @throws IllegalBlockSizeException
     *             exception
     */
    public static String decrypt(String encryptedValue) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = Base64.decodeBase64(encryptedValue.getBytes());
        byte[] decValue = c.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    private static Key generateKey() {
        Key key = new SecretKeySpec(KEY_VALUE, ALGORITHM);
        return key;
    }
}
