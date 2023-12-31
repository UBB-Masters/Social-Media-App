package Entities.Channel;

import Entities.Misc.IDGenerator;
import jakarta.persistence.*;


@Entity
@Table(name = "groupchat")
public class GroupChat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long groupId;

    @Column(name = "groupName")
    private String groupName;

//    @OneToMany(mappedBy = "receiver")
//    private List<MessageFactory> receivedMessages = new ArrayList<>();

//    public List<MessageFactory> getReceivedMessages() {
//        return receivedMessages;
//    }
//
//    public void setReceivedMessages(List<MessageFactory> receivedMessages) {
//        this.receivedMessages = receivedMessages;
//    }

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
