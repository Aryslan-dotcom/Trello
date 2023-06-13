package Bootcamp6.Trello.controllers;

import Bootcamp6.Trello.entities.Comments;
import Bootcamp6.Trello.entities.Folders;

import Bootcamp6.Trello.entities.Task;
import Bootcamp6.Trello.entities.TaskCategories;
import Bootcamp6.Trello.services.CategoryService;
import Bootcamp6.Trello.services.CommentService;
import Bootcamp6.Trello.services.FolderService;
import Bootcamp6.Trello.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/trello")
public class TrelloController {
    @Autowired
    private FolderService folderService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/home")
    public String openHome(Model model) {
        model.addAttribute("folders", folderService.getAllFolders());
        return "home";
    }

    @PostMapping(value = "/delete_folder")
    public String deleteFolder(@RequestParam(name = "delete_folder_id") Long folder_id) {
        folderService.deleteFolder(folder_id);

        return "redirect:/trello/home";
    }

    @PostMapping("/add-folder")
    public String addFolder(@RequestParam(name = "name") String name) {
        String redirect = "/trello/add-folder?error";
        Folders folder = Folders.builder()
                .name(name)
                .build();
        if (folderService.addFolder(folder) != null) {
            redirect = "/trello/home";
        }
        return "redirect:" + redirect;
    }

    @GetMapping(value = "/details/{id}")
    public String openDetails(@PathVariable("id") Long id,
                              Model model) {
        Folders folder = folderService.getFolder(id);
        model.addAttribute("folder", folder);
        List<Task> tasks = taskService.getAllTasks();
        List<Task> newTasks = taskService.getTasksbyFolder(tasks, folder);
        model.addAttribute("tasks", newTasks);
        List<TaskCategories> taskCategories = categoryService.getAllTaskCategories();
        List<TaskCategories> newCategories = folderService.notUsedCategories(taskCategories, folder);
        model.addAttribute("taskCategoriesList", newCategories);
        return "details";
    }
    @PostMapping(value = "/assign_category")
    public String assignCategoryPost(@RequestParam(name = "category_id") Long category_id,
                                     @RequestParam(name = "folder_id") Long folder_id) {
        String redirect = "/trello/assign_category?error";
        Folders folder = folderService.getFolder(folder_id);
        TaskCategories taskCategory = categoryService.getTaskCategory(category_id);
        folder.getCategories().add(taskCategory);
        if (folderService.addFolder(folder) != null) {
            redirect = "/trello/details/" + folder.getId();
        }
        return "redirect:" + redirect;
    }


    @GetMapping(value = "/add_categories")
    public String openAddCategory(Model model) {
        List<TaskCategories> taskCategories = categoryService.getAllTaskCategories();
        model.addAttribute("taskCategories", taskCategories);
        return "add-categories";
    }

    @PostMapping(value = "/add_categories")
    public String addCategory(@RequestParam(name = "category_name") String name) {
        String redirect = "/trello/add-categories?error";
        TaskCategories taskCategory = TaskCategories.builder()
                .name(name)
                .build();
        if (categoryService.addTaskCategory(taskCategory) != null) {
            redirect = "/trello/add_categories";
        }
        return "redirect:" + redirect;
    }

    @PostMapping(value = "/delete_categories")
    public String deleteCategory(@RequestParam(name = "category_id") Long id) {
        categoryService.deleteTaskCategory(id);
        return "redirect:add_categories";

    }

    @PostMapping(value = "/delete_category_from_folder_details")
    public String deleteCategoryFromFolderDetails(@RequestParam(name = "category_id") Long category_id,
                                                  @RequestParam(name = "folder_id") Long folder_id) {
        String redirect = "/trello/delete_category_from_folder_details?error";

        Folders folder = folderService.getFolder(folder_id);
        int index = -1;
        for (int i = 0; i < folder.getCategories().size(); i++) {
            if (folder.getCategories().get(i).getId() == category_id) {
                index = i;
                break;
            }
        }
        folder.getCategories().remove(index);
        if (folderService.addFolder(folder) != null) {
            redirect = "/trello/details/" + folder.getId();
        }
        return "redirect:" + redirect;
    }

    @PostMapping(value = "/task_details")
    public String addTaskCategories(@RequestParam(name = "task_title") String title,
                                    @RequestParam(name = "task_description") String description,
                                    @RequestParam(name = "task_id") Long task_id,
                                    @RequestParam(name = "task_status") int status_id) {


        Task task = taskService.getTask(task_id);
        task.setTitle(title);
        task.setDescription(description);
        task.setStatus(status_id);
        taskService.addTask(task);
        return "redirect:/trello/details/" + task.getFolder().getId();
    }

    @PostMapping(value = "/add_task")
    public String addNewTask(@RequestParam(name = "title") String title,
                             @RequestParam(name = "description") String description,
                             @RequestParam(name = "folder_id") Long folder_id) {
        String redirect = "/trello/details?error";
        if (title != null && description != null) {
            Folders folder = folderService.getFolder(folder_id);
            Task task = Task.builder()
                    .title(title)
                    .description(description)
                    .status(1)
                    .folder(folder)
                    .build();

            if (taskService.addTask(task) != null) {
                redirect = "/trello/details/" + folder_id;
            }
        }
        return "redirect:" + redirect;
    }

    @PostMapping(value = "/delete_task")
    public String deleteTask(@RequestParam(name = "delete_task", required = false) String delete_task_id,
                             @RequestParam(name = "folder_delete_id") Long folder_id,
                             @RequestParam(name = "task_delete_id") Long task_id) {
        if (delete_task_id!=null) {
            taskService.deleteTask(task_id);
            return "redirect:/trello/details/" + folder_id;
        }
        return "redirect:/trello/details/" + folder_id;
    }

    @GetMapping(value = "/comment/{id}")
    public String openComments(@PathVariable("id")Long id,
                               Model model){
        Task task = taskService.getTask(id);
        model.addAttribute("comments",commentService.getCommentsTask(task));
        return "comments";
    }
    @PostMapping(value = "/add_comment")
    public String addComment(@RequestParam(name = "comment")String comment,
                             @RequestParam(name = "task_id")Long task_id){
        Task task = taskService.getTask(task_id);

        Comments comments = Comments.builder()
                .comment(comment)
                .task(task)
                .build();
        commentService.addComment(comments);
        return "redirect:/trello/comment/"+ task_id;
    }
}
