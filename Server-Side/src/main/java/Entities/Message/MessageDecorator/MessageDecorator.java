package Entities.Message.MessageDecorator;

import Entities.Message.MessageFactory;
import Entities.User.User;

public interface MessageDecorator {

    String getDescription();
    User getSender();
    User getReceiver();

}
