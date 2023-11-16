package Entities.User;

import javax.persistence.*;

@Entity
@Table
public class ProfilePicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pictureID;
    @OneToOne(mappedBy = "user_id")
    private User user;
    private String picture;

    public ProfilePicture() {
        this.picture = "default";
        this.user = new User();
    }

    public ProfilePicture(String pictureFileName, User user) {
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