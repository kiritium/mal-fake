package de.choong.model.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "T_USER")
public class UserDO implements Serializable {

    private static final long serialVersionUID = 4038226541758100768L;

    @Id
    @GeneratedValue
    private int id;

    @Column(unique = true, length = 20, nullable = false)
    private String username;

    @Column(length = 20, nullable = false)
    private String password;
    
    @Column(length = 16, nullable = false)
    private String salt;
    
    @Enumerated(EnumType.STRING)
    private UserRight userRight;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

	public UserRight getUserRight() {
		return userRight;
	}

	public void setUserRight(UserRight userRight) {
		this.userRight = userRight;
	}

}
