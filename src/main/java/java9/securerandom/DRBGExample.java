package java9.securerandom;

import java.security.DrbgParameters;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.SecureRandomParameters;

import static java.security.DrbgParameters.Capability.NONE;
import static java.security.DrbgParameters.Capability.PR_AND_RESEED;

public class DRBGExample {
        public static void main(String[] args) throws NoSuchAlgorithmException {

            SecureRandom drbg;
            byte[] buffer = new byte[32];

            // Any DRBG is OK
            drbg = SecureRandom.getInstance("DRBG");
            drbg.nextBytes(buffer);

            SecureRandomParameters params = drbg.getParameters();
            if (params instanceof DrbgParameters.Instantiation) {
                DrbgParameters.Instantiation ins = (DrbgParameters.Instantiation) params;
                if (ins.getCapability().supportsReseeding()) {
                    drbg.reseed();
                }
            }

            // The following call requests a weak DRBG instance. It is only
            // guaranteed to support 112 bits of security strength.
            drbg = SecureRandom.getInstance("DRBG",
                    DrbgParameters.instantiation(112, NONE, null));

            // Both the next two calls will likely fail, because drbg could be
            // instantiated with a smaller strength with no prediction resistance
            // support.
            drbg.nextBytes(buffer,
                    DrbgParameters.nextBytes(256, false, "more".getBytes()));
            drbg.nextBytes(buffer,
                    DrbgParameters.nextBytes(112, true, "more".getBytes()));

            // The following call requests a strong DRBG instance, with a
            // personalization string. If it successfully returns an instance,
            // that instance is guaranteed to support 256 bits of security strength
            // with prediction resistance available.
            drbg = SecureRandom.getInstance("DRBG", DrbgParameters.instantiation(
                    256, PR_AND_RESEED, "hello".getBytes()));

            // Prediction resistance is not requested in this single call,
            // but an additional input is used.
            drbg.nextBytes(buffer,
                    DrbgParameters.nextBytes(-1, false, "more".getBytes()));

            // Same for this call.
            drbg.reseed(DrbgParameters.reseed(false, "extra".getBytes()));
        }
}
