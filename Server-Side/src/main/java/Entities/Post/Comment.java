package Entities.Post;

import Entities.Misc.IDGenerator;

import java.util.Date;

public class Comment {
    private final long commentId;
    private long postId;
    private String content;
    private Date timestamp;
    private long userId;

    public Comment(long postId, String content, Date timestamp, long userId) {
        commentId = IDGenerator.generateID(Comment.class);
        this.postId = postId;
        this.content = content;
        this.timestamp = timestamp;
        this.userId = userId;
    }

    public long getCommentId() {
        return commentId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
