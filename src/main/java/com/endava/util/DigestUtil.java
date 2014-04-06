package com.endava.util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class DigestUtil {
	
	public static final String MD5Instance = "MD5";

	public static String digest(String source) throws Exception{
		String md5 = null;

		MessageDigest mdEnc = MessageDigest.getInstance(MD5Instance); // Encryption
																	  // algorithm
		mdEnc.update(source.getBytes(), 0, source.length());
		md5 = new BigInteger(1, mdEnc.digest()).toString(16); 	// Encrypted
																// string
		return md5;
	}
	
}
