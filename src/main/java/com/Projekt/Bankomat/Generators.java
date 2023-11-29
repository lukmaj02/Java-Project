package com.Projekt.Bankomat;

import java.util.Random;

public class Generators{
    private Generators(){}
    public static String generateRandomNumber(int numberOfDigits) {
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        result.append(random.nextInt(9) + 1);
        for (int i = 1; i < numberOfDigits; i++) {
            result.append(random.nextInt(10));
        }
        return result.toString();
    }
}
