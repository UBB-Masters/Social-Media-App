package Entities.Reaction;

import Entities.Post.Post;
import Strategy.ReactionStrategy;

public class SadStrategy implements ReactionStrategy {
    @Override
    public void react(Post post, long userId) {
        Reaction reaction = new Reaction(userId, "Sad");
        post.addReaction(reaction);
    }
}
