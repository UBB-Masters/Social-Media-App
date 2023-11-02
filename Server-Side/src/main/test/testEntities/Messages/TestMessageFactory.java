package main.test.testEntities.Messages;

import Entities.Exceptions.MessageException;
import Entities.Message.MessageFactory;
import Entities.Message.MessageTypes.AudioMessage;
import Entities.Message.MessageTypes.ImageMessage;
import Entities.Message.MessageTypes.TextMessage;
import Entities.Message.MessageTypes.VideoMessage;
import Entities.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestMessageFactory {
    private User sender;
    private User receiver;

    @BeforeEach
    public void setUp() {
        sender = new User("senderUsername", "senderPassword", new Date(), "sender@example.com", User.Visibility.PUBLIC);
        receiver = new User("receiverUsername", "receiverPassword", new Date(), "receiver@example.com", User.Visibility.PUBLIC);
    }

    @Test
    public void testCreateTextMessage() throws MessageException {
        MessageFactory message = MessageFactory.createMessage(MessageFactory.MessageType.TEXT, "Hello, World!", sender, receiver);
        assertEquals("Hello, World!", message.getDescription());
        assertEquals(sender, message.getSender());
        assertEquals(receiver, message.getReceiver());
        assertTrue(message instanceof TextMessage);
    }

    @Test
    public void testCreateImageMessage() throws MessageException {
        MessageFactory message = MessageFactory.createMessage(MessageFactory.MessageType.IMAGE, "image.jpg", sender, receiver);
        assertEquals("image.jpg", message.getDescription());
        assertEquals(sender, message.getSender());
        assertEquals(receiver, message.getReceiver());
        assertTrue(message instanceof ImageMessage);
    }

    @Test
    public void testCreateAudioMessage() throws MessageException {
        MessageFactory message = MessageFactory.createMessage(MessageFactory.MessageType.AUDIO, "audio.mp3", sender, receiver);
        assertEquals("audio.mp3", message.getDescription());
        assertEquals(sender, message.getSender());
        assertEquals(receiver, message.getReceiver());
        assertTrue(message instanceof AudioMessage);
    }

    @Test
    public void testCreateVideoMessage() throws MessageException {
        MessageFactory message = MessageFactory.createMessage(MessageFactory.MessageType.VIDEO, "video.mp4", sender, receiver);
        assertEquals("video.mp4", message.getDescription());
        assertEquals(sender, message.getSender());
        assertEquals(receiver, message.getReceiver());
        assertTrue(message instanceof VideoMessage);
    }

    @Test
    public void testGettersAndSetters() throws MessageException {
        MessageFactory message = MessageFactory.createMessage(MessageFactory.MessageType.TEXT, "Hello, World!", sender, receiver);

        message.setDescription("New content");
        assertEquals("New content", message.getDescription());

        User newSender = new User("newSender", "newSenderPassword", new Date(), "newSender@example.com", User.Visibility.PUBLIC);
        message.setSender(newSender);
        assertEquals(newSender, message.getSender());

        User newReceiver = new User("newReceiver", "newReceiverPassword", new Date(), "newReceiver@example.com", User.Visibility.PUBLIC);
        message.setReceiver(newReceiver);
        assertEquals(newReceiver, message.getReceiver());
    }
}
