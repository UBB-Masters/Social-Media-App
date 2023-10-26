package main.test.testRepo;

import Entities.Message.MessageFactory;
import Entities.User.User;
import Persistence.InMemoryMessageRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class InMemoryRepoMessageTest {

    private InMemoryMessageRepository messageRepository;

    @Before
    public void setUp() {
        messageRepository = new InMemoryMessageRepository();
    }

    @Test
    public void testSaveMessage() {
        User sender = new User("sender", "password", new Date(), "dob.bob@gmail.com");
        User receiver = new User("receiver", "password", new Date(), "dob.bob@gmail.com");

        MessageFactory message = new MessageFactory("Hello, world!", sender, receiver) {
            public String convertContentToText() {
                return content;
            }
        };

        messageRepository.getMessages().add(message);

        Set<MessageFactory> savedMessages = messageRepository.getMessages();
        assertEquals(1, savedMessages.size());
        assertEquals(message, savedMessages.iterator().next());
    }
}