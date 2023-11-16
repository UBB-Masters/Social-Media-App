package main.test.testEntities.TestUsers.TestPosts;

import Entities.Post.Comment;
import Entities.Post.Post;
import Entities.Reaction.Reaction;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestPost {
    private Post post;

    @BeforeEach
    public void setUp() {
        Date timestamp = new Date();
        post = new Post(1, "This is a post.", timestamp);
    }

    @Test
    public void testConstructor() {
        setUp();
        assertNotNull(post.getPostId());
        assertEquals(1, post.getUserId());
        assertEquals("This is a post.", post.getContent());
        assertNotNull(post.getTimestamp());
        assertNotNull(post.getComments());
        assertNotNull(post.getReactions());
        assertEquals(0, post.getComments().size());
        assertEquals(0, post.getReactions().size());
    }

    @Test
    public void testSetUserId() {
        setUp();
        post.setUserId(2);
        assertEquals(2, post.getUserId());
    }

    @Test
    public void testSetContent() {
        setUp();
        post.setContent("Updated post content.");
        assertEquals("Updated post content.", post.getContent());
    }

    @Test
    public void testSetTimestamp() {
        setUp();
        Date newTimestamp = new Date();
        post.setTimestamp(newTimestamp);
        assertEquals(newTimestamp, post.getTimestamp());
    }

    @Test
    public void testAddComment() {
        setUp();
        Comment comment = new Comment(1, "A new comment.", new Date(), 2);
        post.addComment(comment);
        assertEquals(1, post.getComments().size());
    }

    @Test
    public void testAddReaction() {
        setUp();
        Reaction reaction = new Reaction(1, "like");
        post.addReaction(reaction);
        assertEquals(1, post.getReactions().size());
    }

    @Test
    public void testToString() {
        setUp();
        String expectedString = "Post{" +
                "postId=" + post.getPostId() +
                ", userId=1" +
                ", content='This is a post.'" +
                ", timestamp=" + post.getTimestamp() +
                ", comments=0" +
                ", reactions=0" +
                ", hashtags=0" +
                '}';
        assertEquals(expectedString, post.toString());
    }
}
