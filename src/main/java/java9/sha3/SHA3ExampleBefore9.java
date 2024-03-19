package java9.sha3;

import org.bouncycastle.jcajce.provider.digest.SHA3;

import java.nio.charset.StandardCharsets;
import java.util.HexFormat;

public class SHA3ExampleBefore9 {
    public static void main(String[] args) {
        String input = "Hello, World!";
        SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest256(); // For SHA3-256, for example

        byte[] hashBytes = digestSHA3.digest(input.getBytes(StandardCharsets.UTF_8));

        // Convert the byte array to a hex string
        String hexString = HexFormat.of().formatHex(hashBytes);

        System.out.println("Input text: " + input);
        System.out.println("SHA-3-256 Hash: " + hexString);
    }
}

