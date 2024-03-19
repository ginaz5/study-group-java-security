package java9.securerandom;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
public class SecureRandomExample {

    public static void main(String[] args) {
        try {
            // Create a SecureRandom instance using the default algorithm
            SecureRandom secureRandom = SecureRandom.getInstanceStrong(); // Strong algorithm, as per JEP 273

            // Generate a random integer
            int randomNumber = secureRandom.nextInt();

            // Print the random number
            System.out.println("Random Number: " + randomNumber);
        } catch (NoSuchAlgorithmException e) {
            // Handle NoSuchAlgorithmException
            System.err.println("Error: " + e.getMessage());
        }
    }

}
