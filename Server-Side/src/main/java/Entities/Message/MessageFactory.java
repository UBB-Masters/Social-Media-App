package Entities.Message;

import Entities.Exceptions.MessageException;
import Entities.Message.MessageTypes.AudioMessage;
import Entities.Message.MessageTypes.ImageMessage;
import Entities.Message.MessageTypes.TextMessage;
import Entities.Message.MessageTypes.VideoMessage;
import Entities.Misc.IDGenerator;
import Entities.User.User;

public class MessageFactory {

    protected final long ID;
    protected String description;
    protected User sender;
    protected User receiver;

    public MessageFactory(String description, User sender, User receiver) {
        this.ID = IDGenerator.generateID(MessageFactory.class);
        this.description = description;
        this.sender = sender;
        this.receiver = receiver;
    }

    public enum MessageType {
        TEXT, IMAGE, AUDIO, VIDEO
    }

    // Factory Method to create specific message types based on the MessageType enum
    public static MessageFactory createMessage(MessageType messageType, String description, User sender, User receiver) {
        return switch (messageType) {
            case TEXT -> new TextMessage(description, sender, receiver);
            case IMAGE -> new ImageMessage(description, sender, receiver);
            case AUDIO -> new AudioMessage(description, sender, receiver);
            case VIDEO -> new VideoMessage(description, sender, receiver);
        };
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public long getID() {
        return this.ID;
    }
}