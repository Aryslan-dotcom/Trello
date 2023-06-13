package Bootcamp6.Trello.services;

import Bootcamp6.Trello.entities.Folders;
import Bootcamp6.Trello.entities.Task;

import java.util.List;

public interface TaskService {
    Task addTask(Task task);
    Task getTask(Long id);
    List<Task> getAllTasks();
    Task updateTask(Task task);
    void deleteTask(Long id);
    List<Task> getTasksbyFolder(List<Task> tasks, Folders folder);
}
