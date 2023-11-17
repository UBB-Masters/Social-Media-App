package Persistence.InMemoryRepositories;

import Entities.Exceptions.DataBaseException;
import Entities.User.User;
import Entities.Events.Events;

import java.util.HashSet;
import java.util.Set;

public class InMemoryEventRepository {

    private static InMemoryEventRepository instance = null;
    private Set<Events> events;

    private InMemoryEventRepository() {
        this.events = new HashSet<>();
    }

    public static InMemoryEventRepository getInstance() {
        if (instance == null) {
            instance = new InMemoryEventRepository();
        }
        return instance;
    }

    public Events findEventByID(long id) {
        return events.stream()
                .filter(event -> event.getID() == id)
                .findFirst()
                .orElse(null);
    }

    public Set<Events> getAllEvents() {
        return events;
    }

    public void addEvent(Events event) throws DataBaseException {
        if (events.contains(event)) {
            throw new DataBaseException("Event already exists");
        }
        events.add(event);
    }

    public void addParticipantToEvent(Events event, User user) {
        event.addParticipant(user);
    }

    public void removeParticipantFromEvent(Events event, User user) {
        event.getParticipants().remove(user);
    }

    public void removeParticipantFromEventById(long id, User user) {
        Events event = findEventByID(id);
        if (event != null) {
            event.getParticipants().remove(user);
        }
    }

    public void addInterestedUserToEvent(Events event, User user) {
        event.getInterestedUsers().add(user);
    }


    public void addInterestedUserToEventById(long id, User user) {
        Events event = findEventByID(id);
        if (event != null) {
            event.getInterestedUsers().add(user);
        }
    }

    //method to remove an event from repo

    public void removeEvent(Events event) throws DataBaseException {
        if (!events.contains(event)) {
            throw new DataBaseException("Event does not exist");
        }
        events.remove(event);
    }

    //remove event by id

    public void removeEventById(long id) throws DataBaseException {
        Events event = findEventByID(id);
        if (event != null) {
            events.remove(event);
        } else {
            throw new DataBaseException("Event not found");
        }
    }

    public void addParticipantToEventById(long id, User user) {
        Events event = findEventByID(id);
        if (event != null) {
            event.addParticipant(user);
        }
    }

    public void removeInterestedUserFromEvent(Events event, User user) {
        event.removeInterestedUser(user);
    }

    public void removeInterestedUserFromEventById(long id, User user) {
        Events event = findEventByID(id);
        if (event != null) {
            event.removeInterestedUser(user);
        }
    }

    //update event

    public void updateEvent(Events oldEvent, Events newEvent) throws DataBaseException {
        if (!events.contains(oldEvent)) {
            throw new DataBaseException("Event does not exist");
        }
        events.remove(oldEvent);
        events.add(newEvent);
    }
}
