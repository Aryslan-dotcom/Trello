package Bootcamp6.Trello.services.impl;

import Bootcamp6.Trello.entities.Folders;
import Bootcamp6.Trello.entities.Task;
import Bootcamp6.Trello.repositories.TasksRepositories;
import Bootcamp6.Trello.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TasksRepositories tasksRepositories;
    @Override
    public Task addTask(Task task) {
        return tasksRepositories.save(task);
    }

    @Override
    public Task getTask(Long id) {
        return tasksRepositories.findAllById(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return tasksRepositories.findAll();
    }

    @Override
    public Task updateTask(Task task) {
         Task currentTask = getTask(task.getId());
         currentTask.setTitle(task.getTitle());
         currentTask.setDescription(task.getDescription());
         currentTask.setStatus(task.getStatus());
         return tasksRepositories.save(currentTask);
    }

    @Override
    public void deleteTask(Long id) {
            tasksRepositories.deleteById(id);
    }

    @Override
    public List<Task> getTasksbyFolder(List<Task> tasks, Folders folder) {
        List<Task> newTasks = new ArrayList<>();
        for (int i = 0; i<tasks.size();i++){
            if (tasks.get(i).getFolder().getId()==folder.getId()){
                newTasks.add(tasks.get(i));
            }
        }return newTasks;
    }
}
