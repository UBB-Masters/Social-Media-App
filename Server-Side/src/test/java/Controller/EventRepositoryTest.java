package Controller;

import Entities.Events.Events;
import Entities.User.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventRepositoryTest {

    @Mock
    private EventRepository eventRepository;

    @Mock
    private Events event;

    @Test
    public void testSaveEvent() {
        when(eventRepository.save(event)).thenReturn(event);
        Events savedEvent = eventRepository.save(event);
        assertEquals(savedEvent, event);
        verify(eventRepository, times(1)).save(event);
    }

    @Test
    public void testFindEventById() {
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));
        Optional<Events> foundEvent = eventRepository.findById(1L);
        assertEquals(foundEvent.get(), event);
        verify(eventRepository, times(1)).findById(1L);
    }

    @Test
    public void testDeleteEvent() {
        eventRepository.delete(event);
        verify(eventRepository, times(1)).delete(event);
    }

    @Test
    public void testAddParticipant() {
        User user = new User();
        event.addParticipant(user);
        verify(event, times(1)).addParticipant(user);
    }

    @Test
    public void testRemoveParticipant() {
        User user = new User();
        event.removeParticipant(user);
        verify(event, times(1)).removeParticipant(user);
    }

    @Test
    public void testAddInterestedUser() {
        User user = new User();
        event.addInterestedUser(user);
        verify(event, times(1)).addInterestedUser(user);
    }

    @Test
    public void testRemoveInterestedUser() {
        User user = new User();
        event.removeInterestedUser(user);
        verify(event, times(1)).removeInterestedUser(user);
    }



    @Test
    public void testFindEventById_NonExistingId() {
        when(eventRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<Events> foundEvent = eventRepository.findById(1L);
        assertFalse(foundEvent.isPresent());
    }

}