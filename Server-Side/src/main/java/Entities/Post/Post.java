package Entities.Post;

import Entities.Misc.IDGenerator;
import Entities.Reaction.Reaction;
import Entities.User.User;
import Observer.Observable;
import Observer.Observer;
import Strategy.ReactionStrategy;
import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "post")
public class Post implements Observable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postID")
    private Long postID;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Comment> comments;
    @Fetch(FetchMode.JOIN)
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Reaction> reactions;
    @Fetch(FetchMode.JOIN)
    @ManyToMany(fetch = FetchType.LAZY)
    private List<Hashtag> hashtags;
    @Transient
    private final List<Observer> observers = new ArrayList<>();
//    private long userId;
    private String content;
    private Date timestamp;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userID")
    private User user;

    @Transient
    private ReactionStrategy reactionStrategy;


    public User getUser() {

        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getUserId() {
        return user.getUserID();
    }

    public void setUserId(long userId) {
        if (this.user != null) {
            this.user.setUserID(userId);
        } else {
            throw new IllegalStateException("User object is null");
        }
    }
    // If you still need to get the user ID, you can do it through the User object



    public Post(User user, String content, Date timestamp) {
        this.postID = IDGenerator.generateID(Post.class);
        this.user = user;
        this.content = content;
        this.timestamp = timestamp;
        this.comments = new ArrayList<>();
        this.reactions = new ArrayList<>();
        this.hashtags = new ArrayList<>();
    }

    public Post() {
        this.postID = IDGenerator.generateID(Post.class);
    }

    public long getPostId() {
        return postID;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public List<Reaction> getReactions() {
        return reactions;
    }

    public void addReaction(Reaction reaction) {
        reactions.add(reaction);
    }

    // Add a hashtag to the list of hashtags associated with this post
    public void addHashtag(Hashtag hashtag) {
        hashtags.add(hashtag);
        hashtag.addPost(this);
    }

    // Remove a hashtag from the list of hashtags associated with this post
    public void removeHashtag(Hashtag hashtag) {
        hashtags.remove(hashtag);
        hashtag.removePost(this);
    }

    // Get the list of hashtags associated with this post
    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postID +
                ", userId=" + (user != null ? user.getUserID() : "null") +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                ", comments=" + comments.size() +
                ", reactions=" + reactions.size() +
                ", hashtags=" + hashtags.size() +
                '}';
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void setReactionStrategy(ReactionStrategy strategy) {
        this.reactionStrategy = strategy;
    }

    public void reactToPost(User user) {
        reactionStrategy.react(this, user.getUserID());
    }

}