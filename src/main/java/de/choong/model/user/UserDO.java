package de.choong.model.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import de.choong.model.BaseDO;

@Entity
@Table(name = "T_USER")
public class UserDO extends BaseDO {

    private static final long serialVersionUID = -4396531083449493842L;

    @Column(unique = true, length = 20, nullable = false)
    private String username;

    @Column(length = 20, nullable = false)
    private String password;

    @Column(length = 16, nullable = false)
    private String salt;

    @Column(length = 50, nullable = false)
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
