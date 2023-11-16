package main.test.testEntities.TestUsers.Messages;

import Entities.Message.MessageTypes.TextMessage;
import Entities.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestTextMessage {
    private TextMessage textMessage;

    @BeforeEach
    public void setUp() {
        User sender = new User("senderUser", "senderPassword", null, "sender@example.com", null);
        User receiver = new User("receiverUser", "receiverPassword", null, "receiver@example.com", null);
        textMessage = new TextMessage("Test text message", sender, receiver, "Hello, this is a text message.");
    }

    @Test
    public void testGetContent() {
        assertEquals("Hello, this is a text message.", textMessage.getMessageData());
    }

    @Test
    public void testSetContent() {
        textMessage.setMessageData("Updated text message content.");
        assertEquals("Updated text message content.", textMessage.getMessageData());
    }
}
