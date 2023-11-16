package Entities.Reaction;

import Entities.Post.Post;
import Strategy.ReactionStrategy;

public class LikeStrategy implements ReactionStrategy {
    @Override
    public void react(Post post, long userId) {
        Reaction reaction = new Reaction(userId, "Like");
        post.addReaction(reaction);
    }
}
