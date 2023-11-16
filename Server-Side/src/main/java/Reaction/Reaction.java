package Reaction;

import Entities.Misc.IDGenerator;

public class Reaction {
    private final long reactionId;
    private long userId;
    private String reactionType;

    public Reaction(long userId, String reactionType) {
        this.reactionId = IDGenerator.generateID(Reaction.class);
        this.userId = userId;
        this.reactionType = reactionType;
    }

    public long getReactionId() {
        return reactionId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getReactionType() {
        return reactionType;
    }

    public void setReactionType(String reactionType) {
        this.reactionType = reactionType;
    }

    @Override
    public String toString() {
        return "Reaction{" +
                "reactionId=" + reactionId +
                ", userId=" + userId +
                ", reactionType='" + reactionType + '\'' +
                '}';
    }
}