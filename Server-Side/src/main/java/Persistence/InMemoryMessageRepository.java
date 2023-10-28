package Persistence;

import Entities.Message.MessageFactory;

import java.util.HashSet;
import java.util.Set;

public class InMemoryMessageRepository {
    private final Set<MessageFactory> messages;

    public InMemoryMessageRepository() {
        messages = new HashSet<>();
    }

    public Set<MessageFactory> getMessages() {
        return messages;
    }

    //method to add a message to the repository
    public void addMessage(MessageFactory message) {
        messages.add(message);
    }

    public void removeMessage(MessageFactory message) {
        messages.remove(message);
    }
}
