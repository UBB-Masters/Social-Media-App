package Entities.Message.MessageTypes;

import Entities.Message.MessageFactory;
import Entities.User.User;

public class VideoMessage extends MessageFactory {
    public VideoMessage(String content, User sender, User receiver) {
        super(content, sender, receiver);
    }
}
