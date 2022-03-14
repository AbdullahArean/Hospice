package com.aem.hospice.arean;
import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;

public class Encryption {
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public Encryption( ){
        try{
            KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
            generator.initialize(2048);
            KeyPair pair = generator.generateKeyPair();
            publicKey = pair.getPublic();
            privateKey = pair.getPrivate();
        }
        catch (Exception ignored){}
    }
    public String Encrypt( String message, PublicKey publicKey) throws Exception{
        byte[] messageToBytes = message.getBytes();
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedByte = cipher.doFinal(messageToBytes);
        return encode(encryptedByte);
    }
    private String encode(byte[] data) {return Base64.getEncoder().encodeToString(data);
    }
    public String Decrypt(String encryptedMessage, PrivateKey privateKey) throws Exception {
        byte[] encryptedBytes = decode(encryptedMessage);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedMessage = cipher.doFinal(encryptedBytes);
        return new String(decryptedMessage, "UTF8");
    }
    private byte[] decode(String data) {return Base64.getDecoder().decode(data);}
    public PublicKey getPublicKey(){
        return publicKey;
    }
    public PrivateKey getPrivateKey(){
        return privateKey;
    }
    public static void main(String[] args) throws Exception {
        Encryption e = new Encryption();
        String s =e.Encrypt("1213",e.getPublicKey());
        System.out.println(e.Decrypt(s, e.getPrivateKey()));

    }
}
