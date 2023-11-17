package Entities.Channel;

import Entities.Misc.IDGenerator;

import javax.persistence.*;

@Entity
@Table(name = "groupchat")
public class GroupChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long groupId;

    private String groupName;

    public GroupChat() {
        this.groupId = IDGenerator.generateID(GroupChat.class);
    }

    public GroupChat(String groupName) {
        this.groupName = groupName;
        this.groupId = IDGenerator.generateID(GroupChat.class);
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
