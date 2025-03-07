package org.example;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class VerifySign {
    public static void main(String[] args) throws Exception {
        String message = "Ayman Test";
        String ReceivedSignEncoded = "NIM6bhLWMfdXzJo36LZWHAA_zCRcOpVKgksJILPnG7U=";
        String secret = "1234567812345678";
        SecretKey secretKey = new SecretKeySpec(secret.getBytes(),0,secret.length(),"HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(secretKey);
        byte[] calculatedSignature = mac.doFinal(message.getBytes());
        String calculatedSignEncoded = Base64.getUrlEncoder().encodeToString(calculatedSignature);
        System.out.println(ReceivedSignEncoded.equals(calculatedSignEncoded)?"OK":"NOP");
    }
}
