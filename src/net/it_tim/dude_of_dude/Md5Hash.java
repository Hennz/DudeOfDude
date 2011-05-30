package net.it_tim.dude_of_dude;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5Hash {
	public Md5Hash(String text) {
		String plaintext = text;
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(plaintext.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			hashtext = bigInt.toString(16);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		while (hashtext.length() < 32) {
			hashtext = "0" + hashtext;
		}
	}

	@Override
	public String toString() {
		return hashtext;
	}

	private String hashtext = "";
}
