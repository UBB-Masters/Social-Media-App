package main.test.testEntities.TestUsers;

import Entities.Chanels.GroupChat;
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
    public void testGetGroupId() {
        assertNotNull(groupChat.getGroupId());
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

    @Test
    public void testAddMember() {
        groupChat.addMember(user1);
        groupChat.addMember(user2);
        List<User> members = groupChat.getMembers();
        assertEquals(2, members.size());
        assertTrue(members.contains(user1));
        assertTrue(members.contains(user2));
    }

    @Test
    public void testRemoveMember() {
        groupChat.addMember(user1);
        groupChat.addMember(user2);
        groupChat.removeMember(user1);
        List<User> members = groupChat.getMembers();
        assertEquals(1, members.size());
        assertFalse(members.contains(user1));
        assertTrue(members.contains(user2));
    }

    @Test
    public void testAddMessage() {
        groupChat.addMessage(message1);
        groupChat.addMessage(message2);
        List<MessageFactory> messages = groupChat.getMessages();
        assertEquals(2, messages.size());
        assertTrue(messages.contains(message1));
        assertTrue(messages.contains(message2));
    }
}
