package Controller;

import Entities.Message.MessageFactory;
import Entities.User.User;
import Persistence.InMemoryMessageRepository;
import Persistence.InMemoryUserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ServerController {
    private final InMemoryUserRepository userRepository;
    private final InMemoryMessageRepository memoryMessageRepository;

    public ServerController(InMemoryUserRepository userRepository, InMemoryMessageRepository memoryMessageRepository) {
        this.userRepository = userRepository;
        this.memoryMessageRepository = memoryMessageRepository;
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


}