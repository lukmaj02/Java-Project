package com.Projekt.Bankomat;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Generators{
    public static String generateRandomNumber(int numberOfDigits) {
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        result.append(random.nextInt(9) + 1);
        for (int i = 1; i < numberOfDigits; i++) {
            result.append(random.nextInt(10));
        }
        return result.toString();
    }
    public static String generateHash(String message) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        byte[] messageDigest = md.digest(message.getBytes());
        BigInteger bigInt = new BigInteger(1,messageDigest);
        return bigInt.toString();
    }
}
