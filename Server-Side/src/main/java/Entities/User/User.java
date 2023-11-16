package Entities.User;

import Entities.Misc.Email;
import Entities.Misc.IDGenerator;
import Entities.Post.Post;
import Events.Events;
import Observer.Observer;
import Observer.Observable;

import java.util.Date;

public class User implements Observer {
    protected final long ID;
    protected String username;
    protected String password;
    protected Date birthdate;
    protected Email email;
    protected Visibility defaultVisibility;
    protected ProfilePicture profilePicture;
    protected Permission permission;
    protected UserStatus userStatus;


    public User(User user) {
        this.ID = user.ID;
        this.username = user.username;
        this.password = user.password;
        this.birthdate = user.birthdate;
        this.email = user.email;
        this.defaultVisibility = user.defaultVisibility;
        this.profilePicture = new ProfilePicture();
        this.permission = Permission.USER;
        this.userStatus = UserStatus.ACTIVE;
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
        this.permission = Permission.USER;
        this.userStatus = UserStatus.ACTIVE;
    }

    @Override
    public void update(Observable observable) {
        if(observable instanceof Events) {
            Events event = (Events) observable;
            System.out.println("User " + this.username + " has been notified of the event " + event.getEventName());
        } else if(observable instanceof Post) {
            Post post = (Post) observable;
            System.out.println("User " + this.username + " has been notified of the post " + post.getContent());
        }
    }



    public enum Visibility {
        PRIVATE, PUBLIC
    }

    public enum Permission {
        USER, ADMIN
    }

    public enum UserStatus {
        ACTIVE, BANNED
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

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}