package main.test;

import Controller.ServerController;
import Entities.Message.MessageFactory;
import Entities.Misc.IDGenerator;
import Entities.User.User;
import Persistence.InMemoryEventRepository;
import Persistence.InMemoryUserRepository;
import Persistence.InMemoryMessageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class testController {
    private ServerController serverController;
    private InMemoryUserRepository userRepository;
    private InMemoryMessageRepository messageRepository;

    private InMemoryEventRepository eventsRepository;
    private User testUser1;
    private User testUser2;

    @BeforeEach
    public void setUp() {
        IDGenerator.resetCounters();
        userRepository = new InMemoryUserRepository();
        messageRepository = new InMemoryMessageRepository();
        eventsRepository = new InMemoryEventRepository();
        serverController = new ServerController(userRepository, messageRepository, eventsRepository);

        // Create some test users
        testUser1 = new User("user1", "password1", new Date(), "user1@example.com", User.Visibility.PUBLIC);
        testUser2 = new User("user2", "password2", new Date(), "user2@example.com", User.Visibility.PUBLIC);
    }

    @Test
    public void testAddUser() {
        serverController.addUser(testUser1);
        assertEquals(1, userRepository.getEntities().size());
    }

    @Test
    public void testRemoveUser() {
        serverController.addUser(testUser1);
        serverController.removeUser(testUser1);
        assertTrue(userRepository.getEntities().isEmpty());
    }

    @Test
    public void testUpdateUserUsername() {
        serverController.addUser(testUser1);
        serverController.updateUserUsername(testUser1, "newUsername");
        User updatedUser = userRepository.findById(testUser1.getID());
        assertEquals("newUsername", updatedUser.getUsername());
    }

    @Test
    public void testSendMessage() {
        serverController.addUser(testUser1);
        serverController.addUser(testUser2);

        serverController.sendMessage(testUser1, testUser2, "Hello, testUser2!");

        List<MessageFactory> user2Messages = serverController.getUserMessages(testUser2);
        assertEquals(1, user2Messages.size());
    }

    @Test
    public void testRemoveMessage() {
        serverController.addUser(testUser1);
        serverController.addUser(testUser2);

        serverController.sendMessage(testUser1, testUser2, "Hello, testUser2!");
        List<MessageFactory> user2Messages = serverController.getUserMessages(testUser2);
        MessageFactory messageToRemove = user2Messages.get(0);

        serverController.removeMessage(messageToRemove);

        // Check if the message is removed from the repository
        Set<MessageFactory> remainingMessages = messageRepository.getMessages();
        assertFalse(remainingMessages.contains(messageToRemove));
        assertEquals(0, remainingMessages.size());
    }

    @Test
    public void testGetAllUsers() {
        serverController.addUser(testUser1);
        serverController.addUser(testUser2);

        List<User> allUsers = serverController.getAllUsers();
        assertEquals(2, allUsers.size());
    }

    @Test
    public void testRemoveUserByID() {
        serverController.addUser(testUser1);

        User removedUser = serverController.removeUserByID(testUser1.getID());
        assertEquals(testUser1, removedUser);
        assertTrue(userRepository.getEntities().isEmpty());
    }

    @Test
    public void testGetUserById() {
        serverController.addUser(testUser1);
        serverController.addUser(testUser2);

        User retrievedUser = serverController.getUserById(testUser1.getID());
        assertEquals(testUser1, retrievedUser);
    }
}
