package Proxy;

import Entities.Post.Post;
import Entities.Reaction.Reaction;
import Entities.User.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PostProxy {
    private Post post;
    private boolean contentLoaded;
    private String cachedContent;

    @OneToMany(mappedBy = "postProxy")
    private List<Reaction> reactions;

    private Map<Long, String> accessControlMap; // Mapping of userId to access rights

    public PostProxy(long userId, String content, Date timestamp) {

        User user = new User();
        user.setUserID(userId);

        this.post = new Post(user, content, timestamp);
        this.contentLoaded = false;
        this.cachedContent = null;
        this.accessControlMap = new HashMap<>(); // Add access control configurations
        initializeAccessControl(); // Initialize access control configuration
    }



    public long getPostId() {
        return post.getPostId();
    }

    public long getUserId() {
        return post.getUserId();
    }

    public void setUserId(long userId) {
        post.setUserId(userId);
    }

    public String getContent() {
        if (!contentLoaded) {
            loadContent();
        }
        return cachedContent != null ? cachedContent : "No content available";
    }

    public void setContent(String content) {
        post.setContent(content);
    }

    public Date getTimestamp() {
        return post.getTimestamp();
    }

    public void setTimestamp(Date timestamp) {
        post.setTimestamp(timestamp);
    }

    private void loadContent() {
        if (!contentLoaded) {
            // Simulate loading content from a source (database, API, etc.)
            // For demonstration, setting the content here
            cachedContent = post.getContent(); // Get content from Post object
            contentLoaded = true;
        }
    }

    // Simulate access control - allow access based on user's role or permissions
    private void initializeAccessControl() {
        // For demonstration purposes, let's assume only certain users have access to the content
        accessControlMap.put(1L, "admin"); // User ID 1 has admin access
        accessControlMap.put(2L, "user"); // User ID 2 has regular user access
    }

    public boolean hasAccess(long userId) {
        if (accessControlMap.containsKey(userId)) {
            // Check access rights for the user
            String userRole = accessControlMap.get(userId);
            return userRole.equals("admin") || userRole.equals("user");
        }
        return false; // No access for unknown users
    }
}
