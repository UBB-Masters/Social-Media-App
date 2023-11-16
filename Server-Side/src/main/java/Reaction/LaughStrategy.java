package Reaction;

import Entities.Post.Post;
import Strategy.ReactionStrategy;

public class LaughStrategy implements ReactionStrategy {
    @Override
    public void react(Post post, long userId) {
        Reaction reaction = new Reaction(userId, "Laugh");
        post.addReaction(reaction);

    }
}
