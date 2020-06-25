package com.loginregister.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Entity
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    public int id;

    @NotEmpty
    @NotNull (message = "UserName Cannot be Null")
    public String userName;

    @NotEmpty
    @NotNull
    @Pattern (regexp = "^[A-Za-z]{5,}[0-9]{3,}$", message = "Minimum 8 Character And 5 Character And 3 Number Compulsory")
    public String password;

    @NotEmpty
    @NotNull
    @Email
    public String emailId;

    @NotEmpty
    @Pattern(regexp = "^[A-Z][a-z]{3,}$")
    public String address;

    public LocalDateTime registerDate;

    public User() {
    }

    public User(String userName, String password, String emailId, String address) {
        this.userName = userName;
        this.password = password;
        this.emailId = emailId;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDateTime registerDate) {
        this.registerDate = registerDate;
    }
}
