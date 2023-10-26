package Entities.Message.MessageTypes;

import Entities.Message.MessageFactory;
import Entities.User.User;

public class TextMessage extends MessageFactory {
    public TextMessage(String content, User sender, User receiver) {
        super(content, sender, receiver);
    }

    @Override
    public String convertContentToText() {
        return null;
    }
}
