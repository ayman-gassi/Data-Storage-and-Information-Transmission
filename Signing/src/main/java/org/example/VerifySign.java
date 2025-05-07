package org.example;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class VerifySign {
    public static void main(String[] args) throws Exception {
        String message = "xxxxxxx.xx MAD au xx/xx/2025";
        String ReceivedSignEncoded = "iOy4ZLDXH33lszzTbZxxoUGKVN2ZKw7lXPWvS7kNkyE=";
        String secret = "MDEyMzQ1Njc4OUFCQ0RFRkdISUpLTE1O";
        SecretKey secretKey = new SecretKeySpec(secret.getBytes(),0,secret.length(),"HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKey);
        byte[] calculatedSignature = mac.doFinal(message.getBytes());
        String calculatedSignEncoded = Base64.getUrlEncoder().encodeToString(calculatedSignature);
        System.out.println(ReceivedSignEncoded.equals(calculatedSignEncoded)?"OK":"NOP");
    }
}
