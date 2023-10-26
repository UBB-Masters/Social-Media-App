package Entities.Message.MessageTypes;

import Entities.Message.MessageTemplate;
import Entities.User.User;

public class TextMessage extends MessageTemplate {
    public TextMessage(String content, User sender, User receiver) {
        super(content, sender, receiver);
    }

    @Override
    public String convertContentToText() {
        return null;
    }
}
