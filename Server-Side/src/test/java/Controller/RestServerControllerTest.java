package Controller;

import Controller.RestServerController;
import Controller.Services.*;
import Entities.Events.Events;
import Entities.Message.MessageFactory;
import Entities.Post.Comment;
import Entities.Post.Post;
import Entities.User.User;
import Proxy.PostProxy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RestServerControllerTest {

    @InjectMocks
    RestServerController restServerController;

    @Mock
    UserService userService;
    @Mock
    private User user;

    @Mock
    private MessageService messageService;

    @Mock
    private EventService eventService;

    @Mock
    private PostService postService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddUser() {
        User user = new User();
        doNothing().when(userService).addUser(user);
        ResponseEntity<String> response = restServerController.addUser(user);
        assertEquals("User added successfully", response.getBody());
        verify(userService, times(1)).addUser(user);
    }

    @Test
    public void testGetAllUsers() {
        List<User> users = Collections.singletonList(new User());
        when(userService.getAllUsers()).thenReturn(users);
        ResponseEntity<List<User>> response = restServerController.getAllUsers();
        verify(userService, times(1)).getAllUsers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }


    @Test
    public void testUpdateUser_ValidCase() {
        when(userService.getUserById(1L)).thenReturn(user);
        ResponseEntity<String> response = restServerController.updateUser(1L, user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User updated successfully", response.getBody());
        verify(userService, times(1)).updateUser(user, user);
    }

    @Test
    public void testGetUserById_ValidCase() {
        when(userService.getUserById(1L)).thenReturn(user);
        ResponseEntity<User> response = restServerController.getUserById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testRemoveUserById_ValidCase() {
        User user = new User();
        when(userService.removeUserById(1L)).thenReturn(user);
        ResponseEntity<String> response = restServerController.removeUserById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User removed successfully", response.getBody());
        verify(userService, times(1)).removeUserById(1L);
    }

    @Test
    public void testUpdateUserUsername_ValidCase() {
        User user = new User();
        when(userService.getUserById(1L)).thenReturn(user);
        ResponseEntity<String> response = restServerController.updateUserUsername(1L, "newUsername");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Username updated successfully", response.getBody());
        verify(userService, times(1)).updateUserUsername(user, "newUsername");
    }


    @Test
    public void testUpdateUserPassword_ValidCase() {
        User user = new User();
        when(userService.getUserById(1L)).thenReturn(user);
        ResponseEntity<String> response = restServerController.updateUserPassword(1L, "newPassword");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Password updated successfully", response.getBody());
        verify(userService, times(1)).updateUserPassword(user, "newPassword");
    }

    @Test
    public void testUpdateUserBirthday_ValidCase() {
        User user = new User();
        Date newBirthday = new Date();
        when(userService.getUserById(1L)).thenReturn(user);
        ResponseEntity<String> response = restServerController.updateUserBirthday(1L, newBirthday);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Birthday updated successfully", response.getBody());
        verify(userService, times(1)).updateUserBirthday(user, newBirthday);
    }

    @Test
    public void testUpdateUserEmail_ValidCase() {
        User user = new User();
        when(userService.getUserById(1L)).thenReturn(user);
        ResponseEntity<String> response = restServerController.updateUserEmail(1L, "newEmail@example.com");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Email updated successfully", response.getBody());
        verify(userService, times(1)).updateUserEmail(user, "newEmail@example.com");
    }

    @Test
    public void testSendMessage_ValidCase() {
        MessageRequest messageRequest = new MessageRequest();
        ResponseEntity<String> response = restServerController.sendMessage(messageRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Message sent successfully", response.getBody());
        verify(messageService, times(1)).sendMessage(messageRequest.getSender(), messageRequest.getReceiver(), messageRequest.getMessage());
    }

    @Test
    public void testGetSentMessages_ValidCase() {
        User user = new User();
        when(userService.getUserById(1L)).thenReturn(user);
        List<MessageFactory> sentMessages = new ArrayList<>();
        when(messageService.getSentMessages(user)).thenReturn(sentMessages);
        ResponseEntity<List<MessageFactory>> response = restServerController.getSentMessages(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(sentMessages, response.getBody());
    }

    @Test
    public void testAddEvent_ValidCase() {
        Events event = new Events();
        ResponseEntity<String> response = restServerController.addEvent(event);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Event added successfully", response.getBody());
        verify(eventService, times(1)).addEvent(event);
    }


    @Test
    public void testRemoveEvent_ValidCase() {
        Events event = new Events();
        ResponseEntity<String> response = restServerController.removeEvent(event);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Event removed successfully", response.getBody());
        verify(eventService, times(1)).removeEvent(event);
    }

    @Test
    public void testGetEventParticipants_ValidCase() {
        Events event = new Events();
        Set<User> participants = new HashSet<>();
        when(eventService.getEventById(1L)).thenReturn(event);
        when(eventService.getEventParticipants(event)).thenReturn(participants);
        ResponseEntity<Set<User>> response = restServerController.getEventParticipants(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(participants, response.getBody());
    }

    @Test
    public void testGetAllEvents_ValidCase() {
        List<Events> events = new ArrayList<>();
        when(eventService.getAllEvents()).thenReturn(events);
        ResponseEntity<List<Events>> response = restServerController.getAllEvents();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(events, response.getBody());
    }

    @Test
    public void testGetEventById_ValidCase() {
        Events event = new Events();
        when(eventService.getEventById(1L)).thenReturn(event);
        ResponseEntity<Events> response = restServerController.getEventById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(event, response.getBody());
    }

    @Test
    public void testAddParticipantToEvent_ValidCase() {
        Events event = new Events();
        User user = new User();
        when(eventService.getEventById(1L)).thenReturn(event);
        ResponseEntity<String> response = restServerController.addParticipantToEvent(1L, user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Participant added to event", response.getBody());
        verify(eventService, times(1)).addParticipantToEvent(event, user);
    }

    @Test
    public void testRemoveParticipantFromEvent_ValidCase() {
        Events event = new Events();
        User user = new User();
        when(eventService.getEventById(1L)).thenReturn(event);
        ResponseEntity<String> response = restServerController.removeParticipantFromEvent(1L, user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Participant removed from event", response.getBody());
        verify(eventService, times(1)).removeParticipantFromEvent(event, user);
    }

    @Test
    public void testAddInterestedUserToEvent_ValidCase() {
        Events event = new Events();
        User user = new User();
        when(eventService.getEventById(1L)).thenReturn(event);
        ResponseEntity<String> response = restServerController.addInterestedUserToEvent(1L, user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User added to interested list", response.getBody());
        verify(eventService, times(1)).addInterestedUserToEvent(event, user);
    }

    @Test
    public void testRemoveInterestedUserFromEvent_ValidCase() {
        Events event = new Events();
        User user = new User();
        when(eventService.getEventById(1L)).thenReturn(event);
        ResponseEntity<String> response = restServerController.removeInterestedUserFromEvent(1L, user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User removed from interested list", response.getBody());
        verify(eventService, times(1)).removeInterestedUserFromEvent(event, user);
    }


    @Test
    public void testRemoveEventByID_ValidCase() {
        Events event = new Events();
        when(eventService.removeEventByID(1L)).thenReturn(event);
        ResponseEntity<String> response = restServerController.removeEventByID(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Event removed successfully", response.getBody());
    }

    @Test
    public void testUpdateEvent_ValidCase() {
        Events oldEvent = new Events();
        Events newEvent = new Events();
        when(eventService.getEventById(1L)).thenReturn(oldEvent);
        ResponseEntity<String> response = restServerController.updateEvent(1L, newEvent);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Event updated successfully", response.getBody());
        verify(eventService, times(1)).updateEvent(oldEvent, newEvent);
    }

    @Test
    public void testJoinEvent_ValidCase() {
        Events event = new Events();
        User user = new User();
        when(eventService.getEventById(1L)).thenReturn(event);
        ResponseEntity<String> response = restServerController.joinEvent(1L, user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User joined event", response.getBody());
        verify(eventService, times(1)).joinEvent(user, event);
    }

    @Test
    public void testShowInterest_ValidCase() {
        Events event = new Events();
        User user = new User();
        when(eventService.getEventById(1L)).thenReturn(event);
        ResponseEntity<String> response = restServerController.showInterest(1L, user);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User showed interest in event", response.getBody());
        verify(eventService, times(1)).showInterest(user, event);
    }

    @Test
    public void testGetUsersInterestedInEvent_ValidCase() {
        Events event = new Events();
        Set<User> users = new HashSet<>();
        when(eventService.getEventById(1L)).thenReturn(event);
        when(eventService.getUsersInterestedInEvent(event)).thenReturn(users);
        ResponseEntity<Set<User>> response = restServerController.getUsersInterestedInEvent(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }

    @Test
    public void testCreatePost_ValidCase() {
        User user = new User();
        String content = "content";
        ResponseEntity<String> response = restServerController.createPost(user, content);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Post created successfully", response.getBody());
        verify(postService, times(1)).createPost(user, content);
    }

    @Test
    public void testGetAllPosts_ValidCase() {
        List<Post> posts = new ArrayList<>();
        when(postService.getAllPosts()).thenReturn(posts);
        ResponseEntity<List<Post>> response = restServerController.getAllPosts();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(posts, response.getBody());
    }

    @Test
    public void testCreatePostProxy_ValidCase() {
        UserPostProxy userPostProxy = new UserPostProxy();
        ResponseEntity<String> response = restServerController.createPostProxy(userPostProxy);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Post created successfully using proxy", response.getBody());
        verify(postService, times(1)).createPostProxy(userPostProxy.getUser(), userPostProxy.getPostProxy());
    }


    @Test
    public void testHasNewPostNotification_ValidCase() {
        when(postService.hasNewPostNotification()).thenReturn(true);
        ResponseEntity<Boolean> response = restServerController.hasNewPostNotification();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody());
    }

    @Test
    public void testClearNewPostNotification_ValidCase() {
        ResponseEntity<String> response = restServerController.clearNewPostNotification();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Post notification cleared", response.getBody());
        verify(postService, times(1)).clearNewPostNotification();
    }

    @Test
    public void testGetPostsByUser_ValidCase() {
        User user = new User();
        List<Post> posts = new ArrayList<>();
        when(userService.getUserById(1L)).thenReturn(user);
        when(postService.getPostsByUser(user)).thenReturn(posts);
        ResponseEntity<List<Post>> response = restServerController.getPostsByUser(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(posts, response.getBody());
    }

    @Test
    public void testGetPostById_ValidCase() {
        Post post = new Post();
        when(postService.getPostById(1L)).thenReturn(post);
        ResponseEntity<Post> response = restServerController.getPostById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(post, response.getBody());
    }

    @Test
    public void testAddCommentToPost_ValidCase() {
        Post post = new Post();
        Comment comment = new Comment();
        when(postService.getPostById(1L)).thenReturn(post);
        ResponseEntity<String> response = restServerController.addCommentToPost(1L, comment);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Comment added to the post", response.getBody());
        verify(postService, times(1)).addCommentToPost(post, comment);
    }


    @Test
    public void testGetSentMessages_InvalidCase() {
        when(userService.getUserById(1L)).thenReturn(null);
        ResponseEntity<List<MessageFactory>> response = restServerController.getSentMessages(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testGetEventById_InvalidCase() {
        when(eventService.getEventById(1L)).thenReturn(null);
        ResponseEntity<Events> response = restServerController.getEventById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testRemoveInterestedUserFromEvent_InvalidCase() {
        User user = new User();
        when(eventService.getEventById(1L)).thenReturn(null);
        ResponseEntity<String> response = restServerController.removeInterestedUserFromEvent(1L, user);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Event not found", response.getBody());
    }

    @Test
    public void testRemoveEventByID_InvalidCase() {
        when(eventService.removeEventByID(1L)).thenReturn(null);
        ResponseEntity<String> response = restServerController.removeEventByID(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Event not found", response.getBody());
    }

    @Test
    public void testGetPostById_InvalidCase() {
        when(postService.getPostById(1L)).thenReturn(null);
        ResponseEntity<Post> response = restServerController.getPostById(1L);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAddCommentToPost_InvalidCase() {
        Comment comment = new Comment();
        when(postService.getPostById(1L)).thenReturn(null);
        ResponseEntity<String> response = restServerController.addCommentToPost(1L, comment);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Post not found", response.getBody());
    }

    @Test
    public void testGetPostsByUser_InvalidCase() {
        long invalidUserId = 999L; // Assuming this ID does not exist in the database
        Mockito.when(userService.getUserById(invalidUserId)).thenReturn(null);

        ResponseEntity<List<Post>> response = restServerController.getPostsByUser(invalidUserId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }
}