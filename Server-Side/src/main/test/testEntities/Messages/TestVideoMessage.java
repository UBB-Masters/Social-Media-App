package main.test.testEntities.Messages;

import Entities.Message.MessageTypes.VideoMessage;
import Entities.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestVideoMessage {
    private VideoMessage videoMessage;

    @BeforeEach
    public void setUp() {
        User sender = new User("senderUser", "senderPassword", null, "sender@example.com", null);
        User receiver = new User("receiverUser", "receiverPassword", null, "receiver@example.com", null);
        videoMessage = new VideoMessage("Test video message", sender, receiver, "videoFile.mp4");
    }

    @Test
    public void testGetVideoFilePath() {
        assertEquals("videoFile.mp4", videoMessage.getVideoFilePath());
    }

    @Test
    public void testSetVideoFilePath() {
        videoMessage.setVideoFilePath("newVideoFile.mp4");
        assertEquals("newVideoFile.mp4", videoMessage.getVideoFilePath());
    }
}
