package Reaction;

import Entities.Post.Post;
import Strategy.ReactionStrategy;

public class AmazedStrategy implements ReactionStrategy {
    @Override
    public void react(Post post, long userId) {
        Reaction reaction = new Reaction(userId, "Wow");
        post.addReaction(reaction);
    }
}
