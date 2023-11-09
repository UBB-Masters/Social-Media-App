package Entities.Message.TagDecorator;

import Entities.Message.MessageDecorator.MessageDecorator;
import Entities.User.User;

public class TagDecorator implements MessageDecorator {

    private MessageDecorator decoratedMessage;
    private String tag;
    public TagDecorator(MessageDecorator decoratedMessage, String tag) {
        this.decoratedMessage = decoratedMessage;
        this.tag = tag;
    }

    @Override
    public String getDescription() {
        return decoratedMessage.getDescription() + " Tag: " + tag;
    }

    @Override
    public User getSender() {
        return decoratedMessage.getSender();
    }

    @Override
    public User getReceiver() {
        return decoratedMessage.getReceiver();
    }
}
