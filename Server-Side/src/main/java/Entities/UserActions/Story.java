package Entities.UserActions;

import Entities.Misc.IDGenerator;
import Entities.User.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "story")
public class Story {
    @Id
    private final long ID;
    @ManyToOne
    private final User user;
    private User.Visibility visibility;

    public Story(long ID,User user, User.Visibility visibility) {
        this.ID = ID;
        this.user = user;
        this.visibility = visibility;
    }

    public Story(User user, User.Visibility visibility) {
        this.ID = IDGenerator.generateID(Story.class);
        this.user = user;
        this.visibility = visibility;
    }

    public Story() {
        this.user = new User();
        this.ID = IDGenerator.generateID(Story.class);
    }

    public long getID() {
        return ID;
    }

    public User getUser() {
        return user;
    }

    public User.Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(User.Visibility visibility) {
        this.visibility = visibility;
    }
}
