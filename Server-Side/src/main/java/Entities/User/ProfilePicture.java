package Entities.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProfilePicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pictureID;

    private String picture;

    public ProfilePicture() {
        this.picture = "default";
    }

    public ProfilePicture(String pictureFileName) {
        this.picture = pictureFileName;
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