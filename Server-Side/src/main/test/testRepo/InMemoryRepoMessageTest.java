package main.test.testRepo;

import Entities.Message.MessageFactory;
import Persistence.InMemoryMessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InMemoryRepoMessageTest {
    private InMemoryMessageRepository messageRepository;
    private MessageFactory testMessage1;
    private MessageFactory testMessage2;

    @BeforeEach
    public void setUp() {
        messageRepository = new InMemoryMessageRepository();

        // Create some test messages
        testMessage1 = new MessageFactory("Test message 1", null, null);
        testMessage2 = new MessageFactory("Test message 2", null, null);
    }

    @Test
    public void testAddMessage() {
        messageRepository.addMessage(testMessage1);
        Set<MessageFactory> messages = messageRepository.getMessages();
        assertTrue(messages.contains(testMessage1));
        assertEquals(1, messages.size());
    }

    @Test
    public void testRemoveMessage() {
        messageRepository.addMessage(testMessage1);
        messageRepository.addMessage(testMessage2);

        messageRepository.removeMessage(testMessage1);
        Set<MessageFactory> messages = messageRepository.getMessages();

        assertFalse(messages.contains(testMessage1));
        assertTrue(messages.contains(testMessage2));
        assertEquals(1, messages.size());
    }

    @Test
    public void testGetMessages() {
        Set<MessageFactory> testMessages = new HashSet<>();
        testMessages.add(testMessage1);
        testMessages.add(testMessage2);

        messageRepository.addMessage(testMessage1);
        messageRepository.addMessage(testMessage2);

        Set<MessageFactory> messages = messageRepository.getMessages();
        assertEquals(testMessages, messages);
    }
}