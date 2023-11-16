package Controller;

import Entities.Exceptions.DataBaseException;
import Entities.Message.MessageDecorator.BasicMessageDecorator;
import Entities.Message.MessageDecorator.MessageDecorator;
import Entities.Message.MessageFactory;
import Entities.Post.Comment;
import Entities.Post.Hashtag;
import Entities.Post.Post;
import Entities.User.User;
import Entities.Events.Events;
import Persistence.EventRepository;
import Persistence.MessageRepository;
import Persistence.PostRepository;
import Persistence.UserRepository;
import Proxy.PostProxy;
import Entities.Reaction.Reaction;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerController {
    private final static Logger LOGGER = Logger.getLogger(ServerController.class.getName());
    private final UserRepository userRepository;
    private final MessageRepository memoryMessageRepository;
    private final EventRepository eventRepository;
    private final PostRepository postRepository;
    private boolean newPostNotification;


    public ServerController(
            UserRepository userRepository,
            MessageRepository memoryMessageRepository,
            EventRepository eventRepository,
            PostRepository postRepository
    ) {
        this.userRepository = userRepository;
        this.memoryMessageRepository = memoryMessageRepository;
        this.eventRepository = eventRepository;
        this.postRepository = postRepository;
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


//    public void sendMessage(User sender, User receiver, String message) {
//        memoryMessageRepository.addMessage(MessageFactory.createMessage(MessageFactory.MessageType.TEXT, message, sender, receiver));
//    }

    public void sendMessage(User sender, User receiver, String message) {
        MessageFactory baseMessage = MessageFactory.createMessage(
                MessageFactory.MessageType.TEXT, message, sender, receiver);

        // Decorate the message
        MessageDecorator decoratedMessage = new BasicMessageDecorator(baseMessage);

        // Add the decorated message to the repository
        memoryMessageRepository.addMessage(decoratedMessage);
    }


    public void removeMessage(MessageDecorator message) {
        memoryMessageRepository.removeMessage(message);
    }

    public void removeMessageFactory(MessageFactory message) {
        MessageDecorator messageDecorator = message.getDecoratedMessage();
        memoryMessageRepository.removeMessage(messageDecorator);
    }


//    public ArrayList<MessageFactory> getUserMessages(User user){
//        ArrayList<MessageFactory> userMessages = new ArrayList<>();
//
//        for(MessageFactory message : memoryMessageRepository.getMessages()){
//            if(message.getReceiver().equals(user))
//                userMessages.add(message);
//        }
//
//        return userMessages;
//    }


//    public ArrayList<MessageFactory> getSentMessages(User sender) {
//        ArrayList<MessageFactory> sentMessages = new ArrayList<>();
//
//        for (MessageFactory message : memoryMessageRepository.getMessages()) {
//            if (message.getSender().equals(sender)) {
//                sentMessages.add(message);
//            }
//        }
//
//        return sentMessages;
//    }


    // Update method signature to return MessageDecorator type
//    public ArrayList<MessageFactory> getUserMessages(User user) {
//        ArrayList<MessageFactory> userMessages = new ArrayList<>();
//
//        for (MessageDecorator message : memoryMessageRepository.getMessages()) {
//            if (message.getReceiver().equals(user)) {
//                if (message instanceof BasicMessageDecorator) {
//                    MessageFactory messageFactory = ((BasicMessageDecorator) message).getDecoratedMessage();
//                    userMessages.add(messageFactory);
//                }
//            }
//        }
//
//        return userMessages;
//    }

    public ArrayList<MessageFactory> getUserMessages(User user) {
        ArrayList<MessageFactory> userMessages = new ArrayList<>();

        for (MessageDecorator message : memoryMessageRepository.getMessages()) {
            if (message.getReceiver().equals(user) && message instanceof BasicMessageDecorator decorator) {
                userMessages.add(decorator.getDecoratedMessage());
            }
        }

        return userMessages;
    }


    public ArrayList<MessageFactory> getSentMessages(User sender) {
        ArrayList<MessageFactory> sentMessages = new ArrayList<>();

        for (MessageDecorator message : memoryMessageRepository.getMessages()) {
            if (message.getSender().equals(sender)) {
                if (message instanceof MessageDecorator) {
                    MessageDecorator decorator = (MessageDecorator) message;
                    sentMessages.add(((BasicMessageDecorator) decorator).getDecoratedMessage());
                } else {
                    sentMessages.add((MessageFactory) message);
                }
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
            event.notifyObservers();
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


    public void createPost(User user, String content) {
        Post newPost = new Post(user.getID(), content, new Date());
        postRepository.addPost(newPost);
        List<User> users = getAllUsers();
        for (User u : users) {
            newPost.addObserver(u);
        }

        newPost.notifyObservers(); // Notify all observers (users) about the new post

        this.newPostNotification = true;


    }

    public void createPostProxy(User user, PostProxy postProxy) {
        // Loading content if necessary
        String content = postProxy.getContent();

        Post newPost = new Post(user.getID(), content, new Date());
        postRepository.addPost(newPost);

        List<User> users = getAllUsers();
        for (User u : users) {
            newPost.addObserver(u);
        }

        newPost.notifyObservers(); // Notify all observers (users) about the new post

        this.newPostNotification = true;
    }


    public void addCommentToPost(Post post, Comment comment) {
        post.addComment(comment);
        postRepository.updatePost(post);
    }

    public void reactToPost(Post post, Reaction reaction) {
        post.addReaction(reaction);
        postRepository.updatePost(post);
    }

    public void addHashtagToPost(Post post, Hashtag hashtag) {
        post.addHashtag(hashtag);
        postRepository.updatePost(post);
    }

    public void removeHashtagFromPost(Post post, Hashtag hashtag) {
        post.removeHashtag(hashtag);
        postRepository.updatePost(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.getAllPosts();
    }

    public List<Post> getPostsByUser(User user) {
        return postRepository.getPostsByUserId(user.getID());
    }

    public Post getPostById(long postId) {
        return postRepository.getPostById(postId);
    }

    public boolean hasNewPostNotification() {
        return newPostNotification;
    }

    public void clearNewPostNotification() {
        this.newPostNotification = false;
    }


    //get hashtag by id


}