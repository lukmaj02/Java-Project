package Bank;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Component
public class DecryptionManager {
    private static final String PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCDVgF8898VVENG" +
            "2xHc48A9gtpfK/OfZ774BiBmnyJF5+EDV9E2RjeQCs+SuRW/d8gKzhCwUJInhFAq" +
            "bsNSt7WDGgUp6OEDF2SRh0Jr7M/zcMUQpRWutMCWATpRELYGCT9znGV27Uxuo6Li" +
            "iJZAj82N48BKyqtCk4WJdp/R7qmCOjHOrkVfNzIPorTmsVZKSQ2KKMpaQm5apuOm" +
            "f49HUIN1lGMS9VvZvXI39836zJ3W0fXPRQgvaz1YYuhwp+nHms22FeWRiVOa1FKz" +
            "J0edlYrHOilDvheZxZPKMreff9CiPY1T/XPhAel+Pe+4EMkStkLsLM7gf9bu0w1I" +
            "E7ax9VcJAgMBAAECggEAL5bDdUZcqDrfIdaRc2unJeK7Q88GONV70h1OMkEpRxFN" +
            "bEuuyC7LRUtViYG9SqKZfqjKTUj0nJMKBUYbSma8w6NzCYuTtO4OB9luDPNDKp/L" +
            "GEvpPmPKgNAzFFqsyiUhs7+Q2HYFwvVSOSlq4Dv7LJXBAJjNU5NmyLeOE6CaFVCC" +
            "Bi2u7oqgXLrCQ3ipqqkua/sYxgdS3g7OqbGSZo+pIIrsRmu6Q73vxoxKT9Y9PO23" +
            "h6PybTNomxfeDYbiiwGlJuTUWD7AUkOcwyuenT1CuBB3DmdijScg0cQy23X7Dfx4" +
            "9imgAr98JEs8P0TLPASO43CQ29DlrVMxnjQxrrVKAQKBgQC5BqxFIVjENPcJpMSN" +
            "BsWQ8v+GwzMOpbVp9cHOTjL3VvkMl/KMVZE3TN/KTgMPVEUb9Q2MU0n1WP5raQml" +
            "30Bp/JFdpZnAjKfYzxlq3iNzh4xiL+p80XrFnyNBRAoIfHgzaIUxlOXDVhJ0qkUS" +
            "5u0QOIAeATOJnHRAaGibIuBHwQKBgQC1twgwCgU0+TL5A26qdf0ZNH6EcWzJTn2f" +
            "VHAMXoDprf7Be25vD0/ixfc439kDzEQ1VIvqG8dant8PFoGqEuPhQWzdl4knx8Ss" +
            "GNobvRuRiX1XJZv9TwGjwnV4PJkJ5aIW5EtmdhKF/wn1eDqzQyuwHrfYZkfrf7Px" +
            "m9R2TwEhSQKBgCJOGWyr1SBzzYXNNeAL7F+GnlfnIzVJBgzCUw+kMhDn4D80gPUB" +
            "/G2rZCbHNgwGjNW1JrDo1GKYAHv7up01emTsPiuFw68OpSQuqcDFtP77l8ywo6JG" +
            "2KOBcPDt44pVQZgOffuGZt9DuyJEW5A4nqWORDrL8DlId8llUlfJLJpBAoGADniD" +
            "82do4L4TnBb04ihBPpeal6t8sU9J271aqvF/rJxJlFjjA38hqnp/ZI0W+CF1MRYJ" +
            "ilwp9QJ/2vC85LFaal7Ob5sYtg1HkTjtbZYSsvVTAPSixnpbNBJRToDs4wfhb5dA" +
            "lelDMl6MCXtImWv4966Kj3YdthhWWSBCvCUwftkCgYEAg+W4p8jIUeWi481oMeXt" +
            "ydt3B5wVn9YapetL1rntUWgiAnDtJaxnaE6ClouvvKHIYCI+Jx/+K6+fHFGpSeXt" +
            "URxx94ebqbK7fOIObZpXdnFMlnwynLhQipLy7bZkMgXzbsL95s41sqsx4caPVutv" +
            "KhmWaQygxU3YS1LFL/cjSuc=";
    private PrivateKey privateKey;

    public void initFromString() throws Exception {
        PKCS8EncodedKeySpec keySpecPrivate = new PKCS8EncodedKeySpec(decode(PRIVATE_KEY));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        privateKey = keyFactory.generatePrivate(keySpecPrivate);
    }
    public String decrypt(String encryptedMessage) throws Exception{
        byte[] encryptedBytes = decode(encryptedMessage);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE,privateKey);
        byte[] decryptedMessage = cipher.doFinal(encryptedBytes);
        return new String(decryptedMessage, StandardCharsets.UTF_8);
    }

    public byte[] decode(String data) throws  Exception {
        return Base64.getDecoder().decode(data);
    }
}
