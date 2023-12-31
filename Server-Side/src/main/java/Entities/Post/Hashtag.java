package Entities.Post;

import Entities.Misc.IDGenerator;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


import java.util.ArrayList;

@Entity
@Table(name = "hashtag")
public class Hashtag {
    @Id
    private final long hashtagID;
    private String tag;
    @ManyToMany
    private ArrayList<Post> posts;

    public Hashtag(String tag) {
        hashtagID = IDGenerator.generateID(Hashtag.class);
        this.tag = tag;
        this.posts = new ArrayList<>();
    }

    public Hashtag() {
        this.tag = "tag";
        this.hashtagID = IDGenerator.generateID(Hashtag.class);
    }

    public long getHashtagID() {
        return hashtagID;
    }

    // Add a post to the list of posts associated with this hashtag
    public void addPost(Post post) {
        posts.add(post);
    }

    // Remove a post from the list of posts associated with this hashtag
    public void removePost(Post post) {
        posts.remove(post);
    }

    // Get the list of posts associated with this hashtag
    public ArrayList<Post> getPosts() {
        return posts;
    }

    // Get the hashtag text
    public String getTag() {
        return tag;
    }

    @Override
    public String toString() {
        return "Hashtag{" +
                "hashtagID=" + hashtagID +
                ", tag='" + tag + '\'' +
                ", posts=" + posts +
                '}';
    }
}
