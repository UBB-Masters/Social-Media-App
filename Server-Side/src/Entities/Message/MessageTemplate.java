package Entities.Message;

import Entities.Exceptions.InvalidMessageException;
import Entities.Message.MessageTypes.AudioMessage;
import Entities.Message.MessageTypes.ImageMessage;
import Entities.Message.MessageTypes.TextMessage;
import Entities.Message.MessageTypes.VideoMessage;
import Entities.User;

public abstract class MessageTemplate {
    public String content;
    public User sender;
    public User receiver;

    public MessageTemplate(String content, User sender, User receiver) {
        this.content = content;
        this.sender = sender;
        this.receiver = receiver;
    }

    public enum MessageType{
        TEXT, IMAGE, AUDIO, VIDEO
    }

    // Factory Method to create specific message types based on the MessageType enum
    public static MessageTemplate createMessage(MessageType messageType, String content, User sender, User receiver) throws InvalidMessageException {
        return switch (messageType) {
            case TEXT -> new TextMessage(content, sender, receiver);
            case IMAGE -> new ImageMessage(content, sender, receiver);
            case AUDIO -> new AudioMessage(content, sender, receiver);
            case VIDEO -> new VideoMessage(content, sender, receiver);
            default -> throw new InvalidMessageException(); // Return null for unknown message types
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

    public abstract String convertContentToText();
}