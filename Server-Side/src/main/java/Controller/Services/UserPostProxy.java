package Controller.Services;

import Entities.User.User;
import Proxy.PostProxy;

public class UserPostProxy {
    private User user;
    private PostProxy postProxy;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PostProxy getPostProxy() {
        return postProxy;
    }

    public void setPostProxy(PostProxy postProxy) {
        this.postProxy = postProxy;
    }
}
