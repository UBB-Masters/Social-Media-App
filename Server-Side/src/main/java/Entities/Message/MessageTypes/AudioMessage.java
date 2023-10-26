package Entities.Message.MessageTypes;

import Entities.Message.MessageFactory;
import Entities.User.User;

public class AudioMessage extends MessageFactory {
    public AudioMessage(String content, User sender, User receiver) {
        super(content, sender, receiver);
    }

    @Override
    public String convertContentToText() {
        return null;
    }
}
