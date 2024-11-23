package com.w3villa.to_do_list.services;

import com.w3villa.to_do_list.models.Task;
import com.w3villa.to_do_list.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Indicates that this class is a service component in the Spring context
public class TaskServiceImpl {

    private final TaskRepository taskRepository;

    // Constructor for dependency injection of TaskRepository
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Retrieves all tasks from the repository.
     * return a list of all tasks
     */
    public List<Task> getAllTask() {
        return taskRepository.findAll();
    }

    /**
     * Retrieves a task by its ID.
     *
     * it takes parameter id the ID of the task
     * return the Task object if found
    * throws RuntimeException if the task is not found
     */
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    /**
     * Adds a new task to the repository.
     *
     * it takes parameter task the Task object to be added
     * return the saved Task object
     */
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * Deletes a task by its ID.
     *
     * it takes parameter id the ID of the task to be deleted
     */
    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    // comment this method to update an existing task
    /*
    public Task updateTask(Task updateTask) {
        Optional<Task> existingTask = getTaskById(updateTask.getId());

        if (existingTask.isPresent()){
            existingTask.get().setDescription(updateTask.getDescription());
            existingTask.get().setCompleted(updateTask.isCompleted());
            return taskRepository.save(existingTask);
        } else return null;
    }
    */
}
