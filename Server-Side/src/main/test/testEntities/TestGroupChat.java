package main.test.testEntities;

import Entities.Channel.GroupChat;
import Entities.Misc.IDGenerator;
import Entities.User.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGroupChat {
    private GroupChat groupChat;
    private User user1;
    private User user2;

    @BeforeEach
    public void setUp() {
        IDGenerator.resetCounters();
        groupChat = new GroupChat("Test Group");
        user1 = new User("user1", "password1", null, "user1@example.com", null);
        user2 = new User("user2", "password2", null, "user2@example.com", null);
    }

    @Test
    public void testGetGroupName() {
        setUp();
        assertEquals("Test Group", groupChat.getGroupName());
    }

    @Test
    public void testSetGroupName() {
        setUp();
        groupChat.setGroupName("Updated Group");
        assertEquals("Updated Group", groupChat.getGroupName());
    }
}
