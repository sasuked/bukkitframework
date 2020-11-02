package io.github.sasuked.text;

import org.apache.commons.codec.digest.DigestUtils;

public class HashHelper {
	
	public static String hashString(String toHash) {
		try {
			return DigestUtils.sha256Hex(toHash);
		} catch (Exception e) {
			return null;
		}
	}
	
	public static boolean compareHash(String hashed, String toCompare) {
		return hashed.equals(hashString(toCompare));
	}
	
}
