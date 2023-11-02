package Controller;

import Entities.Exceptions.DataBaseException;
import Entities.Message.MessageFactory;
import Entities.User.User;
import Persistence.InMemoryMessageRepository;
import Persistence.InMemoryUserRepository;
import Persistence.InMemoryEventRepository;
import Events.Events;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerController {
    private final InMemoryUserRepository userRepository;
    private final InMemoryMessageRepository memoryMessageRepository;

    private final InMemoryEventRepository eventRepository;

    private final static Logger LOGGER = Logger.getLogger(ServerController.class.getName());


    public ServerController(
            InMemoryUserRepository userRepository,
            InMemoryMessageRepository memoryMessageRepository,
            InMemoryEventRepository eventRepository
    ) {
        this.userRepository = userRepository;
        this.memoryMessageRepository = memoryMessageRepository;
        this.eventRepository = eventRepository;
    }

    public void addUser(User user) {
        userRepository.add(user);
    }

    public void removeUser(User user) {
        userRepository.remove(user);
    }

    public void updateUser(User oldUser, User newUser) {
        userRepository.update(oldUser, newUser);
    }

    public void updateUserUsername(User user, String newUsername) {
        User updatedUser = new User(user);
        updatedUser.setUsername(newUsername);
        updateUser(user, updatedUser);
    }

    public void updateUserPassword(User user, String newPassword) {
        User updatedUser = new User(user);
        updatedUser.setPassword(newPassword);
        updateUser(user, updatedUser);
    }

    public void updateUserBirthday(User user, Date newBirthday) {
        User updatedUser = new User(user);
        updatedUser.setBirthdate(newBirthday);
        updateUser(user, updatedUser);
    }

    public void updateUserEmail(User user, String newEmail) {
        User updatedUser = new User(user);
        updatedUser.setEmail(newEmail);
        updateUser(user, updatedUser);
    }


    public void sendMessage(User sender, User receiver, String message) {
        memoryMessageRepository.addMessage(MessageFactory.createMessage(MessageFactory.MessageType.TEXT, message, sender, receiver));
    }

    public void removeMessage(MessageFactory message) {
        memoryMessageRepository.removeMessage(message);
    }

    public ArrayList<MessageFactory> getUserMessages(User user){
        ArrayList<MessageFactory> userMessages = new ArrayList<>();

        for(MessageFactory message : memoryMessageRepository.getMessages()){
            if(message.getReceiver().equals(user))
                userMessages.add(message);
        }

        return userMessages;
    }


    public ArrayList<MessageFactory> getSentMessages(User sender) {
        ArrayList<MessageFactory> sentMessages = new ArrayList<>();

        for (MessageFactory message : memoryMessageRepository.getMessages()) {
            if (message.getSender().equals(sender)) {
                sentMessages.add(message);
            }
        }

        return sentMessages;
    }


    public User getUserById(int userId) {
        return userRepository.findById(userId);
    }

    public User getUserById(long userId) {
        return userRepository.findById(userId);
    }

    //method that returns all users from the repo
    public List<User> getAllUsers() {
        return new ArrayList<>(userRepository.getEntities());
    }

    public User removeUserByID(Integer id) {
        return userRepository.removeUserById(id);
    }

    public User removeUserByID(Long id) {
        return userRepository.removeUserById(id);
    }


    public void addEvent(Events event) {
        try {
            eventRepository.addEvent(event);
        } catch (DataBaseException e) {
            LOGGER.log(Level.SEVERE, "Exception while adding an event: " + e.getMessage(), e);
        }
    }

    public void removeEvent(Events event) {
        try {
            eventRepository.removeEvent(event);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Exception while removing an event: " + ex.getMessage(), ex);
        }
    }


    public Set<User> getEventParticipants(Events event) {
        return event.getParticipants();
    }


    public Set<Events> getAllEvents() {
        return eventRepository.getAllEvents();
    }

    public Events getEventById(long id) {
        return eventRepository.findEventByID(id);
    }

    public void addParticipantToEvent(Events event, User user) {
        eventRepository.addParticipantToEvent(event, user);
    }

    public void addParticipantToEventById(long id, User user) {
        eventRepository.addParticipantToEventById(id, user);
    }

    public void removeParticipantFromEvent(Events event, User user) {
        eventRepository.removeParticipantFromEvent(event, user);
    }

    public void removeParticipantFromEventById(long id, User user) {
        eventRepository.removeParticipantFromEventById(id, user);
    }

    public void addInterestedUserToEvent(Events event, User user) {
        eventRepository.addInterestedUserToEvent(event, user);
    }

    public void addInterestedUserToEventById(long id, User user) {
        eventRepository.addInterestedUserToEventById(id, user);
    }

    public void removeInterestedUserFromEvent(Events event, User user) {
        eventRepository.removeInterestedUserFromEvent(event, user);
    }

    public void removeInterestedUserFromEventById(long id, User user) {
        eventRepository.removeInterestedUserFromEventById(id, user);
    }

    public Events removeEventByID(Integer id) {
        try {
            eventRepository.removeEventById(id);
        } catch (DataBaseException e) {
            LOGGER.log(Level.SEVERE, "Exception while removing an event: " + e.getMessage(), e);
        }
        return null;
    }

    public void updateEvent(Events oldEvent, Events newEvent) {
        try {
            eventRepository.updateEvent(oldEvent, newEvent);
        } catch (DataBaseException e) {
            LOGGER.log(Level.SEVERE, "Exception while updating an event: " + e.getMessage(), e);
        }
    }

    public Events getEventByID(int eventId) {
        // Logic to retrieve the event by ID
        return eventRepository.findEventByID(eventId); // Example method call to retrieve the event from the repository
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