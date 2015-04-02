package de.choong.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import org.apache.wicket.util.crypt.Base64;

import de.choong.UserRight;
import de.choong.model.UserDO;

public class UserUtil {
	public static String generateSalt() {
		byte[] salt = new byte[32];
		final Random r = new SecureRandom();
		r.nextBytes(salt);
		String encodedSalt = Base64.encodeBase64String(salt);
		return encodedSalt.substring(0, 16);
	}

	public static String hash(String password, String salt) {
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			return "";
		}
		messageDigest.update((password + salt).getBytes());
		String hash = new String(messageDigest.digest());
		return hash;
	}

	public static boolean isLoggedIn() {
		return getCurrentUser() != null;
	}

	// TODO load the user from the session
	public static UserDO getCurrentUser() {
		return null;
	}

	public static boolean hasRight(UserRight right) {
		if (isLoggedIn()) {
			UserDO user = getCurrentUser();
			return user.getUserRight().compareTo(right) <= 0;
		}
		return false;
	}

}