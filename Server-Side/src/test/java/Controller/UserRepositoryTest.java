package Controller;

import Entities.User.User;
import Controller.UserRepository; // You need to create a repository interface

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testSaveUser() {
        // Given
        User user = new User("testUser", "password", new Date(), "test@example.com", User.Visibility.PUBLIC);

        // When
        User savedUser = userRepository.save(user);

        // Then
        assertEquals(user.getUsername(), savedUser.getUsername());
        assertEquals(user.getEmail(), savedUser.getEmail());
        // Add more assertions as needed
    }

    @Test
    public void testFindUserById() {
        // Given
        User user = new User("testUser", "password", new Date(), "test@example.com", User.Visibility.PUBLIC);
        User savedUser = userRepository.save(user);

        // When
        Optional<User> foundUser = userRepository.findById(savedUser.getUserID());

        // Then
        assertTrue(foundUser.isPresent());
        assertEquals(savedUser.getUsername(), foundUser.get().getUsername());
        assertEquals(savedUser.getEmail(), foundUser.get().getEmail());
    }

    @Test
    public void testUpdateUser() {
        // Given
        User user = new User("testUser", "password", new Date(), "test@example.com", User.Visibility.PUBLIC);
        User savedUser = userRepository.save(user);

        // When
        savedUser.setEmail("updated@example.com");
        User updatedUser = userRepository.save(savedUser);

        // Then
        assertEquals(savedUser.getUserID(), updatedUser.getUserID());
        assertEquals("updated@example.com", updatedUser.getEmail());
    }

    @Test
    public void testDeleteUser() {
        // Given
        User user = new User("testUser", "password", new Date(), "test@example.com", User.Visibility.PUBLIC);
        User savedUser = userRepository.save(user);

        // When
        userRepository.deleteById(savedUser.getUserID());

        // Then
        Optional<User> deletedUser = userRepository.findById(savedUser.getUserID());
        assertFalse(deletedUser.isPresent());
    }

//    @Test
//    public void testFindByUsername() {
//        // Given
//        User user = new User("testUser", "password", new Date(), "test@example.com", User.Visibility.PUBLIC);
//        userRepository.save(user);
//
//        // When
//        Optional<User> foundUser = userRepository.findByUsername("testUser");
//
//        // Then
//        assertTrue(foundUser.isPresent());
//        assertEquals(user.getUsername(), foundUser.get().getUsername());
//    }

    // Add more tests as needed

}
