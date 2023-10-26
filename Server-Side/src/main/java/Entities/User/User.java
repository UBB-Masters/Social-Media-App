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

    public User(String username, String password, Date birthdate, String email) {
        this.ID = IDGenerator.generateID(User.class);
        this.username = username;
        this.password = password;
        this.birthdate = birthdate;
        try {
            this.email = new Email(email);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
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