package Reaction;

import Strategy.ReactionStrategy;

public class ReactionFactory {

    public static ReactionStrategy createReactionStrategy(String reactionType) {
        ReactionStrategy reactionStrategy = null;

        switch (reactionType.toLowerCase()) {
            case "like":
                reactionStrategy = new LikeStrategy();
                break;
            case "love":
                reactionStrategy = new LoveStrategy();
                break;
            case "laugh":
                reactionStrategy = new LaughStrategy();
                break;
            case "sad":
                reactionStrategy = new SadStrategy();
                break;
            case "wow":
                reactionStrategy = new AmazedStrategy();
                break;
            case "angry":
                reactionStrategy = new AngryStrategy();
                break;
            default:
                System.out.println("Invalid reaction type");
                break;
        }

        return reactionStrategy;
    }
}