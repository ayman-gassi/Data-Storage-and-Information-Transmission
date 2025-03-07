package org.example;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Crypt {
    public static void main(String[] args) throws Exception {
      String message = "Hello Hello";
      String secret = "1234567812345678";
      SecretKey secretKey = new SecretKeySpec(secret.getBytes(),0,secret.length(),"AES");
      Cipher cipher = Cipher.getInstance("AES");
      cipher.init(Cipher.ENCRYPT_MODE,secretKey);
      byte[] encryptedMessage = cipher.doFinal(message.getBytes());
      String encodedEncryptedMsg = Base64.getUrlEncoder().encodeToString(encryptedMessage);
      System.out.println(new String(encodedEncryptedMsg));
    }
}