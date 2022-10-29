package com.todolist.customtodolist;

import com.todolist.customtodolist.domainModel.Task;
import com.todolist.customtodolist.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskResource {
    private final TaskService taskService;

    public TaskResource(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Task>> getAllTasks () {
        List<Task> tasks = taskService.findAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/getAllActive")
    public ResponseEntity<List<Task>> getAllIsActiveTasks () {
        List<Task> tasks = taskService.findAllIsActiveTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/getAllActive/{isComplete}")
    public ResponseEntity<List<Task>> getAllIsActiveByIsCompleteTasks (@PathVariable("isComplete") Boolean isComplete) {
        List<Task> tasks = taskService.findAllIsActiveByIsCompleteTasks(isComplete);
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Task> getTaskById (@PathVariable("id") Long id) {
        Task task = taskService.findTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @PostMapping("/addTask")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        Task newTask = taskService.addTask(task);
        return new ResponseEntity<>(newTask, HttpStatus.CREATED);
    }

    @PutMapping("/updateTask")
    public ResponseEntity<Task> updateTask(@RequestBody Task task) {
        Task updateTask = taskService.updateTask(task);
        return new ResponseEntity<>(updateTask, HttpStatus.OK);
    }

    @PutMapping("/clearAllCompleted")
    public ResponseEntity<List<Task>> clearAllCompleted() {
        List<Task> clearTasks = taskService.clearAllCompleted();
        return new ResponseEntity<>(clearTasks, HttpStatus.OK);
    }

    @PutMapping("/markAllCompleted")
    public ResponseEntity<List<Task>> markAllCompleted() {
        List<Task> markTasks = taskService.markAllCompleted();
        return new ResponseEntity<>(markTasks, HttpStatus.OK);
    }

    //making this endpoint for the formality of it. but i do not intend to use it.
    @DeleteMapping("/deleteTask/{id}")
    @Transactional
    public ResponseEntity<?> deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
