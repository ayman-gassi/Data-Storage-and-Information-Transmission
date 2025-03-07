package org.example;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.Base64;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class PreSign {
    public static void main(String[] args) throws Exception{
       String message = "Ayman Test";
       String secret = "1234567812345678";
       SecretKey secretKey = new SecretKeySpec(secret.getBytes(),0,secret.length(),"HmacSHA256");

        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKey);
        byte[] signature = mac.doFinal(message.getBytes());
        String SignatureEncoded = Base64.getUrlEncoder().encodeToString(signature);
        System.out.println(SignatureEncoded);
    }
}