package Entities.Message.MessageTypes;

import Entities.Message.MessageTemplate;
import Entities.User.User;

public class ImageMessage extends MessageTemplate {
    public ImageMessage(String content, User sender, User receiver) {
        super(content, sender, receiver);
    }

    @Override
    public String convertContentToText() {
        return null;
    }
}
