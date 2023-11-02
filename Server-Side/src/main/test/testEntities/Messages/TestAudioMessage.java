package main.test.testEntities.Messages;

import Entities.Message.MessageTypes.AudioMessage;
import Entities.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAudioMessage {
    private AudioMessage audioMessage;
    private User sender;
    private User receiver;

    @BeforeEach
    public void setUp() {
        sender = new User("senderUser", "senderPassword", null, "sender@example.com", null);
        receiver = new User("receiverUser", "receiverPassword", null, "receiver@example.com", null);
        audioMessage = new AudioMessage("Test audio message", sender, receiver, "audio-file.mp3");
    }

    @Test
    public void testGetAudioFilePath() {
        assertEquals("audio-file.mp3", audioMessage.getAudioFilePath());
    }

    @Test
    public void testSetAudioFilePath() {
        audioMessage.setAudioFilePath("updated-audio-file.mp3");
        assertEquals("updated-audio-file.mp3", audioMessage.getAudioFilePath());
    }
}
