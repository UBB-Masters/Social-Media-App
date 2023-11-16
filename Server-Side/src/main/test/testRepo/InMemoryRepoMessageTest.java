package main.test.testRepo;

import Entities.Message.MessageDecorator.BasicMessageDecorator;
import Entities.Message.MessageDecorator.MessageDecorator;
import Entities.Message.MessageFactory;
import Entities.User.User;
import Persistence.InMemoryMessageRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InMemoryRepoMessageTest {
    private InMemoryMessageRepository messageRepository;
    private MessageFactory testMessage1;
    private MessageFactory testMessage2;

    @BeforeEach
    public void setUp() {
        messageRepository = InMemoryMessageRepository.getInstance();

        // Create some test messages
        testMessage1 = MessageFactory.createMessage(
                MessageFactory.MessageType.TEXT, "Test message 1", null, null);
        testMessage2 = MessageFactory.createMessage(
                MessageFactory.MessageType.TEXT, "Test message 2", null, null);
    }

    @AfterEach
    public void tearDown() {
        // Clear the messages in the repository after each test
        messageRepository.getMessages().clear();
    }

    @Test
    public void testAddMessage() {
        // Decorate the messages
        MessageDecorator decoratedMessage1 = new BasicMessageDecorator(testMessage1);
        MessageDecorator decoratedMessage2 = new BasicMessageDecorator(testMessage2);

        messageRepository.addMessage(decoratedMessage1);
        Set<MessageDecorator> messages = messageRepository.getMessages();

        assertTrue(messages.contains(decoratedMessage1));
        assertFalse(messages.contains(decoratedMessage2));
        assertEquals(1, messages.size());
    }

    @Test
    public void testRemoveMessage() {
        // Decorate the messages
        MessageDecorator decoratedMessage1 = new BasicMessageDecorator(testMessage1);
        MessageDecorator decoratedMessage2 = new BasicMessageDecorator(testMessage2);

        messageRepository.addMessage(decoratedMessage1);
        messageRepository.addMessage(decoratedMessage2);

        messageRepository.removeMessage(decoratedMessage1);
        Set<MessageDecorator> messages = messageRepository.getMessages();

        assertFalse(messages.contains(decoratedMessage1));
        assertTrue(messages.contains(decoratedMessage2));
        assertEquals(1, messages.size());
    }

    @Test
    public void testGetMessages() {
        // Decorate the messages
        MessageDecorator decoratedMessage1 = new BasicMessageDecorator(testMessage1);
        MessageDecorator decoratedMessage2 = new BasicMessageDecorator(testMessage2);

        messageRepository.addMessage(decoratedMessage1);
        messageRepository.addMessage(decoratedMessage2);

        Set<MessageDecorator> messages = messageRepository.getMessages();

        assertTrue(messages.contains(decoratedMessage1));
        assertTrue(messages.contains(decoratedMessage2));
        assertEquals(2, messages.size());
    }
}
