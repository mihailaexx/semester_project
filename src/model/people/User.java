package model.people;

import enums.SEX;
import utils.SecurityUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public abstract class User extends Person implements Serializable {
    private static final long serialVersionUID = 2L;

    private int userId;
    private String username; // Now separate from email
    private String email;
    private String passwordHash;
    private String salt;
    private String phoneNumber;
    private String citizenship;

    // Constructor for initial user creation
    public User(String name, String surname, SEX sex, Date birthDate, String username, String email, String password, String phoneNumber, String citizenship) {
        super(name, surname, sex, birthDate);
        this.username = username;
        this.email = email;
        this.salt = SecurityUtils.generateSalt();
        this.passwordHash = SecurityUtils.hashPassword(password, this.salt);
        this.phoneNumber = phoneNumber;
        this.citizenship = citizenship;
    }

    // Constructor for creating users from data (e.g., when loading from memory)
    public User(int userId, String username, String email, String passwordHash, String name, String surname, SEX sex, Date birthDate, String phoneNumber, String citizenship) {
        super(name, surname, sex, birthDate);
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.passwordHash = SecurityUtils.hashPassword(passwordHash, this.salt);
        this.salt = SecurityUtils.generateSalt();;
        this.phoneNumber = phoneNumber;
        this.citizenship = citizenship;
    }

    // Getters and setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public boolean authenticate(String password) {
        String hashedInputPassword = SecurityUtils.hashPassword(password, this.salt);
        return this.passwordHash.equals(hashedInputPassword);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return userId == user.userId &&
                Objects.equals(username, user.username) &&
                Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, username, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", citizenship='" + citizenship + '\'' +
                ", name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", sex=" + getSex() +
                ", birthDate=" + getBirthDate() +
                '}';
    }
}