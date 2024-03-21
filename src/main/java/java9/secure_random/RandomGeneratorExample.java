package java9.secure_random;

import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

public class RandomGeneratorExample {
    public static void main(String[] args) {

        // Listing available RNG algorithms
        RandomGeneratorFactory.all()
                .map(RandomGeneratorFactory::name)
                .forEach(System.out::println);

        // Obtaining a RandomGenerator instance by name
        RandomGenerator randomGenerator = RandomGeneratorFactory.of("Xoshiro256PlusPlus").create();

        // Using the RandomGenerator
        System.out.println("Random number: " + randomGenerator.nextInt());

    }
}
