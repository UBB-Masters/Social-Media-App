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
    protected String content;
    protected User sender;
    protected User receiver;

    public MessageFactory(String content, User sender, User receiver) {
        this.ID = IDGenerator.generateID(MessageFactory.class);
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
    }

    public enum MessageType {
        TEXT, IMAGE, AUDIO, VIDEO
    }

    // Factory Method to create specific message types based on the MessageType enum
    public static MessageFactory createMessage(MessageType messageType, String content, User sender, User receiver) {
        return switch (messageType) {
            case TEXT -> new TextMessage(content, sender, receiver);
            case IMAGE -> new ImageMessage(content, sender, receiver);
            case AUDIO -> new AudioMessage(content, sender, receiver);
            case VIDEO -> new VideoMessage(content, sender, receiver);
        };
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    @Override
    public String toString() {
        return "MessageFactory{" +
                "ID=" + ID +
                ", content='" + content + '\'' +
                ", sender=" + sender.toString() +
                ", receiver=" + receiver.toString() +
                '}';
    }
}