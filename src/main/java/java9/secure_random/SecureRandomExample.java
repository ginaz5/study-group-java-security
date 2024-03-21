package java9.secure_random;

import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Set;
import java.security.Security;

public class SecureRandomExample {

    public static void main(String[] args) throws NoSuchAlgorithmException {

        generalRandom();

        viewAlgoListOfSecureRandom();

        secureRandomUsage();

        viewProviders();
    }

    static void secureRandomUsage() throws NoSuchAlgorithmException {
        // using the default algorithm
        SecureRandom secureRandomDefault = new SecureRandom();
        System.out.println("Default algo: " + secureRandomDefault.getAlgorithm());

        // change the algorithm
        SecureRandom secureRandomAlgoChanged = SecureRandom.getInstance("SHA1PRNG");
        System.out.println("Changed algo: " + secureRandomAlgoChanged.getAlgorithm());

        // Java 9
        SecureRandom drbg = SecureRandom.getInstance("DRBG");
        System.out.println(drbg);

        Security.setProperty("securerandom.drbg.config", "HMAC_DRBG");
        SecureRandom drbg_hmac = SecureRandom.getInstance("DRBG");
        System.out.println(drbg_hmac);

        System.out.println("Random Number: " + secureRandomDefault.nextInt());
    }

    static void viewAlgoListOfSecureRandom() {
        Set<String> algorithms = Security.getAlgorithms("SecureRandom");
        System.out.println("View algorithms: " + algorithms);
    }

    static void generalRandom() {
        // Random are thread-safe by default
        Random random = new Random();
        int number = random.nextInt();
        System.out.println(number);

        // multi-thread
        int num2 = ThreadLocalRandom.current().nextInt();
        System.out.println(num2);
    }

    static void viewProviders() {
        // Iterate over all installed providers
        for (Provider provider : Security.getProviders()) {
            System.out.println("Provider: " + provider.getName() + ", Version: " + provider.getVersion());
        }
    }
}
