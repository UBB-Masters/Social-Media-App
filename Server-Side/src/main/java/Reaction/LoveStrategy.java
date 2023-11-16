package Reaction;

import Entities.Post.Post;
import Strategy.ReactionStrategy;

public class LoveStrategy implements ReactionStrategy {
    @Override
    public void react(Post post, long userId) {
        Reaction reaction = new Reaction(userId, "Love");
        post.addReaction(reaction);
    }
}
