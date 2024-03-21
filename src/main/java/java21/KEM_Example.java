package java21;

import javax.crypto.DecapsulateException;
import javax.crypto.KEM;
import java.security.InvalidKeyException;
import java.security.KeyPairGenerator;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class KEM_Example {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, DecapsulateException {

        // Receiver - Alice
        var kpg = KeyPairGenerator.getInstance("X25519"); // e1
        var alice_kp = kpg.generateKeyPair();

        // Sender - Bob
        var kem1 = KEM.getInstance("DHKEM");
        var sender = kem1.newEncapsulator(alice_kp.getPublic()); // e2
        var enc_from_bob = sender.encapsulate();
        var secretKey_bob = enc_from_bob.key();

        // Receiver - Alice
        var kem2 = KEM.getInstance("DHKEM");
        var receiver = kem2.newDecapsulator(alice_kp.getPrivate());
        var secretKey_alice = receiver.decapsulate(enc_from_bob.encapsulation()); // e3

        System.out.println(Arrays.toString(secretKey_bob.getEncoded()));
        System.out.println(Arrays.toString(secretKey_alice.getEncoded()));
        assert Arrays.equals(secretKey_bob.getEncoded(), secretKey_alice.getEncoded());
    }
}
