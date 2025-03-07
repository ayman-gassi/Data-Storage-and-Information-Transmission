package org.example;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Decrypt {
    public static void main(String[] args) throws Exception {
            String encodedCryptedMsg = "XMbbkm2GEbXGZv2ZWuzKWg==";
            byte []cryptedMsg = Base64.getUrlDecoder().decode(encodedCryptedMsg);
            String secret = "1234567812345678";
            SecretKey secretKey = new SecretKeySpec(secret.getBytes(),0,secret.length(),"AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE,secretKey);
            byte[] decryptedMsg = cipher.doFinal(cryptedMsg);
            System.out.println(new String(decryptedMsg));
    }
}
