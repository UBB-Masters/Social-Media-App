package Entities.User;

import Entities.Misc.IDGenerator;

public class Story {
    private final long ID;
    private final User user;
    private User.Visibility visibility;

    public Story(User user, User.Visibility visibility) {
        this.ID = IDGenerator.generateID(Story.class);
        this.user = user;
        this.visibility = visibility;
    }

    public long getID() {
        return ID;
    }

    public void setVisibility(User.Visibility visibility) {
        this.visibility = visibility;
    }

    public User getUser() {
        return user;
    }

    public User.Visibility getVisibility() {
        return visibility;
    }
}
