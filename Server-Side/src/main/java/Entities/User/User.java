package Entities.User;

import Entities.Events.Events;
import Entities.Misc.Email;
import Entities.Misc.IDGenerator;
import Entities.Post.Post;
import Observer.Observable;
import Observer.Observer;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "User")
public class User implements Observer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private String username;
    private String password;
    private Date birthdate;
    private String email;
    private Visibility defaultVisibility;

    @Enumerated(EnumType.STRING)
    private Permission permission;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;


    public User(User user) {
        this.ID = user.ID;
        this.username = user.username;
        this.password = user.password;
        this.birthdate = user.birthdate;
        this.email = user.email;
        this.defaultVisibility = user.defaultVisibility;
        this.permission = Permission.USER;
        this.userStatus = UserStatus.ACTIVE;
    }

    public User(String username, String password, Date birthdate, String email, Visibility defaultVisibility) {
        this.ID = IDGenerator.generateID(User.class);
        this.username = username;
        this.password = password;
        this.birthdate = birthdate;
        this.email = email;
        this.defaultVisibility = defaultVisibility;
        this.permission = Permission.USER;
        this.userStatus = UserStatus.ACTIVE;
    }

    public User() {
        this.permission = Permission.USER;
        this.userStatus = UserStatus.ACTIVE;
    }

    @Override
    public void update(Observable observable) {
        if (observable instanceof Events) {
            Events event = (Events) observable;
            System.out.println("User " + this.username + " has been notified of the event " + event.getEventName());
        } else if (observable instanceof Post) {
            Post post = (Post) observable;
            System.out.println("User " + this.username + " has been notified of the post " + post.getContent());
        }
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

    public void setID(Long id) {
        this.ID = id;
    }

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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEmail(Email email) {
        this.email = email.getAddress();
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

    public enum Visibility {
        PRIVATE, PUBLIC
    }

    public enum Permission {
        USER, ADMIN
    }

    public enum UserStatus {
        ACTIVE, BANNED
    }
}