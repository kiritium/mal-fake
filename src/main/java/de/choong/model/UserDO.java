package de.choong.model;

import java.io.Serializable;

/**
 * User
 *
 */
public class UserDO implements Serializable {

	private static final long serialVersionUID = 4038226541758100768L;
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
