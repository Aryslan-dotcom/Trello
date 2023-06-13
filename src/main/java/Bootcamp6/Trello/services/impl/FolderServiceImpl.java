package Bootcamp6.Trello.services.impl;

import Bootcamp6.Trello.entities.Folders;
import Bootcamp6.Trello.entities.TaskCategories;
import Bootcamp6.Trello.repositories.FolderRepositories;
import Bootcamp6.Trello.services.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class FolderServiceImpl implements FolderService {
    @Autowired
    private FolderRepositories folderRepositories;
    @Override
    public Folders addFolder(Folders folder){
       return folderRepositories.save(folder);
    }

    @Override
    public List<Folders> getAllFolders() {
        return folderRepositories.findAll();
    }

    @Override
    public Folders getFolder(Long id){
        return folderRepositories.findAllById(id);
    }

    @Override
    public List<TaskCategories> notUsedCategories(List<TaskCategories> taskCategories, Folders folder) {
        List<TaskCategories> newCategories = new ArrayList<>();
        for (int i = 0; i < taskCategories.size(); i++) {
            boolean flag = false    ;
            for (int j = 0; j < folder.getCategories().size(); j++) {
                if (taskCategories.get(i).getId() == folder.getCategories().get(j).getId()) {
                    flag = true;
                    break;
                }
            }
            if (flag == false) {
                newCategories.add(taskCategories.get(i));
            }
        }
        return newCategories;
    }

    @Override
    public void deleteFolder(Long id) {
        folderRepositories.deleteById(id);
    }


}
