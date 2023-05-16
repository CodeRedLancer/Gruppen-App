package org.abs.gruppenapp.services;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class PasswordService {
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");

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

    public static String getPepper() {
        return "g$KzfS/?X<aT]8d@3";
    }

    public static String toHexString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }

        return hexString.toString();
    }

    public static String[] hashPassword(String password) throws NoSuchAlgorithmException {
        String salt = getSalt(16);
        String hashedPassword = toHexString(getSHA(password + salt + getPepper()));

        return new String[]{hashedPassword, salt};

    }

    public static String[] hashPassword(String password, int saltLength) throws NoSuchAlgorithmException {
        String salt = getSalt(saltLength);
        String hashedPassword = toHexString(getSHA(password + salt + getPepper()));

        return new String[]{hashedPassword, salt};

    }

    public static Boolean validatePassword(String password, String salt, String hashedPassword) throws NoSuchAlgorithmException {
        String passwordToCheck = toHexString(getSHA(password + salt + getPepper()));

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