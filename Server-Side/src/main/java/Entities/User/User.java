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
    private ProfilePicture profilePicture;

    public User(User user) {
        this.ID = user.ID;
        this.username = user.username;
        this.password = user.password;
        this.birthdate = user.birthdate;
        this.email = user.email;
        this.defaultVisibility = user.defaultVisibility;
        this.profilePicture = new ProfilePicture();
    }

    public enum Visibility {
        PRIVATE, FRIENDS, PUBLIC
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
        this.profilePicture = new ProfilePicture();
    }

    @Override
    public String toString() {
        return "User{" +
                "ID=" + ID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthdate=" + birthdate +
                ", email=" + email +
                ", defaultVisibility=" + defaultVisibility +
                ", profilePicture=" + profilePicture +
                '}';
    }

    @Override
    public boolean equals(Object other) {
        return other.getClass() == this.getClass() && this.getID() == ((User) other).getID();
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

    public void setEmail(Email email) {
        this.email = email;
    }

    public ProfilePicture getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(ProfilePicture profilePicture) {
        this.profilePicture = profilePicture;
    }
}