package Bootcamp6.Trello.services;

import Bootcamp6.Trello.entities.TaskCategories;

import java.util.List;

public interface CategoryService {
    TaskCategories addTaskCategory(TaskCategories taskCategory);
    List<TaskCategories> getAllTaskCategories();
    TaskCategories getTaskCategory(Long id);
    void deleteTaskCategory(Long id);

}
