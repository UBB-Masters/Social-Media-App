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

    private Visibility defaultVisibility;

    public enum Visibility {
        PRIVATE, FRIENDS, PUBLIC
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public User(String username, String password, Date birthdate, String email, Visibility defaultVisibility) {
        this.ID = IDGenerator.generateID(User.class);
        this.username = username;
        this.password = password;
        this.birthdate = birthdate;
        try {
            this.email = new Email(email);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
        this.defaultVisibility = defaultVisibility;
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthdate=" + birthdate +
                ", email=" + email.getAddress() +
                '}';
    }

    public Visibility getDefaultVisibility() {
        return defaultVisibility;
    }

    public void setDefaultVisibility(Visibility defaultVisibility) {
        this.defaultVisibility = defaultVisibility;
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

    public String getEmail() {
        return email.getAddress();
    }

    public void setEmail(String email) {
        try {
            this.email = new Email(email);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}