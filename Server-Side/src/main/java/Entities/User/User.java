package Entities.User;

import Entities.Misc.Email;
import Entities.Misc.IDGenerator;

import java.util.Date;

public class User {
    private final long ID;
    private String username;
    private String password;
    private Date birthdate;
    private Email email;

    public User(String username, String password, Date birthdate, Email email) {
        this.ID = IDGenerator.generateID(User.class);
        this.username = username;
        this.password = password;
        this.birthdate = birthdate;
        this.email = email;
    }

    public long getID() {
        return ID;
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