package Bootcamp6.Trello.repositories;

import Bootcamp6.Trello.entities.TaskCategories;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface CategoriesRepositories extends JpaRepository<TaskCategories,Long> {
    TaskCategories findAllById(Long id);
}
