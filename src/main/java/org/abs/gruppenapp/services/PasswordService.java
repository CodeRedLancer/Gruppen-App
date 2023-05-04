package org.abs.gruppenapp.services;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class PasswordService {
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        /* MessageDigest instance for hashing using SHA256 */
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        /* digest() method called to calculate message digest of an input and return array of byte */
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    public static String getSalt(int length) {
        int leftLimit = 97; // a
        int rightLimit = 122; // z
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    public static String toHexString(byte[] hash) {
        /* Convert byte array of hash into digest */
        BigInteger number = new BigInteger(1, hash);

        /* Convert the digest into hex value */
        StringBuilder hexString = new StringBuilder(number.toString(16));

        /* Pad with leading zeros */
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    public static String[] hashPassword(String password) throws NoSuchAlgorithmException {
        String salt = getSalt(16);
        String hashedPassword = toHexString(getSHA(password + salt));

        return new String[]{hashedPassword, salt};

    }

    public static String[] hashPassword(String password, int saltLength) throws NoSuchAlgorithmException {
        String salt = getSalt(saltLength);
        String hashedPassword = toHexString(getSHA(password + salt));

        return new String[]{hashedPassword, salt};

    }

    public static Boolean validatePassword(String password, String salt, String hashedPassword) throws NoSuchAlgorithmException {
        String passwordToCheck = toHexString(getSHA(password + salt));

        return hashedPassword.equals(passwordToCheck);
    }

    public static void demo() {
        try {
            String[] passwort = hashPassword("meinPasswort");
            String[] passwort2 = hashPassword("meinPasswort", 16);

            System.out.println("Passwort: " + passwort[0] + " Salt: " + passwort[1]);
            System.out.println("Passwort: " + passwort2[0] + " Salt: " + passwort2[1]);

            System.out.println(validatePassword("meinPasswort", passwort[1], passwort[0]));

        } catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown for incorrect algorithm: " + e);
        }
    }
    public static void main(String[] args) {
        demo();
    }
}