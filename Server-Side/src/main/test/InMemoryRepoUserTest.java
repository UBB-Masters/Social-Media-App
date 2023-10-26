package main.test;

import Entities.Exceptions.DataBaseException;


import Entities.User.User;
import Persistence.InMemoryUserRepository;

import jdk.jfr.Name;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
public class InMemoryRepoUserTest {

    private InMemoryUserRepository userRepository;
    private Set<User> users;

    @Before
    public void setUp() {
        users = new HashSet<>();
        userRepository = new InMemoryUserRepository(users);
    }

    @Test
    @Name("Test find user by id")
    public void testSaveUsers() throws DataBaseException {
        User user = new User(1, "bob", "dob", null, null);

        userRepository.add(user);

        assertEquals(1, userRepository.getEntities().size());
        User savedUser = userRepository.getEntities().iterator().next();
        assertEquals(user, savedUser);

    }

    @Test
    @Name("Test find user by id")
    public void testFindUserById() throws DataBaseException {
        User user = new User(1, "bob", "dob", null, null);
        userRepository.add(user);

        User found = userRepository.findById(user.getId());

        assertEquals(user, found);
    }

    @Test
    @Name("Test find user by username")
    public void testFindUserByUsername() throws DataBaseException {
        User user = new User(1, "bob", "dob", null, null);
        userRepository.add(user);

        User found = userRepository.findByUsername(user.getUsername());

        assertEquals(user, found);
    }

    @Test
    public void testFindUserByUsernameNotFound() {
        User foundUser = userRepository.findByUsername("non_existent_user");

        assertNull(foundUser);
    }

    @Test
    public void testDeleteUser() throws DataBaseException {
        User user = new User(1, "bob", null, null, null);
        userRepository.add(user);

        userRepository.remove(user);

        assertEquals(0, userRepository.getEntities().size());
    }

    @Test
    public void testUpdateUser() throws DataBaseException {
        User oldUser = new User(1, "bob", null, null, null);
        User newUser = new User(1, "dob", null, null, null);

        userRepository.add(oldUser);
        userRepository.update(oldUser, newUser);

        User updatedUser = userRepository.findById(oldUser.getId());

        assertEquals(newUser, updatedUser);
    }


}
