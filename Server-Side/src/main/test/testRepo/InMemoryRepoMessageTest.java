package main.test.testRepo;

import Entities.Message.MessageDecorator.BasicMessageDecorator;
import Entities.Message.MessageDecorator.MessageDecorator;
import Entities.Message.MessageFactory;
import Persistence.InMemoryRepositories.InMemoryMessageRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryRepoMessageTest {
    private InMemoryMessageRepository inMemoryMessageRepository;
    private MessageFactory testMessage1;
    private MessageFactory testMessage2;

    @BeforeEach
    public void setUp() {
        inMemoryMessageRepository = InMemoryMessageRepository.getInstance();

        // Create some test messages
        testMessage1 = MessageFactory.createMessage(
                MessageFactory.MessageType.TEXT, "Test message 1", null, null);
        testMessage2 = MessageFactory.createMessage(
                MessageFactory.MessageType.TEXT, "Test message 2", null, null);
    }

    @AfterEach
    public void tearDown() {
        // Clear the messages in the repository after each test
        inMemoryMessageRepository.getMessages().clear();
    }

    @Test
    public void testAddMessage() {
        // Decorate the messages
        MessageDecorator decoratedMessage1 = new BasicMessageDecorator(testMessage1);
        MessageDecorator decoratedMessage2 = new BasicMessageDecorator(testMessage2);

        inMemoryMessageRepository.addMessage(decoratedMessage1);
        Set<MessageDecorator> messages = inMemoryMessageRepository.getMessages();

        assertTrue(messages.contains(decoratedMessage1));
        assertFalse(messages.contains(decoratedMessage2));
        assertEquals(1, messages.size());
    }

    @Test
    public void testRemoveMessage() {
        // Decorate the messages
        MessageDecorator decoratedMessage1 = new BasicMessageDecorator(testMessage1);
        MessageDecorator decoratedMessage2 = new BasicMessageDecorator(testMessage2);

        inMemoryMessageRepository.addMessage(decoratedMessage1);
        inMemoryMessageRepository.addMessage(decoratedMessage2);

        inMemoryMessageRepository.removeMessage(decoratedMessage1);
        Set<MessageDecorator> messages = inMemoryMessageRepository.getMessages();

        assertFalse(messages.contains(decoratedMessage1));
        assertTrue(messages.contains(decoratedMessage2));
        assertEquals(1, messages.size());
    }

    @Test
    public void testGetMessages() {
        // Decorate the messages
        MessageDecorator decoratedMessage1 = new BasicMessageDecorator(testMessage1);
        MessageDecorator decoratedMessage2 = new BasicMessageDecorator(testMessage2);

        inMemoryMessageRepository.addMessage(decoratedMessage1);
        inMemoryMessageRepository.addMessage(decoratedMessage2);

        Set<MessageDecorator> messages = inMemoryMessageRepository.getMessages();

        assertTrue(messages.contains(decoratedMessage1));
        assertTrue(messages.contains(decoratedMessage2));
        assertEquals(2, messages.size());
    }
}
