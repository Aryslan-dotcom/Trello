package Bootcamp6.Trello.repositories;

import Bootcamp6.Trello.entities.Comments;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
@Transactional
public interface CommentsRepositories extends JpaRepository<Comments,Long> {
}
