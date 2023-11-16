package main.test.testEntities.TestUsers;

import Entities.Channel.GroupChat;
import Entities.Message.MessageFactory;
import Entities.Misc.IDGenerator;
import Entities.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestGroupChat {
    private GroupChat groupChat;
    private User user1;
    private User user2;
    private MessageFactory message1;
    private MessageFactory message2;

    @BeforeEach
    public void setUp() {
        IDGenerator.resetCounters();
        groupChat = new GroupChat("Test Group");
        user1 = new User("user1", "password1", null, "user1@example.com", null);
        user2 = new User("user2", "password2", null, "user2@example.com", null);
        message1 = new MessageFactory("Test message 1", user1, new ArrayList<>());
        message2 = new MessageFactory("Test message 2", user2, new ArrayList<>());
    }

    @Test
    public void testGetGroupName() {
        assertEquals("Test Group", groupChat.getGroupName());
    }

    @Test
    public void testSetGroupName() {
        groupChat.setGroupName("Updated Group");
        assertEquals("Updated Group", groupChat.getGroupName());
    }
}
