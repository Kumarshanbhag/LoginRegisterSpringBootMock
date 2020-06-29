package com.loginregister.model;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Entity
public class User {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    public int id;

    @NotEmpty
    @Pattern (regexp = "^[A-Z][a-z]{2,}$", message = "UserName Cannot be Null")
    @Column (nullable = false)
    public String userName;

    @NotEmpty
    @Pattern (regexp = "^[A-Za-z]{5,}[0-9]{3,}$", message = "Minimum 8 Character And 5 Character And 3 Number Compulsory")
    @Column (nullable = false)
    public String password;

    @NotEmpty
    @Pattern (regexp = "^[a-zA-Z0-9]{3,}[@][a-zA-Z0-9]+[.][a-zA-Z]{2,3}$", message = "Can Consists Character & number, It should have minimum 3 Character and @ company name and . domain")
    @Column (unique = true, nullable = false)
    public String emailId;

    @NotEmpty
    @Pattern (regexp = "^[A-Z][A-za-z]{3,}$", message = "Should Start With Capital Letter and Consist Of Minimum 3 character and No number Or Special Character Allowed")
    @Column (nullable = false)
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
