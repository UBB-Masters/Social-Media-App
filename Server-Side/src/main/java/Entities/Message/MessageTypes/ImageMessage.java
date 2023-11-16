package Entities.Message.MessageTypes;

import Entities.Message.MessageFactory;
import Entities.User.User;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ImageMessage extends MessageFactory {
    private String pictureFileName;

    public ImageMessage(String description, User sender, ArrayList<User> receiver) {
        super(description, sender, receiver);
    }

    public ImageMessage(String description, User sender, ArrayList<User> receiver, String pictureFileName) {
        super(description, sender, receiver);
        this.pictureFileName = pictureFileName;
    }

    public ImageMessage(String description, User sender, User receiver) {
        super(description, sender, receiver);
        this.pictureFileName = "default";
    }

    public ImageMessage(String description, User sender, User receiver, String pictureFileName) {
        super(description, sender, receiver);
        this.pictureFileName = pictureFileName;
    }

    public static BufferedImage loadImageFromFile(String filePath) throws IOException {
        try {
            return ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
    }

    public String getPictureFileName() {
        return pictureFileName;
    }

    public void setPictureFileName(String pictureFileName) {
        this.pictureFileName = pictureFileName;
    }

    public String getPictureFilePath(String baseDirectory) {
        return baseDirectory + "/" + pictureFileName;
    }

    @Override
    public String toString() {
        return "ImageMessage{" +
                "ID=" + ID +
                ", description='" + description + '\'' +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", pictureFileName='" + pictureFileName + '\'' +
                '}';
    }
}
