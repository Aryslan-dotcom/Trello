package Bootcamp6.Trello.services.impl;

import Bootcamp6.Trello.entities.TaskCategories;
import Bootcamp6.Trello.repositories.CategoriesRepositories;
import Bootcamp6.Trello.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoriesRepositories categoriesRepositories;
    @Override
    public TaskCategories addTaskCategory(TaskCategories taskCategory) {
        return categoriesRepositories.save(taskCategory);
    }

    @Override
    public List<TaskCategories> getAllTaskCategories() {
        return categoriesRepositories.findAll();
    }

    @Override
    public TaskCategories getTaskCategory(Long id) {
        return categoriesRepositories.findAllById(id);
    }

    @Override
    public void deleteTaskCategory(Long id) {
        categoriesRepositories.deleteById(id);
    }
}
