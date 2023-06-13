package Bootcamp6.Trello.repositories;

import Bootcamp6.Trello.entities.Folders;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface FolderRepositories extends JpaRepository<Folders,Long> {
    Folders findAllById(Long id);
}
