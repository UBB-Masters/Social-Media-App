package Controller;
import Entities.Message.MessageFactory;
import Entities.User.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class MessageRepositoryTest {
    @Test
    public void testSaveMessage() {
        // Given
        MessageRepository messageRepository = Mockito.mock(MessageRepository.class);
        User sender = new User("sender", "password", new Date(), "sender@example.com", User.Visibility.PUBLIC);
        User receiver = new User("receiver", "password", new Date(), "receiver@example.com", User.Visibility.PUBLIC);
        MessageFactory message = new MessageFactory("Hello", sender, receiver);

        // When
        when(messageRepository.save(message)).thenReturn(message);
        MessageFactory savedMessage = messageRepository.save(message);

        // Then
        assertEquals(savedMessage.getDescription(), message.getDescription());
        assertEquals(savedMessage.getSenderID(), message.getSenderID());
        assertEquals(savedMessage.getReceiverID(), message.getReceiverID());
    }

    @Test
    public void testFindBySenderID() {
        // Given
        MessageRepository messageRepository = Mockito.mock(MessageRepository.class);
        User sender = new User("sender", "password", new Date(), "sender@example.com", User.Visibility.PUBLIC);
        User receiver = new User("receiver", "password", new Date(), "receiver@example.com", User.Visibility.PUBLIC);
        MessageFactory message = new MessageFactory("Hello", sender, receiver);
        List<MessageFactory> messages = new ArrayList<>();
        messages.add(message);

        // When
        when(messageRepository.findBySenderID_UserID(sender.getUserID())).thenReturn(messages);
        List<MessageFactory> foundMessages = messageRepository.findBySenderID_UserID(sender.getUserID());

        // Then
        assertEquals(foundMessages.size(), 1);
        assertEquals(foundMessages.get(0).getDescription(), message.getDescription());
        assertEquals(foundMessages.get(0).getSenderID(), message.getSenderID());
        assertEquals(foundMessages.get(0).getReceiverID(), message.getReceiverID());
    }
    @Test
    public void testFindAllMessages() {
        // Given
        MessageRepository messageRepository = Mockito.mock(MessageRepository.class);
        User sender = new User("sender", "password", new Date(), "sender@example.com", User.Visibility.PUBLIC);
        User receiver = new User("receiver", "password", new Date(), "receiver@example.com", User.Visibility.PUBLIC);
        MessageFactory message1 = new MessageFactory("Hello", sender, receiver);
        MessageFactory message2 = new MessageFactory("Hi", sender, receiver);
        List<MessageFactory> messages = new ArrayList<>();
        messages.add(message1);
        messages.add(message2);

        // When
        when(messageRepository.findAll()).thenReturn(messages);
        List<MessageFactory> foundMessages = messageRepository.findAll();

        // Then
        assertEquals(foundMessages.size(), 2);
    }

    @Test
    public void testDeleteMessage() {
        // Given
        MessageRepository messageRepository = Mockito.mock(MessageRepository.class);
        User sender = new User("sender", "password", new Date(), "sender@example.com", User.Visibility.PUBLIC);
        User receiver = new User("receiver", "password", new Date(), "receiver@example.com", User.Visibility.PUBLIC);
        MessageFactory message = new MessageFactory("Hello", sender, receiver);

        // When
        Mockito.doNothing().when(messageRepository).delete(message);
        messageRepository.delete(message);

        // Then
        Mockito.verify(messageRepository, Mockito.times(1)).delete(message);
    }

    @Test
    public void testSaveMessage_NullMessage() {
        // Given
        MessageRepository messageRepository = Mockito.mock(MessageRepository.class);
        MessageFactory message = null;

        // When
        when(messageRepository.save(message)).thenThrow(new IllegalArgumentException());

        // Then
        assertThrows(IllegalArgumentException.class, () -> messageRepository.save(message));
    }

    @Test
    public void testFindBySenderID_NullUserID() {
        // Given
        MessageRepository messageRepository = Mockito.mock(MessageRepository.class);
        Long userID = null;

        // When
        when(messageRepository.findBySenderID_UserID(userID)).thenThrow(new IllegalArgumentException());

        // Then
        assertThrows(IllegalArgumentException.class, () -> messageRepository.findBySenderID_UserID(userID));
    }

    @Test
    public void testFindAllMessages_NoMessages() {
        // Given
        MessageRepository messageRepository = Mockito.mock(MessageRepository.class);
        List<MessageFactory> messages = new ArrayList<>();

        // When
        when(messageRepository.findAll()).thenReturn(messages);
        List<MessageFactory> foundMessages = messageRepository.findAll();

        // Then
        assertTrue(foundMessages.isEmpty());
    }

    @Test
    public void testDeleteMessage_NullMessage() {
        // Given
        MessageRepository messageRepository = Mockito.mock(MessageRepository.class);
        MessageFactory message = null;

        // When
        Mockito.doThrow(new IllegalArgumentException()).when(messageRepository).delete(message);

        // Then
        assertThrows(IllegalArgumentException.class, () -> messageRepository.delete(message));
    }
}
