package com.bajaj.fixeddeposit.util;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

public class kycEncryDecryp {
	private kycEncryDecryp(){}

	private static final Logger logger = Logger.getLogger(kycEncryDecryp.class);

	public static byte[] encryptOp (byte[] plaintext,SecretKey key,byte[] iv ) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException  
	{

		Cipher cipher = Cipher.getInstance(Utility.getPropertyFileValue("EncryptionAlgo"));
		SecretKeySpec keySpec = new SecretKeySpec(key.getEncoded(), "AES");
		IvParameterSpec ivSpec = new IvParameterSpec(iv);
		cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

		return cipher.doFinal(plaintext);
	}

	public static String decryptOP (String cipherText, byte[] key,String v)throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException
	{
		logger.info("======== NTBEncryption : EncryptText============ "+cipherText+" "+key+" "+v);


		Cipher cipher = Cipher.getInstance(Utility.getPropertyFileValue("EncryptionAlgo"));
		SecretKeySpec skeySpec = new SecretKeySpec(key, Utility.getPropertyFileValue("EncryptionAlorithm"));


		IvParameterSpec ivSpec = new IvParameterSpec(v.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, skeySpec,ivSpec);
		byte[] decryptedText = cipher.doFinal(Base64.getDecoder().decode(cipherText));

		return new String(decryptedText);
	}


	public static String encryptAES(String strToEncrypt,String SecretKey,String ivKey)
	{
		String encrypDta="";
		try {
			IvParameterSpec iv = new IvParameterSpec(ivKey.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(SecretKey.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
			byte[] encrypted = cipher.doFinal(strToEncrypt.getBytes());
			encrypDta=Base64.getEncoder().encodeToString(encrypted);
		} catch (Exception ex)
		{
			logger.error("exception in encryptAES256", ex);
		}
		return encrypDta;
	}


	public static String decryptAES(String decrypted,String SecretKey,String ivKey)
	{
		String decrypt="";
		try {
			IvParameterSpec iv = new IvParameterSpec(ivKey.getBytes("UTF-8"));
			SecretKeySpec skeySpec = new SecretKeySpec(SecretKey.getBytes("UTF-8"), "AES");

			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] original = cipher.doFinal(Base64.getMimeDecoder().decode(decrypted));
			decrypt=new String(original);

		} catch (Exception ex) {
			logger.error("exception in decryptAES256", ex);
		}

		return decrypt;
	}





}
