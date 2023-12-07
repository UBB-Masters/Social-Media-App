package Controller;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import Entities.User.User;
import Entities.User.User.Visibility;
import Entities.Post.Post;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class UserRepositoryTest {

    @Test
    public void testSaveUser() {
        // Given
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        List<Post> posts = new ArrayList<>();
        User user = new User("testUser", "password", new Date(), "test@example.com", Visibility.PUBLIC, posts);

        // When
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userRepository.save(user);

        // Then
        assertEquals(savedUser.getUsername(), user.getUsername());
        assertEquals(savedUser.getEmail(), user.getEmail());
        // Add more assertions as needed
    }

    @Test
    public void testFindUserById() {
        // Given
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        List<Post> posts = new ArrayList<>();
        User user = new User("testUser", "password", new Date(), "test@example.com", Visibility.PUBLIC, posts);
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userRepository.save(user);

        // When
        when(userRepository.findById(savedUser.getUserID())).thenReturn(Optional.of(savedUser));
        Optional<User> foundUser = userRepository.findById(savedUser.getUserID());

        // Then
        assertEquals(foundUser.isPresent(), true);
        assertEquals(foundUser.get().getUsername(), savedUser.getUsername());
        assertEquals(foundUser.get().getEmail(), savedUser.getEmail());
    }

    @Test
    public void testUpdateUser() {
        // Given
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        List<Post> posts = new ArrayList<>();
        User user = new User("testUser", "password", new Date(), "test@example.com", Visibility.PUBLIC, posts);
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userRepository.save(user);

        // When
        savedUser.setUsername("updatedUser");
        when(userRepository.save(savedUser)).thenReturn(savedUser);
        User updatedUser = userRepository.save(savedUser);

        // Then
        assertEquals(updatedUser.getUsername(), "updatedUser");
    }

    @Test
    public void testDeleteUser() {
        // Given
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        List<Post> posts = new ArrayList<>();
        User user = new User("testUser", "password", new Date(), "test@example.com", Visibility.PUBLIC, posts);
        when(userRepository.save(user)).thenReturn(user);
        User savedUser = userRepository.save(user);

        // When
        Mockito.doNothing().when(userRepository).delete(savedUser);
        userRepository.delete(savedUser);

        // Then
        Mockito.verify(userRepository, Mockito.times(1)).delete(savedUser);
    }

    @Test
    public void testFindAllUsers() {
        // Given
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        List<Post> posts = new ArrayList<>();
        User user1 = new User("testUser1", "password", new Date(), "test1@example.com", Visibility.PUBLIC, posts);
        User user2 = new User("testUser2", "password", new Date(), "test2@example.com", Visibility.PUBLIC, posts);
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        // When
        when(userRepository.findAll()).thenReturn(users);
        List<User> foundUsers = userRepository.findAll();

        // Then
        assertEquals(foundUsers.size(), 2);
    }

    @Test
    public void testSaveUserNull() {
        // Given
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        User user = null;

        // When
        when(userRepository.save(user)).thenThrow(new IllegalArgumentException());

        // Then
        assertThrows(IllegalArgumentException.class, () -> userRepository.save(user));
    }

    @Test
    public void testFindUserByInvalidId() {
        // Given
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        Long invalidId = -1L;

        // When
        when(userRepository.findById(invalidId)).thenReturn(Optional.empty());

        // Then
        Optional<User> foundUser = userRepository.findById(invalidId);
        assertFalse(foundUser.isPresent());
    }

    @Test
    public void testUpdateUserNull() {
        // Given
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        User user = null;

        // When
        when(userRepository.save(user)).thenThrow(new IllegalArgumentException());

        // Then
        assertThrows(IllegalArgumentException.class, () -> userRepository.save(user));
    }

    @Test
    public void testDeleteUserNull() {
        // Given
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        User user = null;

        // When
        Mockito.doThrow(new IllegalArgumentException()).when(userRepository).delete(user);

        // Then
        assertThrows(IllegalArgumentException.class, () -> userRepository.delete(user));
    }

    @Test
    public void testFindAllUsersEmpty() {
        // Given
        UserRepository userRepository = Mockito.mock(UserRepository.class);
        List<User> users = new ArrayList<>();

        // When
        when(userRepository.findAll()).thenReturn(users);

        // Then
        List<User> foundUsers = userRepository.findAll();
        assertTrue(foundUsers.isEmpty());
    }



}