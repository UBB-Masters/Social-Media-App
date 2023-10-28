package Controller;

import Entities.Message.MessageFactory;
import Entities.User.User;
import Persistence.InMemoryMessageRepository;
import Persistence.InMemoryUserRepository;

import java.util.ArrayList;
import java.util.List;

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

    void sendMessage(User sender, User receiver, String message) {
        memoryMessageRepository.addMessage(MessageFactory.createMessage(MessageFactory.MessageType.TEXT, message, sender, receiver));
    }

    void removeMessage(MessageFactory message) {
        memoryMessageRepository.removeMessage(message);
    }

    ArrayList<MessageFactory> getUserMessages(User user){
        ArrayList<MessageFactory> userMessages = new ArrayList<>();

        for(MessageFactory message : memoryMessageRepository.getMessages()){
            if(message.getReceiver().equals(user))
                userMessages.add(message);
        }

        return userMessages;
    }

    public User getUserById(int userId) {

        return userRepository.findById(userId);
    }

    //method that returns all users from the repo

    public List<User> getAllUsers() {
        return new ArrayList<>(userRepository.getEntities());
    }

    public User removeUserByID(Integer id) {

        return userRepository.removeUserById(id);
    }
}