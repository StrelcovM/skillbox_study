package ru.strelkov.todolist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.strelkov.todolist.models.Task;
import ru.strelkov.todolist.service.TaskService;

import java.util.List;

@RestController
public class MainController {
    private final TaskService taskService;

    @Autowired
    public MainController(TaskService taskService) {
        this.taskService = taskService;
        taskService.save(new Task("Max", "Task #1"));
        taskService.save(new Task("Tin", "Task #2"));
        taskService.save(new Task("Alex", "Task #3"));
        taskService.save(new Task("Genri", "Task #5"));
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> list() {
        List<Task> taskList = taskService.getAll();
        if (taskList.size() > 0)
            return new ResponseEntity<>(taskList, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") int id) {
        Task task = taskService.getById(id);
        if (task != null)
            return new ResponseEntity<>(task, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        return new ResponseEntity<>(taskService.save(task), HttpStatus.CREATED);
    }

    @PostMapping("/tasks/{id}")
    public ResponseEntity<HttpStatus> notAllowed(@PathVariable("id") int id) {
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @DeleteMapping("/tasks")
    public ResponseEntity<HttpStatus> deleteAll() {
        taskService.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Integer> deleteTask(@PathVariable(name = "id") int id) {
        if (taskService.deleteById(id))
            return new ResponseEntity<>(id, HttpStatus.OK);
        return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/tasks")
    public ResponseEntity<HttpStatus> editMoreThanOne(@RequestBody List<Task> taskList) {
        for (Task task : taskList)
            if (taskService.getById(task.getId()) == null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        taskList.forEach(taskService::edit);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<HttpStatus> edit(@RequestBody Task task, @PathVariable("id") int id) {
        task.setId(id);
        if (taskService.edit(task))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
