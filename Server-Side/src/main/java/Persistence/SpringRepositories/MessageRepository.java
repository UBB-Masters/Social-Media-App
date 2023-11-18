package Persistence.SpringRepositories;

import Entities.Message.MessageFactory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepository extends JpaRepository<MessageFactory, Long> {
}