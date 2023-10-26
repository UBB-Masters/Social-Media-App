package Entities.Message.MessageTypes;

import Entities.Message.MessageTemplate;
import Entities.User.User;

public class VideoMessage extends MessageTemplate {
    public VideoMessage(String content, User sender, User receiver) {
        super(content, sender, receiver);
    }

    @Override
    public String convertContentToText() {
        return null;
    }
}
