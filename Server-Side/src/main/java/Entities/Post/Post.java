package Entities.Post;

import Entities.Misc.IDGenerator;
import Entities.User.User;
import Observer.Observable;
import Observer.Observer;
import Reaction.Reaction;
import Strategy.ReactionStrategy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post implements Observable {
    private final long postId;
    private long userId;
    private String content;
    private Date timestamp;
    private final ArrayList<Comment> comments;
    private final ArrayList<Reaction> reactions;
    private final List<Hashtag> hashtags;

    private final List<Observer> observers = new ArrayList<>();

    private ReactionStrategy reactionStrategy; // Add a reference to the strategy


    public Post(long userId, String content, Date timestamp) {
        this.postId = IDGenerator.generateID(Post.class);
        this.userId = userId;
        this.content = content;
        this.timestamp = timestamp;
        this.comments = new ArrayList<>();
        this.reactions = new ArrayList<>();
        this.hashtags = new ArrayList<>();
    }

    public long getPostId() {
        return postId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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
                "postId=" + postId +
                ", userId=" + userId +
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
        for(Observer observer : observers){
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
        reactionStrategy.react(this, user.getID());
    }

}