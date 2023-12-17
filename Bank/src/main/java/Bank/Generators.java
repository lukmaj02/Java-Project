package Bank;

import java.util.Random;

public class Generators{
    private Generators(){}
    public static Random random = new Random();
    public static String generateRandomNumber(int numberOfDigits) {
        StringBuilder result = new StringBuilder();
        result.append(random.nextInt(9) + 1);
        for (int i = 1; i < numberOfDigits; i++) {
            result.append(random.nextInt(10));
        }
        return result.toString();
    }
}
