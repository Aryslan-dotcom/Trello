package Bootcamp6.Trello.services;

import Bootcamp6.Trello.entities.Folders;
import Bootcamp6.Trello.entities.TaskCategories;

import java.util.List;

public interface FolderService {
    Folders addFolder(Folders folder);
    List<Folders> getAllFolders();
    Folders getFolder(Long id);
    List<TaskCategories> notUsedCategories(List<TaskCategories> taskCategories,Folders folder);
    void  deleteFolder(Long id);
}
