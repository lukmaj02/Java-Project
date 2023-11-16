package com.Projekt.Bankomat;

import org.springframework.context.annotation.Bean;

import java.util.Random;


public final class Generators {
    @Bean
    public String generateUniqueRandom26DigitNumber(Integer length) {
        Random random = new Random();
        long timestamp = System.currentTimeMillis();
        StringBuilder uniqueRandomStringBuilder = new StringBuilder(Long.toString(timestamp));

        // Generate the remaining digits for the 26-digit number
        for (int i = 0; i < length - Long.toString(timestamp).length(); i++) {
            // Append a random digit (0-9) to the StringBuilder
            uniqueRandomStringBuilder.append(random.nextInt(10));
        }

        // Convert the StringBuilder to a String and return
        return uniqueRandomStringBuilder.toString();
    }
}
