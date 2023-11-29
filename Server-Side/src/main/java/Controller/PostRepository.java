package Controller;

import Entities.Post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

//    @Query("SELECT p FROM Post p WHERE p.user.userID = :userId")
//    List<Post> findByUserId(Long userId);

}
