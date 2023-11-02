package Entities.Message.MessageTypes;

import Entities.Message.MessageFactory;
import Entities.User.User;

import java.util.ArrayList;

public class TextMessage extends MessageFactory {
    private String content;

    public TextMessage(String description, User sender, ArrayList<User> receiver) {
        super(description, sender, receiver);
    }

    public TextMessage(String description, User sender, ArrayList<User> receiver, String content) {
        super(description, sender, receiver);
        this.content = content;
    }

    public TextMessage(String description, User sender, User receiver, String content) {
        super(description, sender, receiver);
        this.content = content;
    }
    public TextMessage(String description, User sender, User receiver) {
        super(description, sender, receiver);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "TextMessage{" +
                "ID=" + ID +
                ", description='" + description + '\'' +
                ", sender=" + sender +
                ", receiver=" + receiver +
                ", content='" + content + '\'' +
                '}';
    }
}