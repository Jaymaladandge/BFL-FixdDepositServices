package com.bajaj.fixeddeposit.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class Encryption {
	
	private static final Logger logger = Logger.getLogger(Encryption.class);
	
	private final int keySize;
	private final int iterationCount;
	private Cipher cipher;
	private static SecureRandom secrd = new SecureRandom();


	byte[] encrypted;
	SecretKey key;
	byte[] decrypted;

	static int iterationCountResponse = 1000;
	static int keySizeVal = 128;
	static String customEncValue = "abcdef";
	static String iv = "F27D5C9927726BCEFE7510B1BDD3D137";


	public Encryption(int keySize, int iterationCount) throws IOException 
	{
		this.keySize = keySize;
		this.iterationCount = iterationCount;
		try { 
			cipher = Cipher.getInstance(Utility.getPropertyFileValue("EncryptionAlgo"));
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e)
		{
			logger.error("------Exception in AesUtil Constructor------", e);
		}
	}

	public String encrypt(String salt, String iv, char[] passphrase, String plaintext)
			throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeySpecException {
		try {

			key = generateKey(salt, passphrase);
			encrypted = doFinal(Cipher.ENCRYPT_MODE, key, iv, plaintext.getBytes("UTF-8"));
		} catch (Exception e) {
			logger.error("------Exception in encrypt Method------", e);
		}
		return base64(encrypted);
	}

	public String decrypt(String salt, String iv, char[] passphrase, String ciphertext)
			throws IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException, InvalidKeySpecException,
			UnsupportedEncodingException {
		SecretKey keyVal = generateKey(salt, passphrase);
		decrypted = doFinal(Cipher.DECRYPT_MODE, keyVal, iv, base64(ciphertext));
		return new String(decrypted, "UTF-8");
	}
	private byte[] doFinal(int encryptMode, SecretKey key, String iv, byte[] bytes)
			throws IllegalBlockSizeException, BadPaddingException {
		try {
			cipher.init(encryptMode, key, new IvParameterSpec(hex(iv)));
		} catch (Exception e) {
			logger.error("------Exception in doFinal Method------", e);
		}
		return cipher.doFinal(bytes);
	}
	private SecretKey generateKey(String salt, char[] passphrase)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		try {
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			KeySpec spec = new PBEKeySpec(passphrase, hex(salt), iterationCount, keySize);
			key = new SecretKeySpec(factory.generateSecret(spec).getEncoded(), Utility.getPropertyFileValue("EncryptionAlorithm"));
		} catch (Exception e) {
			logger.error("------Exception in generateKey Method------", e);
		}
		return key;
	}
	public static String random(int length) {
		byte[] salt = new byte[length];
		new SecureRandom().nextBytes(salt);
		return hex(salt);
	}
	public static String base64(byte[] bytes) {
		return Base64.encodeBase64String(bytes);
	}
	public static byte[] base64(String str) {
		return Base64.decodeBase64(str);
	}
	public static String hex(byte[] bytes) {
		return Hex.encodeHexString(bytes);
	}
	public static byte[] hex(String str) {
		try {
			return Hex.decodeHex(str.toCharArray());
		} catch (DecoderException e) {
			throw new IllegalStateException(e);
		}
	}
	public static String getSalt(int length) throws IOException  
	{
		StringBuilder returnValue = new StringBuilder(length);
		String alphabet = Utility.getPropertyFileValue("alphabet");
		for (int i = 0; i < length; i++) {
			returnValue.append(alphabet.charAt(secrd.nextInt(alphabet.length())));
		}
		return new String(returnValue);
	}

	public static String getEncryptedVal(JSONObject responseVal) throws JSONException{
		JSONObject repStatus = new JSONObject();
		try{
			String encryptedRespVal="";
			String salt = Encryption.getSalt(20);
			Encryption aesUtil = new Encryption(keySizeVal, iterationCountResponse);
			char[] rpsphrase = Utility.getPropertyFileValue("Rpsphrase").toCharArray();
			encryptedRespVal = aesUtil.encrypt(salt, iv, rpsphrase, responseVal.toString());
			repStatus.put("salt", salt);
			repStatus.put("iv", iv);
			repStatus.put("passphrase", customEncValue);
			repStatus.put("encryptedRespVal", encryptedRespVal);


			String saltVal = "off1:"+repStatus.get("salt").toString();
			String ivVal = "off2:"+repStatus.get("iv").toString();
			String passVal = "off3:"+repStatus.get("passphrase").toString();
			String encrypVal = "off4:"+repStatus.get("encryptedRespVal").toString();
			
			String newEncrpitp = saltVal+"~"+ivVal+"~"+passVal+"~"+encrypVal;
			
			String newJsonString = new JSONObject().put("offer", newEncrpitp).toString();
			
			char[] pval = Utility.getPropertyFileValue("Rpsphrase").toCharArray();
			String ival = iv;
			String sval = Encryption.getSalt(20);
			
			String newoffer = aesUtil.encrypt(sval, ival, pval, newJsonString);
		
			//String finaloffer = "newoffer:"+newoffer+"~sval:"+sval;
			
			JSONObject offjson = new JSONObject();
			offjson.put("offjson", newoffer);
			offjson.put("newoffer",sval);
			
			return offjson.toString();
			
		}catch(Exception e){
			logger.error("========Exception getEncryptedVal=========",e);
			repStatus.put("finalStatus","Fail");
			return repStatus.toString();
		}
	}

}
