package Entities.User;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ProfilePicture {
    private String picture;

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public ProfilePicture() {
        this.picture = "default";
    }

    public ProfilePicture(String pictureFileName) {
        this.picture = pictureFileName;
    }

    public String getPicture() {
        return picture;
    }

    public String getPictureFilePath(String baseDirectory) {
        return baseDirectory + "/" + picture;
    }

    public static BufferedImage loadImageFromFile(String filePath) throws IOException {
        try {
            return ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
    }
}