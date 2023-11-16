package Entities.Message.MessageDecorator;

import Entities.User.User;

public interface MessageDecorator {

    String getDescription();

    User getSender();

    User getReceiver();

}
