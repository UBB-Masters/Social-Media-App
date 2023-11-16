package Entities.Message.MessageTypes;

import Entities.Message.MessageFactory;
import Entities.User.User;

import javax.imageio.ImageIO;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Entity
@Table
public class ImageMessage extends MessageFactory {
    public ImageMessage(String description, User sender, ArrayList<User> receiver) {
        super(description, sender, receiver);
    }

    public ImageMessage(String description, User sender, ArrayList<User> receiver, String messageData) {
        super(description, sender, receiver);
        this.messageData = messageData;
    }

    public ImageMessage(String description, User sender, User receiver) {
        super(description, sender, receiver);
        this.messageData = "default";
    }

    public ImageMessage(String description, User sender, User receiver, String messageData) {
        super(description, sender, receiver);
        this.messageData = messageData;
    }

    public ImageMessage() {
        super();
    }

    public static BufferedImage loadImageFromFile(String filePath) throws IOException {
        try {
            return ImageIO.read(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException(e);
        }
    }

    public String getMessageData() {
        return messageData;
    }

    public void setMessageData(String messageData) {
        this.messageData = messageData;
    }

    public String getPictureFilePath(String baseDirectory) {
        return baseDirectory + "/" + messageData;
    }

    @Override
    public String toString() {
        return "ImageMessage{" +
                "ID=" + ID +
                ", description='" + description + '\'' +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", pictureFileName='" + messageData + '\'' +
                '}';
    }
}
