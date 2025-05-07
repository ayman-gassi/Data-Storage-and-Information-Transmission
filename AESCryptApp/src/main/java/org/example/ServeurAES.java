package org.example;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class ServeurAES {
    public static void main(String[] args) throws Exception {
            String encodedCryptedMsg = "CTiGj5SEeNgdcwzF1nhDhpBKhWHyqQ0m2S7ZFEhlFBafNVjuMoOGM12_MAC9U7PapE-1XPePAedMoDX6yvcBDVjffpYrw3DuNI7OaaWfYag=";
            byte []cryptedMsg = Base64.getUrlDecoder().decode(encodedCryptedMsg);
            String secret = "MDEyMzQ1Njc4OUFCQ0RFRkdISUpLTE1O";
            SecretKey secretKey = new SecretKeySpec(secret.getBytes(),0,secret.length(),"AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE,secretKey);
            byte[] decryptedMsg = cipher.doFinal(cryptedMsg);
            System.out.println(new String(decryptedMsg));
    }
}
