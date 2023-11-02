package Entities.Post;

import Entities.Misc.IDGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Post {
    private final long postId;
    private long userId;
    private String content;
    private Date timestamp;
    private final ArrayList<Comment> comments;
    private final ArrayList<Reaction> reactions;

    public Post(long userId, String content, Date timestamp) {
        this.postId = IDGenerator.generateID(Entities.Post.Post.class);
        this.userId = userId;
        this.content = content;
        this.timestamp = timestamp;
        this.comments = new ArrayList<>();
        this.reactions = new ArrayList<>();
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

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", timestamp=" + timestamp +
                ", comments=" + comments.size() +
                ", reactions=" + reactions.size() +
                '}';
    }
}