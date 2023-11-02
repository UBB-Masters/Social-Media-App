package Entities.User;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ProfilePicture {
    private String pictureFileName;

    public void setPictureFileName(String pictureFileName) {
        this.pictureFileName = pictureFileName;
    }

    public ProfilePicture() {
        this.pictureFileName = "default";
    }

    public ProfilePicture(String pictureFileName) {
        this.pictureFileName = pictureFileName;
    }

    public String getPictureFileName() {
        return pictureFileName;
    }

    public String getPictureFilePath(String baseDirectory) {
        return baseDirectory + "/" + pictureFileName;
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