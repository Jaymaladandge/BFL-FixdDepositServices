package com.bajaj.fixeddeposit.util;


import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

import net.iharder.Base64;

public class DataEncryption
{

	private static final Logger logger = Logger.getLogger(DataEncryption.class);
	public static String decrypt(final String encrypted) throws Exception
	{

		 try
		 {
		  SecretKey key = new SecretKeySpec(Base64.decode(Utility.getPropertyFileValue("AESKey").trim()), "AES");
		 logger.info("----------AES key---------"+Utility.getPropertyFileValue("AESKey"));
		 AlgorithmParameterSpec iv = new IvParameterSpec(Base64.decode("5D9r9ZVzEYYgha93/aUK2w=="));
		 byte[] decodeBase64 = Base64.decode(encrypted);
		 Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		 cipher.init(Cipher.DECRYPT_MODE, key, iv);
		 return new String(cipher.doFinal(decodeBase64), "UTF-8");
		 
		 } catch (Exception e)
		 {
			 logger.error("----------exception in decrypt-----",e);
			 return "{\"status\":\"invalid details\"}";
		 }
		 
		} 
	
	
	
	


}
