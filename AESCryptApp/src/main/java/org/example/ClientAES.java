package org.example;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class ClientAES {
    public static void main(String[] args) throws Exception {
      String message = "\"Virement 5000 MAD vers compte xxxxxxxxxxx du Mr votre nom et votre prénom";
      String secret = "MDEyMzQ1Njc4OUFCQ0RFRkdISUpLTE1O";
      SecretKey secretKey = new SecretKeySpec(secret.getBytes(),0,secret.length(),"AES");
      Cipher cipher = Cipher.getInstance("AES");
      cipher.init(Cipher.ENCRYPT_MODE,secretKey);
      byte[] encryptedMessage = cipher.doFinal(message.getBytes());
      String encodedEncryptedMsg = Base64.getUrlEncoder().encodeToString(encryptedMessage);
      System.out.println(new String(encodedEncryptedMsg));
    }
}