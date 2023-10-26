import Entities.Message.MessageTemplate;
import Entities.User.User;
import Persistence.InMemoryMessageRepository;
import org.junit.Before;
import org.junit.Test;

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
        User sender = new User(1, "sender", "password", null, null);
        User receiver = new User(1, "receiver", "password", null, null);

        MessageTemplate message = new MessageTemplate("Hello, world!", sender, receiver) {
            @Override
            public String convertContentToText() {
                return content;
            }
        };

        messageRepository.getMessages().add(message);

        Set<MessageTemplate> savedMessages = messageRepository.getMessages();
        assertEquals(1, savedMessages.size());
        assertEquals(message, savedMessages.iterator().next());
    }

}



