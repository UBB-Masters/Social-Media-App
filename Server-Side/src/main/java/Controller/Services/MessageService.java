package Controller.Services;

import Controller.MessageRepository;
import Entities.Message.MessageDecorator.BasicMessageDecorator;
import Entities.Message.MessageDecorator.MessageDecorator;
import Entities.Message.MessageFactory;
import Entities.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
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

    public List<MessageFactory> getSentMessages(User sender) {
        List<MessageFactory> sentMessages = new ArrayList<>();

        for (MessageFactory message : messageRepository.findAll()) {
            if (message.getSender().equals(sender)) {
                sentMessages.add(message);
            }
        }

        return sentMessages;
    }
}
