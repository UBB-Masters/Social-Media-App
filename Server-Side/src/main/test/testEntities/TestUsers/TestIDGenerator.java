package main.test.testEntities.TestUsers;

import Entities.Misc.IDGenerator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestIDGenerator {
    @Test
    public void testUniqueIDs() {
        IDGenerator.resetCounters(); // Reset counters for a clean test

        // Create an anonymous class for a hypothetical object type
        Object hypotheticalObject = new Object() {
        };

        // Generate 100 IDs for the anonymous hypothetical object type
        for (int i = 0; i < 100; i++) {
            long id = IDGenerator.generateID(hypotheticalObject.getClass());
            assertEquals(i + 1, id); // IDs should start from 1 and be unique
        }
    }

    @Test
    public void testUniqueIDsForDifferentTypes() {
        IDGenerator.resetCounters(); // Reset counters for a clean test

        // Create anonymous classes for different object types
        Object userObject = new Object() {
        };
        Object messageObject = new Object() {
        };

        // Generate 100 IDs for the anonymous User and Message object types
        for (int i = 0; i < 100; i++) {
            long userID = IDGenerator.generateID(userObject.getClass());
            long messageID = IDGenerator.generateID(messageObject.getClass());
            assertEquals(i + 1, userID); // User IDs should start from 1 and be unique
            assertEquals(i + 1, messageID); // Message IDs should start from 1 and be unique
        }
    }
}