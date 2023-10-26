package Entities.Message.MessageTypes;

import Entities.Exceptions.DataBaseException;
import Entities.Message.MessageTemplate;
import Entities.User.User;
import Persistence.InMemoryMessageRepository;
import Persistence.InMemoryUserRepository;
import jdk.jfr.Name;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AudioMessage extends MessageTemplate {
    public AudioMessage(String content, User sender, User receiver) {
        super(content, sender, receiver);
    }

    @Override
    public String convertContentToText() {
        return null;
    }
}










