package Entities.User;

import java.io.Serializable;

public class ProfilePicturePK implements Serializable {

    private long pictureID;
    private Long userID;

    public ProfilePicturePK(long pictureID, Long userID) {
        this.pictureID = pictureID;
        this.userID = userID;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public long getPictureID() {
        return pictureID;
    }

    public void setPictureID(long pictureID) {
        this.pictureID = pictureID;
    }

    public Long getUserID() {
        return userID;
    }

    public void setUserID(Long userID) {
        this.userID = userID;
    }
}
