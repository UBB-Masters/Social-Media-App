package Reaction;
import Entities.Post.Post;
import Strategy.ReactionStrategy;

public class AngryStrategy implements ReactionStrategy {
    @Override
    public void react(Post post, long userId) {
        Reaction reaction = new Reaction(userId, "Angry");
        post.addReaction(reaction);
    }
}
