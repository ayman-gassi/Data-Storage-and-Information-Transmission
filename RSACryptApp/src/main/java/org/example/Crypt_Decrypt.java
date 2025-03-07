package org.example;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Crypt_Decrypt {
    public static void main(String[] args) throws  Exception{
      String message = "Hello Ayman";
      KeyPairGenerator keyPairGenerator =KeyPairGenerator.getInstance("RSA");
      KeyPair keyPair = keyPairGenerator.generateKeyPair();
      PublicKey publicKey = keyPair.getPublic();
      PrivateKey privateKey = keyPair.getPrivate();

      Cipher cipher = Cipher.getInstance("RSA");
      cipher.init(Cipher.ENCRYPT_MODE,publicKey);
      byte[] cryptedMessage = cipher.doFinal(message.getBytes());
      System.out.println(cryptedMessage);

      cipher.init(Cipher.DECRYPT_MODE,privateKey);
      byte[] descryptedMessage = cipher.doFinal(cryptedMessage);
      System.out.println(new String(descryptedMessage));

    }
}