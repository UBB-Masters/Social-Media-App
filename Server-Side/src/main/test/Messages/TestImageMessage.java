package main.test.Messages;

import Entities.Message.MessageTypes.ImageMessage;
import Entities.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestImageMessage {
    private ImageMessage imageMessage;
    private User sender;
    private User receiver;

    @BeforeEach
    public void setUp() {
        sender = new User("senderUser", "senderPassword", null, "sender@example.com", null);
        receiver = new User("receiverUser", "receiverPassword", null, "receiver@example.com", null);
        imageMessage = new ImageMessage("Test image message", sender, receiver, "image-file.jpg");
    }

    @Test
    public void testGetPictureFileName() {
        assertEquals("image-file.jpg", imageMessage.getMessageData());
    }

    @Test
    public void testSetPictureFileName() {
        imageMessage.setMessageData("updated-image-file.jpg");
        assertEquals("updated-image-file.jpg", imageMessage.getMessageData());
    }
}
