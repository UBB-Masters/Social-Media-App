package Entities.Message.MessageTypes;

import Entities.Message.MessageFactory;
import Entities.User.User;

public class AudioMessage extends MessageFactory {
    private String audioFilePath;

    public AudioMessage(String description, User sender, User receiver, String audioFilePath) {
        super(description, sender, receiver);
        this.audioFilePath = audioFilePath;
    }

    public AudioMessage(String description, User sender, User receiver) {
        super(description, sender, receiver);
    }

    public String getAudioFilePath() {
        return audioFilePath;
    }

    public void setAudioFilePath(String audioFilePath) {
        this.audioFilePath = audioFilePath;
    }

    @Override
    public String toString() {
        return "AudioMessage{" +
                "ID=" + ID +
                ", description='" + description + '\'' +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", audioFilePath='" + audioFilePath + '\'' +
                '}';
    }
}










