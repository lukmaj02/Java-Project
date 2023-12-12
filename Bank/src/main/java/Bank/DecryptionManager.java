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
    private static final String PRIVATE_KEY = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEApiY2OaP15AC80VVWRlOgnh8wuadRtFgQ3ycz2" +
            "ULxg0i1053x3mHkXB8u/aK2LmTLhNhn7VrhlDR/9ff5+IXDYQIDAQABAj8KjwZ+VpfdIw3gDjDVZkc+1/aCo9jqjJb0x/mrRSQS7IHCwbT1M8igiV4YLd+ux" +
            "44JRW/IbfvpNHDhsrtp75MCIQDzOV6VbwVV0snixjNhpt+GFaIrg7+IAItzDTdHtmRsiwIhAK7gaxVmoRWnrAlsN5qroQN/2NviuiR5HRIiwmyffHFDAiEAgW" +
            "4HCihhQ3F/Y4EreIyvqA+9E8DxXHNFwfzHbQ7/U88CIQCkTfhukBGTLgmGhOWtbcoHZqxS1DA+Koj0zDHOKv6ZzwIhAJ6YGYMSPCmbZEmYnSXT+obFFREOc/E7bqO4dYa7t4M7";
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
