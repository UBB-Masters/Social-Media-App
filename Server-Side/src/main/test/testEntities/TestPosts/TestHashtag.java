package main.test.testEntities.TestPosts;

import Entities.Post.Hashtag;
import Entities.Post.Post;
import org.junit.jupiter.api.BeforeEach;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestHashtag {
    private Hashtag hashtag;
    private Post post1;
    private Post post2;

    @BeforeEach
    public void setUp() {
        hashtag = new Hashtag("test");
        post1 = new Post(1, "Post 1", new Date());
        post2 = new Post(2, "Post 2", new Date());
    }

    @Test
    public void testAddPost() {
        setUp();
        hashtag.addPost(post1);
        hashtag.addPost(post2);

        ArrayList<Post> posts = hashtag.getPosts();

        assertEquals(2, posts.size());
        assertEquals(post1, posts.get(0));
        assertEquals(post2, posts.get(1));
    }

    @Test
    public void testRemovePost() {
        setUp();
        hashtag.addPost(post1);
        hashtag.addPost(post2);

        hashtag.removePost(post1);

        ArrayList<Post> posts = hashtag.getPosts();

        assertEquals(1, posts.size());
        assertEquals(post2, posts.get(0));
    }

    @Test
    public void testGetTag() {
        setUp();
        String tag = hashtag.getTag();
        assertEquals("test", tag);
    }

    @Test
    public void testAddHashtagToPost() {
        setUp();
        // Add the hashtag to a post
        post1.addHashtag(hashtag);

        // Check if the hashtag has the post
        assertEquals(1, hashtag.getPosts().size());
        assertEquals(post1, hashtag.getPosts().get(0));
    }

    @Test
    public void testRemoveHashtagFromPost() {
        setUp();
        // Add the hashtag to two posts
        post1.addHashtag(hashtag);
        post2.addHashtag(hashtag);

        // Remove the hashtag from the first post
        post1.removeHashtag(hashtag);

        // Check if the hashtag still has the second post
        assertEquals(1, hashtag.getPosts().size());
        assertEquals(post2, hashtag.getPosts().get(0));
    }

    @Test
    public void testGetHashtagsForPost() {
        setUp();
        // Add the hashtag to a post
        post1.addHashtag(hashtag);

        // Get the list of hashtags for the post
        List<Hashtag> hashtags = post1.getHashtags();

        assertEquals(1, hashtags.size());
        assertEquals(hashtag, hashtags.get(0));
    }
}
