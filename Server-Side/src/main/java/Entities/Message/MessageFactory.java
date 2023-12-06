package Entities.Message;

import Entities.Message.MessageDecorator.BasicMessageDecorator;
import Entities.Message.MessageDecorator.MessageDecorator;
import Entities.Message.MessageTypes.AudioMessage;
import Entities.Message.MessageTypes.ImageMessage;
import Entities.Message.MessageTypes.TextMessage;
import Entities.Message.MessageTypes.VideoMessage;
import Entities.Misc.IDGenerator;
import Entities.User.User;
import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "message")
public class MessageFactory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageID")
    protected long ID;
    @Column(name = "description")
    protected String description;
    @ManyToOne
    @JoinColumn(name = "senderID", referencedColumnName = "userID")
    protected User senderID;

    @ManyToOne
    @JoinColumn(name = "receiverID", referencedColumnName = "userID")
    protected User receiverID;

    @Column(name = "message_content")
    protected String messageData;

    public MessageFactory(String description, User senderID, User receiverID) {
        this.ID = IDGenerator.generateID(MessageFactory.class);
        this.description = description;
        this.senderID = senderID;
        this.receiverID = receiverID;
    }

    public MessageFactory(String description, User senderID, ArrayList<User> receiverID) {
        this.ID = IDGenerator.generateID(MessageFactory.class);
        this.description = description;
        this.senderID = senderID;
        this.receiverID = receiverID.getFirst();
    }

    public MessageFactory() {
        this.ID = IDGenerator.generateID(MessageFactory.class);
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

    private static MessageFactory useMessageFactory(MessageType messageType, String description, User sender, ArrayList<User> receiver) {
        return switch (messageType) {
            case TEXT -> new TextMessage(description, sender, receiver);
            case IMAGE -> new ImageMessage(description, sender, receiver);
            case AUDIO -> new AudioMessage(description, sender, receiver);
            case VIDEO -> new VideoMessage(description, sender, receiver);
        };
    }




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

    public MessageType getType() {
        return switch (this) {
            case TextMessage textMessage -> MessageType.TEXT;
            case ImageMessage imageMessage -> MessageType.IMAGE;
            case AudioMessage audioMessage -> MessageType.AUDIO;
            case VideoMessage videoMessage -> MessageType.VIDEO;
            default -> null;
        };
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getSenderID() {
        return senderID;
    }

    public void setSenderID(User sender) {
        this.senderID = sender;
    }

    public User getReceiverID() {
        return this.receiverID;
    }

    public void setReceiverID(User receiver) {
        this.receiverID = receiver;
    }

    public void setReceiver(ArrayList<User> receivers) {
        this.receiverID = receivers.getFirst();
    }

    public ArrayList<User> getReceiverList() {
        return new ArrayList<>((Collection) this.receiverID);
    }

    public long getID() {
        return this.ID;
    }

    public MessageDecorator getDecoratedMessage() {
        return new BasicMessageDecorator(this);
    }

    public enum MessageType {
        TEXT, IMAGE, AUDIO, VIDEO
    }

    public String getSenderName() {
        return senderID.getUsername();
    }

    public String getReceiverName() {
        return receiverID.getUsername();
    }



}