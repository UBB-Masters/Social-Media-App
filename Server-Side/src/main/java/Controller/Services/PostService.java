package Controller.Services;

import Controller.PostRepository;
import Controller.UserRepository;
import Entities.Post.Comment;
import Entities.Post.Hashtag;
import Entities.Post.Post;
import Entities.Reaction.Reaction;
import Entities.User.User;
import Proxy.PostProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;


    private boolean newPostNotification;


    @Autowired

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public void createPost(User user, String content) {
        Post newPost = new Post(user, content, new Date());
        postRepository.save(newPost);
        List<User> users = getAllUsers();
        for (User u : users) {
            newPost.addObserver(u);
        }
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


//
//        newPost.notifyObservers(); // Notify all observers (users) about the new post
//
//        this.newPostNotification = true;
//
//
//    }

    public void createPostProxy(User user, PostProxy postProxy) {
        // Using PostProxy to get the content
        String content = postProxy.getContent();

        // Creating a new Post entity
        Post newPost = new Post(user, content, new Date());

        // Saving the new post to the repository
        postRepository.save(newPost);

        // Adding observers to the new post
        List<User> users = getAllUsers();
        for (User u : users) {
            newPost.addObserver(u);
        }

        // Notifying observers about the new post
        newPost.notifyObservers();

        this.newPostNotification = true;
    }





    public void addCommentToPost(Post post, Comment comment) {
        post.addComment(comment);
        postRepository.save(post);
    }

    public void reactToPost(Post post, Reaction reaction) {
        post.addReaction(reaction);
        postRepository.save(post);
    }

    public void addHashtagToPost(Post post, Hashtag hashtag) {
        post.addHashtag(hashtag);
        postRepository.save(post);
    }

    public void removeHashtagFromPost(Post post, Hashtag hashtag) {
        post.removeHashtag(hashtag);
        postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getPostsByUser(User user) {
        return postRepository.findByUser(user);
    }


    public Post getPostById(long postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public boolean hasNewPostNotification() {
        return newPostNotification;
    }

    public void clearNewPostNotification() {
        this.newPostNotification = false;
    }

}
