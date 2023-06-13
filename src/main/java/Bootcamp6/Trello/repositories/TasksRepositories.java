package Bootcamp6.Trello.repositories;

import Bootcamp6.Trello.entities.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface TasksRepositories extends JpaRepository<Task,Long> {
    Task findAllById(Long id);
}
