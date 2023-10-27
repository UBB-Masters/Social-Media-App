package Entities.Chanels.ChanelTypes;

import Entities.Chanels.ChanelFactory;
import Entities.User.User;

public class DirectChanel extends ChanelFactory {

    private User chanelMember1;
    private User chanelMember2;

    public User getChanelMember1() {
        return chanelMember1;
    }

    public void setChanelMember1(User chanelMember1) {
        this.chanelMember1 = chanelMember1;
    }

    public User getChanelMember2() {
        return chanelMember2;
    }

    public void setChanelMember2(User chanelMember2) {
        this.chanelMember2 = chanelMember2;
    }

    public DirectChanel() {
        super();
    }
}
