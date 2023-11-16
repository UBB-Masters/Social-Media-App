package Strategy;

import Entities.Post.Post;

public interface ReactionStrategy {

    void react(Post post, long userId);
}
