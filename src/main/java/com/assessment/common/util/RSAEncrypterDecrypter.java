package com.assessment.common.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.apache.commons.codec.binary.Base64;
public class RSAEncrypterDecrypter {
	private Cipher cipher;
	
	private static RSAEncrypterDecrypter encrypterDecrypter;
	
	private static PrivateKey privateKey;
	private static PublicKey publicKey;
	
    private RSAEncrypterDecrypter() throws NoSuchAlgorithmException, NoSuchPaddingException {
        this.cipher = Cipher.getInstance("RSA");
    }
    
    public static RSAEncrypterDecrypter getRSAEncrypterDecrypter() throws Exception{
    	if(encrypterDecrypter == null){
    		encrypterDecrypter = new RSAEncrypterDecrypter();
            privateKey = encrypterDecrypter.getPrivate("KeyPair/privateKey");
            publicKey = encrypterDecrypter.getPublic("KeyPair/publicKey");
    	}
    	return encrypterDecrypter;
    }

    // https://docs.oracle.com/javase/8/docs/api/java/security/spec/PKCS8EncodedKeySpec.html
    public PrivateKey getPrivate(String filename) throws Exception {
        byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    // https://docs.oracle.com/javase/8/docs/api/java/security/spec/X509EncodedKeySpec.html
    public PublicKey getPublic(String filename) throws Exception {
        byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    public void encryptFile(byte[] input, File output, PrivateKey key) 
        throws IOException, GeneralSecurityException {
        this.cipher.init(Cipher.ENCRYPT_MODE, key);
        writeToFile(output, this.cipher.doFinal(input));
    }

    public void decryptFile(byte[] input, File output, PublicKey key) 
        throws IOException, GeneralSecurityException {
        this.cipher.init(Cipher.DECRYPT_MODE, key);
        writeToFile(output, this.cipher.doFinal(input));
    }

    private void writeToFile(File output, byte[] toWrite)
            throws IllegalBlockSizeException, BadPaddingException, IOException {
        FileOutputStream fos = new FileOutputStream(output);
        fos.write(toWrite);
        fos.flush();
        fos.close();
    }

    public static String encryptText(String msg, PrivateKey key) 
            throws NoSuchAlgorithmException, NoSuchPaddingException,
            UnsupportedEncodingException, IllegalBlockSizeException, 
            BadPaddingException, InvalidKeyException {
    	encrypterDecrypter.cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.encodeBase64String(encrypterDecrypter.getCipher().doFinal(msg.getBytes("UTF-8")));
    }

    public static  String decryptText(String msg, PublicKey key)
            throws InvalidKeyException, UnsupportedEncodingException, 
            IllegalBlockSizeException, BadPaddingException {
    	encrypterDecrypter.getCipher().init(Cipher.DECRYPT_MODE, key);
        return new String(encrypterDecrypter.getCipher().doFinal(Base64.decodeBase64(msg)), "UTF-8");
    }

    public byte[] getFileInBytes(File f) throws IOException {
        FileInputStream fis = new FileInputStream(f);
        byte[] fbytes = new byte[(int) f.length()];
        fis.read(fbytes);
        fis.close();
        return fbytes;
    }
    
    

    public Cipher getCipher() {
		return cipher;
	}

	public void setCipher(Cipher cipher) {
		this.cipher = cipher;
	}
	
	

	public static PrivateKey getPrivateKey() {
		return privateKey;
	}

	public static void setPrivateKey(PrivateKey privateKey) {
		RSAEncrypterDecrypter.privateKey = privateKey;
	}

	public static PublicKey getPublicKey() {
		return publicKey;
	}

	public static void setPublicKey(PublicKey publicKey) {
		RSAEncrypterDecrypter.publicKey = publicKey;
	}

	public static void main(String[] args) throws Exception {
    	RSAEncrypterDecrypter ac = new RSAEncrypterDecrypter();
        PrivateKey privateKey = ac.getPrivate("KeyPair/privateKey");
        PublicKey publicKey = ac.getPublic("KeyPair/publicKey");

        String msg = ""+System.currentTimeMillis();
        String encrypted_msg = ac.encryptText(msg, privateKey);
        String decrypted_msg = ac.decryptText(encrypted_msg, publicKey);
        System.out.println("Original Message: " + msg + 
            "\nEncrypted Message: " + encrypted_msg
            + "\nDecrypted Message: " + decrypted_msg);

        
    }
}
