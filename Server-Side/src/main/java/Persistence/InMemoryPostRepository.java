package Persistence;

import Entities.Post.Post;

import java.util.ArrayList;
import java.util.List;

public class InMemoryPostRepository {
    private static InMemoryPostRepository instance = null;
    private final List<Post> posts;

    private InMemoryPostRepository() {
        this.posts = new ArrayList<>();
    }

    public static InMemoryPostRepository getInstance() {
        if (instance == null) {
            instance = new InMemoryPostRepository();
        }
        return instance;
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    public void deletePost(Post post) {
        posts.remove(post);
    }

    public List<Post> getAllPosts() {
        return posts;
    }

    public Post getPostById(long postId) {
        for (Post post : posts) {
            if (post.getPostId() == postId) {
                return post;
            }
        }
        return null;
    }

    public List<Post> getPostsByUserId(long userId) {
        List<Post> userPosts = new ArrayList<>();
        for (Post post : posts) {
            if (post.getUserId() == userId) {
                userPosts.add(post);
            }
        }
        return userPosts;
    }

    public void updatePost(Post updatedPost) {
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getPostId() == updatedPost.getPostId()) {
                posts.set(i, updatedPost);
                break;
            }
        }
    }
}
