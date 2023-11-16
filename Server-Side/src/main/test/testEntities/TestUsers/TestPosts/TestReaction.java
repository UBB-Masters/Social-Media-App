package main.test.testEntities.TestUsers.TestPosts;

import Entities.Misc.IDGenerator;
import Entities.Reaction.Reaction;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TestReaction {
    private Reaction reaction;

    @BeforeEach
    public void setUp() {
        IDGenerator.resetCounters();
        reaction = new Reaction(1, "like");
    }

    @Test
    public void testConstructor() {
        setUp();
        assertEquals(1, reaction.getReactionId());
        assertEquals(1, reaction.getUserId());
        assertEquals("like", reaction.getReactionType());
    }

    @Test
    public void testSetUserId() {
        reaction.setUserId(2);
        assertEquals(2, reaction.getUserId());
    }

    @Test
    public void testSetReactionType() {
        reaction.setReactionType("love");
        assertEquals("love", reaction.getReactionType());
    }

    @Test
    public void testToString() {
        String expectedString = "Reaction{reactionId=1, userId=2, reactionType='love'}";
        assertEquals(expectedString, reaction.toString());
    }

    @Test
    public void testNotEquals() {
        Reaction differentReaction = new Reaction(2, "love");
        assertNotEquals(reaction, differentReaction);
    }
}
