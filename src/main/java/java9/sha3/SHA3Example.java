package java9.sha3;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class SHA3Example {
    public static void main(String[] args) throws NoSuchAlgorithmException{
        String inputText = "Hello, World!";
        String hashFunction = "SHA3-256";
        // Create a MessageDigest instance for SHA3-256
        MessageDigest digest = MessageDigest.getInstance(hashFunction);

        // Perform the hash
        // compute the hash value of the input string using the digest() method
        byte[] hashBytes = digest.digest(inputText.getBytes(StandardCharsets.UTF_8));

        // Convert the hash to a hexadecimal string
        String hexString = HexFormat.of().formatHex(hashBytes);

        // Print the hash
        System.out.println("Input: " + inputText);
        System.out.println("Hash Function: " + hashFunction);
        System.out.println("Hashed Output - hex string: " + hexString);
    }
}
