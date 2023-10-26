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
}
