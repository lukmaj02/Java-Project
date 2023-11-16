package com.Projekt.Bankomat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.Random;


public final class Generators {
    private Generators(){}
    public static String generateUniqueRandomDigitNumber(Integer length) {
        long timestamp = System.currentTimeMillis();
        Random random = new Random();
        StringBuilder uniqueRandomStringBuilder = new StringBuilder(Long.toString(timestamp));
        for (int i = 0; i < length - Long.toString(timestamp).length(); i++) {
            uniqueRandomStringBuilder.append(random.nextInt(10));
        }
        return uniqueRandomStringBuilder.toString();
    }
}
