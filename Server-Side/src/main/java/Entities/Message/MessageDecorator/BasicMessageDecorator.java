package Entities.Message.MessageDecorator;

import Entities.Message.MessageFactory;
import Entities.User.User;

public class BasicMessageDecorator implements MessageDecorator {
    private final MessageFactory message;

    public BasicMessageDecorator(MessageFactory message) {
        this.message = message;
    }

    @Override
    public String getDescription() {
        return message.getDescription();
    }

    @Override
    public User getSender() {
        return message.getSender();
    }

    @Override
    public User getReceiver() {
        return message.getReceiver();
    }

    // Method to get the wrapped MessageFactory
    public MessageFactory getDecoratedMessage() {
        return message;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BasicMessageDecorator that = (BasicMessageDecorator) obj;
        return message.equals(that.message);
    }

    @Override
    public int hashCode() {
        return message.hashCode();
    }
}
