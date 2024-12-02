package com.w3villa.to_do_list.controllers;

import com.w3villa.to_do_list.models.Task;
import com.w3villa.to_do_list.models.User;
import com.w3villa.to_do_list.services.CustomUserDetailService;
import com.w3villa.to_do_list.services.TaskServiceImpl;
import com.w3villa.to_do_list.services.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller // Indicates that this class is a Spring MVC controller
public class TaskController {

    private final TaskServiceImpl taskServiceImpl; // Service for managing tasks
    private final UserService userService;
    private final CustomUserDetailService customUserDetailService;

    // Constructor for dependency injection of TaskServiceImpl
    public TaskController(TaskServiceImpl taskServiceImpl, UserService userService, CustomUserDetailService customUserDetailService) {
        this.taskServiceImpl = taskServiceImpl;
        this.userService = userService;
        this.customUserDetailService = customUserDetailService;
    }

    /**
     * Redirects the root URL to the home page.
     */
    @GetMapping("/")
    public String redirectToHome() {
        return "redirect:/home"; // Redirect to the home page
    }

    /**
     * Displays the home page.
     */
    @GetMapping("/home")
    public String home() {
        return "home"; // Return the home view
    }

    /**
     * Retrieves all tasks and adds them to the model for display.
     * Displays all tasks
     */

    /*@GetMapping("/tasks")
    public String getTask(Model model){
        List<Task> tasks = taskServiceImpl.getAllTask();
        model.addAttribute("tasks", tasks);

        return "tasks"; // Return the tasks view
    }
    */

    /**
     * Adds a new task with the specified title.
     * Create a new task
     * Set the title of the task
     * Set the completion status of the task to false
     */
    @PostMapping("/tasks")
    public String addTask(@RequestParam String title) {
        Task task = new Task();
        task.setTitle(title);
        task.setCompleted(false);


        // Fetch the user and associate it with the task
        User user = customUserDetailService.getExistUser() ;
        if (user != null) {
            task.setUser(user); // Set the user for the task
        }

        taskServiceImpl.addTask(task); // Save the task
        return "redirect:/tasks"; // Redirect to the tasks page
    }

    /**
     * Deletes a task by its ID.
     */
    @GetMapping("/tasks/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskServiceImpl.deleteTaskById(id);
        return "redirect:/tasks"; // Redirect to the tasks page
    }

    @GetMapping("/tasks")
    public String getTasksByUser(Model model) {
        User user = customUserDetailService.getExistUser();
        List<Task> tasksByUser = taskServiceImpl.getTasksByUser(user);

        model.addAttribute("tasksByUser", tasksByUser);

        return "tasks"; // Return the tasks view

    }

    /**
     * Toggles the completion status of a task by its ID.
     */

    @GetMapping("/tasks/{id}/complete")
    public String updateTask(@PathVariable Long id){
        Task task = taskServiceImpl.getTaskById(id);
        task.setCompleted(!task.isCompleted());
        taskServiceImpl.addTask(task);
        return "redirect:/tasks"; // Redirect to the tasks page
    }

}
