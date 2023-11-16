package Entities.Message.MessageTypes;

import Entities.Message.MessageFactory;
import Entities.User.User;

import java.util.ArrayList;

public class VideoMessage extends MessageFactory {

    private String videoFilePath;

    public VideoMessage(String description, User sender, ArrayList<User> receiver) {
        super(description, sender, receiver);
    }

    public VideoMessage(String description, User sender, ArrayList<User> receiver, String videoFilePath) {
        super(description, sender, receiver);
        this.videoFilePath = videoFilePath;
    }

    public VideoMessage(String description, User sender, User receiver, String videoFilePath) {
        super(description, sender, receiver);
        this.videoFilePath = videoFilePath;
    }

    public VideoMessage(String description, User sender, User receiver) {
        super(description, sender, receiver);
    }


    public String getVideoFilePath() {
        return videoFilePath;
    }

    public void setVideoFilePath(String videoFilePath) {
        this.videoFilePath = videoFilePath;
    }

    @Override
    public String toString() {
        return "VideoMessage{" +
                "ID=" + ID +
                ", description='" + description + '\'' +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", videoFilePath='" + videoFilePath + '\'' +
                '}';
    }
}
