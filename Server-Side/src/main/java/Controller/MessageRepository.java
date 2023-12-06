package Controller;

import Entities.Message.MessageFactory;
import Entities.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageFactory, Long> {
    List<MessageFactory> findBySenderID_UserID(Long userID);
}