package Entities.User;

import Entities.Events.Events;
import Entities.Misc.Email;
import Entities.Misc.IDGenerator;
import Entities.Post.Post;
import Observer.Observable;
import Observer.Observer;
import Utils.PasswordHasher;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "User")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public class User implements Observer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userID")
    private Long userID;
    @Column(name = "username")
    private String username;
    @Column(name = "password_hash")
    private String password;
    @Column(name = "birthdate")
    private Date birthdate;
    @Column(name = "email")
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(name = "visibility")
    private Visibility defaultVisibility;
    @Column(name = "permission")
    @Enumerated(EnumType.STRING)
    private Permission permission;
    @Column(name = "user_status")
    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    @Column(name = "hashed_password")
    private String hashedPassword;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProfilePicture profilePicture;

    public User(User user) {
        this.userID = user.userID;
        this.username = user.username;
        this.password = user.password;
        this.birthdate = user.birthdate;
        this.email = user.email;
        this.defaultVisibility = user.defaultVisibility;
        this.permission = Permission.USER;
        this.userStatus = UserStatus.ACTIVE;
        this.posts = user.posts;
    }

    public User(String username, String password, Date birthdate, String email, Visibility defaultVisibility, List<Post> posts) {
        this.userID = IDGenerator.generateID(User.class);
        this.username = username;
        this.password = password;
        this.birthdate = birthdate;
        this.email = email;
        this.defaultVisibility = defaultVisibility;
        this.permission = Permission.USER;
        this.userStatus = UserStatus.ACTIVE;
        this.posts = posts;
    }

    public User() {
        this.permission = Permission.USER;
        this.userStatus = UserStatus.ACTIVE;
    }

    public User(String username, String password, Date birthdate, String email, Visibility defaultVisibility) {
        this.userID = IDGenerator.generateID(User.class);
        this.username = username;
        this.password = password;
        this.birthdate = birthdate;
        this.email = email;
        this.defaultVisibility = defaultVisibility;
        this.permission = Permission.USER;
        this.userStatus = UserStatus.ACTIVE;
        this.posts = new ArrayList<Post>();
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
                "ID=" + userID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", birthdate=" + birthdate +
                ", email=" + email +
                ", defaultVisibility=" + defaultVisibility +
                '}';
    }

    @Override
    public boolean equals(Object other) {
        return other.getClass() == this.getClass() && this.getUserID() == ((User) other).getUserID();
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setPasswordHashed(String password) {
        this.hashedPassword = PasswordHasher.hashPassword(password);
    }



    public static User createWithHashedPassword(String username, String password, Date birthdate, String email, Visibility defaultVisibility) {
        String hashedPassword = PasswordHasher.hashPassword(password);
        User user = new User(username, hashedPassword, birthdate, email, defaultVisibility);
        return new HashedPasswordDecorator(user);
    }

    public static User createWithHashedPassword(String username, String password, Date birthdate, String email, Visibility defaultVisibility, List<Post> posts) {
        User user = new User(username, password, birthdate, email, defaultVisibility, posts);
        return new HashedPasswordDecorator(user);
    }

    public Visibility getDefaultVisibility() {
        return defaultVisibility;
    }

    public void setDefaultVisibility(Visibility defaultVisibility) {
        this.defaultVisibility = defaultVisibility;
    }

    //setter for visibility
    public long getUserID() {
        return userID;
    }

    public void setUserID(Long id) {
        this.userID = id;
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

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> post) {
        this.posts = post;
    }

    public enum Permission {
        USER, ADMIN
    }

    public enum UserStatus {
        ACTIVE, BANNED
    }

    public boolean authenticate(String enteredPassword) {
        String hashedEnteredPassword = PasswordHasher.hashPassword(enteredPassword);
        return hashedEnteredPassword.equals(hashedPassword);
    }

}