package test.testRepo;

import Entities.Exceptions.DataBaseException;
import Entities.User.User;
import Persistence.InMemoryRepositories.InMemoryUserInMemoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class InMemoryRepoUserTest {
    private InMemoryUserInMemoryRepository userRepository;
    private User testUser1;
    private User testUser2;

    @BeforeEach
    public void setUp() {
        userRepository = InMemoryUserInMemoryRepository.getInstance();
        userRepository.getEntities().clear(); // Reset the repository before each test

        // Create some test users
        testUser1 = new User("user1", "password1", null, "user1@example.com", User.Visibility.PUBLIC);
        testUser2 = new User("user2", "password2", null, "user2@example.com", User.Visibility.PUBLIC);

        userRepository.add(testUser1);
        userRepository.add(testUser2);
    }

    @Test
    public void testFindById() {
        User foundUser = userRepository.findById(testUser1.getUserID());
        assertEquals(testUser1, foundUser);
    }

    @Test
    public void testFindByUsername() {
        User foundUser = userRepository.findByUsername("user2");
        assertEquals(testUser2, foundUser);
    }

    @Test
    public void testAddUser() throws DataBaseException {
        User newUser = new User("user3", "password3", null, "user3@example.com", User.Visibility.PUBLIC);
        userRepository.add(newUser);

        User foundUser = userRepository.findById(newUser.getUserID());
        assertEquals(newUser, foundUser);
    }

    @Test
    public void testRemoveUser() throws DataBaseException {
        userRepository.remove(testUser1);

        User foundUser = userRepository.findById(testUser1.getUserID());
        assertNull(foundUser);
    }

    @Test
    public void testUpdateUser() throws DataBaseException {
        User updatedUser1 = new User(testUser1);
        updatedUser1.setUsername("newUsername");

        userRepository.update(testUser1, updatedUser1);

        User foundUser = userRepository.findById(testUser1.getUserID());
        assertEquals(updatedUser1, foundUser);
    }

    @Test
    public void testRemoveUserById() throws DataBaseException {
        long userId = testUser1.getUserID();
        User removedUser = userRepository.removeUserById(userId);

        assertEquals(testUser1, removedUser);
        assertNull(userRepository.findById(userId));
    }
}
