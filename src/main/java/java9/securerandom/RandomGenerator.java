package java9.securerandom;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomGenerator {
    public static void main(String[] args) {
        // Random are thread-safe by default
        Random random = new Random();
        int number = random.nextInt(1000);
        System.out.println(number);

        int num2 = ThreadLocalRandom.current().nextInt(1000);
        System.out.println(num2);

    }
}
