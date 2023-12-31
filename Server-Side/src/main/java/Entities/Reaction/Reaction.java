package Entities.Reaction;

import Entities.Misc.IDGenerator;
import Proxy.PostProxy;
import jakarta.persistence.*;


@Entity
@Table(name = "reaction")
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reactionID")
    private long reactionId;

    @Column(name = "userID")
    private long userId;

    @Column(name = "reaction_type")
    private String reactionType;




    public Reaction(long userId, String reactionType) {
        this.reactionId = IDGenerator.generateID(Reaction.class);
        this.userId = userId;
        this.reactionType = reactionType;
    }

    public Reaction() {
        this.reactionId = IDGenerator.generateID(Reaction.class);
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