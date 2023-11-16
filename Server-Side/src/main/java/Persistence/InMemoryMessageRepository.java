//package Persistence;
//
//import Entities.Message.MessageFactory;
//
//import java.util.HashSet;
//import java.util.Set;
//
//public class InMemoryMessageRepository {
//    private final Set<MessageFactory> messages;
//
//    private static InMemoryMessageRepository instance = null;
//
//    private InMemoryMessageRepository() {
//        messages = new HashSet<>();
//    }
//
//    public static InMemoryMessageRepository getInstance() {
//        if (instance == null) {
//            instance = new InMemoryMessageRepository();
//        }
//        return instance;
//    }
//
//    public Set<MessageFactory> getMessages() {
//        return messages;
//    }
//
//    //method to add a message to the repository
//    public void addMessage(MessageFactory message) {
//        messages.add(message);
//    }
//
//    public void removeMessage(MessageFactory message) {
//        messages.remove(message);
//    }
//}
package Persistence;

import Entities.Message.MessageDecorator.MessageDecorator;

import java.util.HashSet;
import java.util.Set;

public class InMemoryMessageRepository {
    private final Set<MessageDecorator> messages;

    private static InMemoryMessageRepository instance = null;

    private InMemoryMessageRepository() {
        messages = new HashSet<>();
    }

    public static InMemoryMessageRepository getInstance() {
        if (instance == null) {
            instance = new InMemoryMessageRepository();
        }
        return instance;
    }

    public Set<MessageDecorator> getMessages() {
        return messages;
    }

    // Update the method to add a decorated message to the repository
    public void addMessage(MessageDecorator message) {
        messages.add(message);
    }

    public void removeMessage(MessageDecorator message) {
        messages.remove(message);
    }
}
