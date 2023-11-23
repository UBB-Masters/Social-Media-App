package Entities.User;


import jakarta.persistence.*;

@Entity
@Table(name = "profilepicture")
public class ProfilePicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pictureID;
    @OneToOne
    @JoinColumn(name = "userID")
    private User user;
    @Column(name = "picture")
    private String picture;

    public ProfilePicture() {
        this.picture = "default";
        this.user = new User();
    }

    public ProfilePicture(String pictureFileName, User user) {
        this.picture = pictureFileName;
        this.user = user;
    }

    public ProfilePicture(long ID, String pictureFileName, User user) {
        this.pictureID = ID;
        this.picture = pictureFileName;
        this.user = user;
    }

    public long getPictureID() {
        return pictureID;
    }

    public void setPictureID(long pictureID) {
        this.pictureID = pictureID;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}