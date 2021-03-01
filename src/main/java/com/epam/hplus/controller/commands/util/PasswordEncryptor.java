package com.epam.hplus.controller.commands.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryptor {
    private static final int POSITIVE_SIGN = 1;
    private static final int HEX_RADIX = 16;
    private static final String SHA_1_ALGORITHM = "SHA-1";
    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordEncryptor.class);

    private PasswordEncryptor() {
    }

    public static String encryptPassword(String originalPassword) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(SHA_1_ALGORITHM);
            messageDigest.update(originalPassword.getBytes(StandardCharsets.UTF_8));
            byte[] passwordEncryptedArray = messageDigest.digest();
            BigInteger passwordEncryptedNumber =
                    new BigInteger(POSITIVE_SIGN, passwordEncryptedArray);
            return passwordEncryptedNumber.toString(HEX_RADIX);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }
}
