package test.TestPosts;

import Entities.Post.Comment;
import Entities.Post.Post;
import Entities.Reaction.Reaction;
import Entities.User.User;
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
        User user = new User("username", "password", new Date(), "email@example.com", User.Visibility.PUBLIC);

        post = new Post(user, "Post 1", new Date());

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


}
