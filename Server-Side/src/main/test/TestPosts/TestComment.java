package main.test.TestPosts;

import Entities.Misc.IDGenerator;
import Entities.Post.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestComment {
    private Comment comment;

    @BeforeEach
    public void setUp() {
        IDGenerator.resetCounters();
        Date timestamp = new Date();
        comment = new Comment(1, "This is a comment.", timestamp, 2);
    }

    @Test
    public void testConstructor() {
        setUp();
        assertEquals(1, comment.getPostId());
        assertNotNull(comment.getCommentId());
        assertEquals("This is a comment.", comment.getContent());
        assertNotNull(comment.getTimestamp());
        assertEquals(2, comment.getUserId());
    }

    @Test
    public void testSetPostId() {
        setUp();
        comment.setPostId(3);
        assertEquals(3, comment.getPostId());
    }

    @Test
    public void testSetContent() {
        setUp();
        comment.setContent("Updated comment content.");
        assertEquals("Updated comment content.", comment.getContent());
    }

    @Test
    public void testSetTimestamp() {
        setUp();
        Date newTimestamp = new Date();
        comment.setTimestamp(newTimestamp);
        assertEquals(newTimestamp, comment.getTimestamp());
    }

    @Test
    public void testSetUserId() {
        setUp();
        comment.setUserId(4);
        assertEquals(4, comment.getUserId());
    }

    @Test
    public void testToString() {
        setUp();
        String expectedString = "Comment{" +
                "commentId=" + comment.getCommentId() +
                ", postId=1" +
                ", content='This is a comment.'" +
                ", timestamp=" + comment.getTimestamp() +
                ", userId=2" +
                '}';
        assertEquals(expectedString, comment.toString());
    }

    @Test
    public void testCommentIdNotNull() {
        setUp();
        assertNotNull(comment.getCommentId());
    }
}
