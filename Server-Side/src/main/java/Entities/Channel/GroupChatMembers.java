package Entities.Channel;
import Entities.User.User;

import javax.persistence.*;

@Entity
@Table
public class GroupChatMembers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToOne
    private GroupChat groupChat;

    public GroupChatMembers() {
        this.groupChat = new GroupChat();
        this.user = new User();
    }

    public GroupChatMembers(User user, GroupChat groupChat) {
        this.user = user;
        this.groupChat = groupChat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public GroupChat getGroupChat() {
        return groupChat;
    }

    public void setGroupChat(GroupChat groupChat) {
        this.groupChat = groupChat;
    }
}
