package Employee;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class EncryptionManager {
    private EncryptionManager(){}
    private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAg1YBfPPfFVRDRtsR3OPA" +
            "PYLaXyvzn2e++AYgZp8iRefhA1fRNkY3kArPkrkVv3fICs4QsFCSJ4RQKm7DUre1" +
            "gxoFKejhAxdkkYdCa+zP83DFEKUVrrTAlgE6URC2Bgk/c5xldu1MbqOi4oiWQI/N" +
            "jePASsqrQpOFiXaf0e6pgjoxzq5FXzcyD6K05rFWSkkNiijKWkJuWqbjpn+PR1CD" +
            "dZRjEvVb2b1yN/fN+syd1tH1z0UIL2s9WGLocKfpx5rNthXlkYlTmtRSsydHnZWK" +
            "xzopQ74XmcWTyjK3n3/Qoj2NU/1z4QHpfj3vuBDJErZC7CzO4H/W7tMNSBO2sfVX" +
            "CQIDAQAB";
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
