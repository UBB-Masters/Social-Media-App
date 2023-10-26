package Persistence;

import Entities.Message.MessageTemplate;

import java.util.HashSet;
import java.util.Set;

public class InMemoryMessageRepository {
    private Set<MessageTemplate> messages;

    public InMemoryMessageRepository() {
        messages = new HashSet<>();
    }

    public Set<MessageTemplate> getMessages() {
        return messages;
    }
}
