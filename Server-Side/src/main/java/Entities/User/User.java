package Entities.User;

import Entities.Misc.Email;

import java.util.Date;

public class User {
    private String username;
    private String password;
    private Date birthdate;
    private Email email;

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(int id, String username, String password, Date birthdate, Email email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.birthdate = birthdate;
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Email getEmail() {
        return email;
    }

    public void setEmail(Email email) {
        this.email = email;
    }
}