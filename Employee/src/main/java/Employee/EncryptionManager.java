package Employee;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class EncryptionManager {
    private EncryptionManager(){}
    private static final String PUBLIC_KEY = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAKYmNjmj9eQAvNFVVkZToJ4fMLmnUbRYEN8nM9lC8YNItdOd8d5h5FwfLv2iti5ky4TYZ+1a4ZQ0f/X3+fiFw2ECAwEAAQ==";
    private static PublicKey publicKey;

    public static String encode(byte[] data){
        return Base64.getEncoder().encodeToString(data);
    }

    public static byte[] decode(String data) throws  Exception {
        return Base64.getDecoder().decode(data);
    }

    public static void initFromString() throws Exception {
        X509EncodedKeySpec keySpecPublic = new X509EncodedKeySpec(decode(PUBLIC_KEY));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        publicKey = keyFactory.generatePublic(keySpecPublic);
    }
    public static String encrypt(String msg) throws Exception{
        byte[] messageToBytes = msg.getBytes();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE,publicKey);
        byte[] encryptedBytes = cipher.doFinal(messageToBytes);
        return encode(encryptedBytes);
    }
}
