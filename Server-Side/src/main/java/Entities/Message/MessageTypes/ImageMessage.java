package Entities.Message.MessageTypes;

import Entities.Message.MessageFactory;
import Entities.User.User;

public class ImageMessage extends MessageFactory {
    public ImageMessage(String content, User sender, User receiver) {
        super(content, sender, receiver);
    }
}
