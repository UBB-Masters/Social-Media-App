package Controller;

import Entities.Events.Events;
import Entities.Exceptions.DataBaseException;
import Entities.Message.MessageDecorator.BasicMessageDecorator;
import Entities.Message.MessageDecorator.MessageDecorator;
import Entities.Message.MessageFactory;
//import Entities.Post.Post;
import Entities.User.User;
//import Proxy.PostProxy;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


@Service
public class ServerController {
    private final static Logger LOGGER = Logger.getLogger(ServerController.class.getName());
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    private final EventRepository eventRepository;

    private boolean newPostNotification;



    @Autowired
    public ServerController(
//            InMemoryMessageRepository memoryInMemoryMessageRepository,
//            InMemoryEventRepository eventRepository,
//            InMemoryPostRepository inMemoryPostRepository,
            UserRepository userRepository,
            MessageRepository messageRepository,
            EventRepository eventRepository
    ) {
//        this.userRepository = userRepository;
//        this.memoryInMemoryMessageRepository = memoryInMemoryMessageRepository;
//        this.eventRepository = eventRepository;
//        this.inMemoryPostRepository = inMemoryPostRepository;
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.eventRepository = eventRepository;
    }



    public void addUser(User user) {
        userRepository.save(user);
    }

    public void updateUser(User oldUser, User newUser) {
        userRepository.save(newUser);
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

    public User getUserByID(long userID) {
        return userRepository.findById(userID).orElse(null);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User removeUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.delete(user);
        }
        return user;
    }


    public void sendMessage(User sender, User receiver, String message) {
        MessageFactory baseMessage = MessageFactory.createMessage(
                MessageFactory.MessageType.TEXT, message, sender, receiver);

        // Decorate the message
        MessageDecorator decoratedMessage = new BasicMessageDecorator(baseMessage);

        // Add the decorated message to the repository
        messageRepository.save(baseMessage);
    }

    //TODO -> implement a logging mechanism and encryption mechansim that uses the decorator for the messages
//
//
//    public void removeMessage(MessageDecorator message) {
//        memoryInMemoryMessageRepository.removeMessage(message);
//    }
//
//    public void removeMessageFactory(MessageFactory message) {
//        MessageDecorator messageDecorator = message.getDecoratedMessage();
//        memoryInMemoryMessageRepository.removeMessage(messageDecorator);
//    }
//
//
//
//    public ArrayList<MessageFactory> getUserMessages(User user) {
//        ArrayList<MessageFactory> userMessages = new ArrayList<>();
//
//        for (MessageDecorator message : memoryInMemoryMessageRepository.getMessages()) {
//            if (message.getReceiver().equals(user) && message instanceof BasicMessageDecorator decorator) {
//                userMessages.add(decorator.getDecoratedMessage());
//            }
//        }
//
//        return userMessages;
//    }
//
//
    public List<MessageFactory> getSentMessages(User sender) {
        List<MessageFactory> sentMessages = new ArrayList<>();

        for (MessageFactory message : messageRepository.findAll()) {
            if (message.getSender().equals(sender)) {
                sentMessages.add(message);
            }
        }

        return sentMessages;
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


//    public Events getEventByID(int eventId) {
//        // Logic to retrieve the event by ID
//        return eventRepository.findEventByID(eventId); // Example method call to retrieve the event from the repository
//    }


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

//
//    public void createPost(User user, String content) {
//        Post newPost = new Post(user.getID(), content, new Date());
//        inMemoryPostRepository.addPost(newPost);
//        List<User> users = getAllUsers();
//        for (User u : users) {
//            newPost.addObserver(u);
//        }
//
//        newPost.notifyObservers(); // Notify all observers (users) about the new post
//
//        this.newPostNotification = true;
//
//
//    }
//
//    public void createPostProxy(User user, PostProxy postProxy) {
//        // Loading content if necessary
//        String content = postProxy.getContent();
//
//        Post newPost = new Post(user.getID(), content, new Date());
//        inMemoryPostRepository.addPost(newPost);
//
//        List<User> users = getAllUsers();
//        for (User u : users) {
//            newPost.addObserver(u);
//        }
//
//        newPost.notifyObservers(); // Notify all observers (users) about the new post
//
//        this.newPostNotification = true;
//    }
//
//
//    public void addCommentToPost(Post post, Comment comment) {
//        post.addComment(comment);
//        inMemoryPostRepository.updatePost(post);
//    }
//
//    public void reactToPost(Post post, Reaction reaction) {
//        post.addReaction(reaction);
//        inMemoryPostRepository.updatePost(post);
//    }
//
//    public void addHashtagToPost(Post post, Hashtag hashtag) {
//        post.addHashtag(hashtag);
//        inMemoryPostRepository.updatePost(post);
//    }
//
//    public void removeHashtagFromPost(Post post, Hashtag hashtag) {
//        post.removeHashtag(hashtag);
//        inMemoryPostRepository.updatePost(post);
//    }
//
//    public List<Post> getAllPosts() {
//        return inMemoryPostRepository.getAllPosts();
//    }
//
//    public List<Post> getPostsByUser(User user) {
//        return inMemoryPostRepository.getPostsByUserId(user.getID());
//    }
//
//    public Post getPostById(long postId) {
//        return inMemoryPostRepository.getPostById(postId);
//    }
//
//    public boolean hasNewPostNotification() {
//        return newPostNotification;
//    }
//
//    public void clearNewPostNotification() {
//        this.newPostNotification = false;
//    }


    //get hashtag by id


}