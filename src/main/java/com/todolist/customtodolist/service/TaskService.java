package com.todolist.customtodolist.service;

import com.todolist.customtodolist.domainModel.Task;
import com.todolist.customtodolist.exception.TaskNotFoundException;
import com.todolist.customtodolist.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    private final TaskRepo taskRepo;

    @Autowired
    public TaskService(TaskRepo taskRepo) {
        this.taskRepo = taskRepo;
    }

    public Task addTask(Task task) {
        task.setIsActive(true);
        task.setIsComplete(false);
        return taskRepo.save(task);
    }

    public List<Task> findAllTasks() {
        return taskRepo.findAll();
    }

    public List<Task> findAllIsActiveTasks() {
        //return taskRepo.findAllIsActive();
        return taskRepo.findByIsActiveIsTrue();
    }

    public List<Task> findAllIsActiveByIsCompleteTasks(Boolean isComplete) {
        return taskRepo.findByIsActiveIsTrueAndIsComplete(isComplete);
    }

    public Task findTaskById(Long id) {
        return taskRepo.findTaskById(id).orElseThrow(() -> new TaskNotFoundException("User not found."));
    }

    public Task updateTask(Task task) {
        return taskRepo.save(task);
    }

    public List<Task> clearAllCompleted() {
        List<Task> completedTasks = taskRepo.findByIsActiveIsTrueAndIsComplete(true);
        completedTasks.forEach(task -> {
            task.setIsActive(false);
        });
        return taskRepo.saveAll(completedTasks);
    }

    public List<Task> markAllCompleted() {
        List<Task> incompleteTasks = taskRepo.findByIsActiveIsTrueAndIsComplete(false);
        incompleteTasks.forEach(task -> {
            task.setIsComplete(true);
        });
        return taskRepo.saveAll(incompleteTasks);
    }

    //making this function for the formality of it. but i do not intend to use it.
    public void deleteTask(Long id) {
        taskRepo.deleteTaskById(id);
    }
}
