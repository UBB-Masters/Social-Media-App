package Entities.Message.MessageTypes;

import Entities.Message.MessageFactory;
import Entities.User.User;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.ArrayList;

@Entity
@Table
public class TextMessage extends MessageFactory {
    public TextMessage(String description, User sender, ArrayList<User> receiver) {
        super(description, sender, receiver);
    }

    public TextMessage(String description, User sender, ArrayList<User> receiver, String messageData) {
        super(description, sender, receiver);
        this.messageData = messageData;
    }

    public TextMessage(String description, User sender, User receiver, String messageData) {
        super(description, sender, receiver);
        this.messageData = messageData;
    }

    public TextMessage(String description, User sender, User receiver) {
        super(description, sender, receiver);
    }

    public TextMessage() {
        super();
    }

    public String getMessageData() {
        return messageData;
    }

    public void setMessageData(String messageData) {
        this.messageData = messageData;
    }

    @Override
    public String toString() {
        return "TextMessage{" +
                "ID=" + ID +
                ", description='" + description + '\'' +
                ", sender=" + senderID +
                ", receiver=" + receiverID +
                ", content='" + messageData + '\'' +
                '}';
    }
}