package Controller.Services;

import Controller.EventRepository;
import Controller.ServerController;
import Entities.Events.Events;
import Entities.Exceptions.DataBaseException;
import Entities.User.User;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class EventService {

    private final static Logger LOGGER = Logger.getLogger(ServerController.class.getName());


    private final EventRepository eventRepository;

    @Autowired

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public void addEvent(Events event) {
        try {
            eventRepository.save(event);
            event.notifyObservers();
        } catch (DataBaseException e) {
            LOGGER.log(Level.SEVERE, "Exception while adding an event: " + e.getMessage(), e);
        }
    }

    public void removeEvent(Events event) {
        try {
            eventRepository.delete(event);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception while removing an event: " + ex.getMessage(), ex);
        }
    }


    public Set<User> getEventParticipants(Events event) {
        return event.getParticipants();
    }


    public List<Events> getAllEvents() {
        List<Events> events = eventRepository.findAll();
        events.forEach(event -> {
            Hibernate.initialize(event.getParticipants());
            Hibernate.initialize(event.getInterestedUsers());
        });
        return events;
    }


    public Events getEventById(Long id) {
        return eventRepository.findById(id).orElse(null);
    }


    public void addParticipantToEvent(Events event, User user) {
        event.addParticipant(user);
        eventRepository.save(event);
    }


    public void removeParticipantFromEvent(Events event, User user) {
        event.removeParticipant(user);
        eventRepository.save(event);
    }


    public void addInterestedUserToEvent(Events event, User user) {
        event.addInterestedUser(user);
        eventRepository.save(event);
    }


    public void removeInterestedUserFromEvent(Events event, User user) {
        event.removeInterestedUser(user);
        eventRepository.save(event);
    }


    public Events removeEventByID(Long id) {
        try {
            eventRepository.deleteById(id);
        } catch (DataBaseException e) {
            LOGGER.log(Level.SEVERE, "Exception while removing an event: " + e.getMessage(), e);
        }
        return null;
    }

    public void updateEvent(Events oldEvent, Events newEvent) {
        try {
            // Modify the fields of the existing event
            oldEvent.setEventName(newEvent.getEventName());
            oldEvent.setEventDescription(newEvent.getEventDescription());
            oldEvent.setEventDate(newEvent.getEventDate());
            oldEvent.setEventLocation(newEvent.getEventLocation());

            // Save the modified event
            eventRepository.save(oldEvent);
        } catch (DataBaseException e) {
            LOGGER.log(Level.SEVERE, "Exception while updating an event: " + e.getMessage(), e);
        }
    }

    public void joinEvent(User user, Events event) {
        event.addParticipant(user);
    }

    public void showInterest(User user, Events event) {
        event.addInterestedUser(user);
    }

    public Set<User> getUsersInterestedInEvent(Events event) {
        Set<User> allInterestedUsers = event.getInterestedUsers();
        Set<User> allParticipants = event.getParticipants();

        Set<User> interestedButNotParticipating = new HashSet<>(allInterestedUsers);
        interestedButNotParticipating.removeAll(allParticipants);

        return interestedButNotParticipating;
    }


}
