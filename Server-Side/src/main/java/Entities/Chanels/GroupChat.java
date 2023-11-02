package Entities.Chanels;

import Entities.Message.MessageFactory;
import Entities.Misc.IDGenerator;
import Entities.User.User;

import java.util.ArrayList;
import java.util.List;

public class GroupChat {
    private final long groupId;
    private String groupName;
    private final ArrayList<User> members;
    private final ArrayList<MessageFactory> messages;

    public GroupChat(String groupName) {
        this.groupId = IDGenerator.generateID(GroupChat.class);
        this.groupName = groupName;
        this.members = new ArrayList<>();
        this.messages = new ArrayList<>();
    }

    public long getGroupId() {
        return groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<User> getMembers() {
        return members;
    }

    public void addMember(User user) {
        members.add(user);
    }

    public void removeMember(User user) {
        members.remove(user);
    }

    public List<MessageFactory> getMessages() {
        return messages;
    }

    public void addMessage(MessageFactory message) {
        messages.add(message);
    }
}
