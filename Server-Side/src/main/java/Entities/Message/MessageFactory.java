package Entities.Message;

import Entities.Message.MessageDecorator.BasicMessageDecorator;
import Entities.Message.MessageDecorator.MessageDecorator;
import Entities.Message.MessageTypes.AudioMessage;
import Entities.Message.MessageTypes.ImageMessage;
import Entities.Message.MessageTypes.TextMessage;
import Entities.Message.MessageTypes.VideoMessage;
import Entities.Misc.IDGenerator;
import Entities.User.User;

import java.util.ArrayList;

public class MessageFactory {

    protected final long ID;
    protected String description;
    protected User sender;
    protected ArrayList<User> receiver;

    public MessageFactory(String description, User sender, User receiver) {
        this.ID = IDGenerator.generateID(MessageFactory.class);
        this.description = description;
        this.sender = sender;
        this.receiver = new ArrayList<>();
        this.receiver.add(receiver);
    }

    public MessageFactory(String description, User sender, ArrayList<User> receiver) {
        this.ID = IDGenerator.generateID(MessageFactory.class);
        this.description = description;
        this.sender = sender;
        this.receiver = receiver;
    }





    public static MessageDecorator createDecoratedMessage(
            MessageType messageType, String description, User sender, User receiver) {
        ArrayList<User> temporaryUserArray = new ArrayList<>();
        temporaryUserArray.add(receiver);
        MessageFactory message = useMessageFactory(messageType, description, sender, temporaryUserArray);
        return new BasicMessageDecorator(message);  // Returning the basic message wrapped in the decorator
    }


    public static MessageDecorator createDecoratedMessage(
            MessageType messageType, String description, User sender, ArrayList<User> receiver) {
        MessageFactory message = useMessageFactory(messageType, description, sender, receiver);
        return new BasicMessageDecorator(message);  // Returning the basic message wrapped in the decorator
    }


    public MessageType getType() {

        return switch (this) {
            case TextMessage textMessage -> MessageType.TEXT;
            case ImageMessage imageMessage -> MessageType.IMAGE;
            case AudioMessage audioMessage -> MessageType.AUDIO;
            case VideoMessage videoMessage -> MessageType.VIDEO;
            default -> null;
        };
    }

    public enum MessageType {
        TEXT, IMAGE, AUDIO, VIDEO
    }

    private static MessageFactory useMessageFactory(MessageType messageType, String description, User sender, ArrayList<User> receiver) {
        return switch (messageType) {
            case TEXT -> new TextMessage(description, sender, receiver);
            case IMAGE -> new ImageMessage(description, sender, receiver);
            case AUDIO -> new AudioMessage(description, sender, receiver);
            case VIDEO -> new VideoMessage(description, sender, receiver);
        };
    }

    // Factory Method to create specific message types based on the MessageType enum
//    public static MessageFactory createMessage(
//            MessageType messageType, String description, User sender, User receiver) {
//        ArrayList<User> temporaryUserArray = new ArrayList<>();
//        temporaryUserArray.add(receiver);
//        return useMessageFactory(messageType, description, sender, temporaryUserArray);
//    }
//
//    public static MessageFactory createMessage(
//            MessageType messageType, String description, User sender, ArrayList<User> receiver) {
//        return useMessageFactory(messageType, description, sender, receiver);
//    }



    public static MessageDecorator createDecoratedMessageWithList(
            MessageType messageType, String description, User sender, ArrayList<User> receiver) {
        MessageFactory message = useMessageFactory(messageType, description, sender, receiver);
        return new BasicMessageDecorator(message);
    }

    public static MessageFactory createMessage(
            MessageType messageType, String description, User sender, User receiver) {
        ArrayList<User> temporaryUserArray = new ArrayList<>();
        temporaryUserArray.add(receiver);
        return useMessageFactory(messageType, description, sender, temporaryUserArray);
    }

    public static MessageFactory createMessageWithList(
            MessageType messageType, String description, User sender, ArrayList<User> receiver) {
        return useMessageFactory(messageType, description, sender, receiver);
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
        return this.receiver.getFirst();
    }

    public ArrayList<User> getReceiverList() {
        return this.receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = new ArrayList<>();
        this.receiver.add(receiver);
    }

    public void setReceiver(ArrayList<User> receivers) {
        this.receiver = receivers;
    }

    public long getID() {
        return this.ID;
    }

    public MessageDecorator getDecoratedMessage() {
        return new BasicMessageDecorator(this);
    }


}