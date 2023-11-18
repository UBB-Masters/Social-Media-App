package Persistence.SpringRepositories;

import Entities.Channel.GroupChat;
import Entities.Channel.GroupChatMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupChatRepository extends JpaRepository<GroupChat, Long> {
}