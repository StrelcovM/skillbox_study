package ru.strelkov.todolist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.strelkov.todolist.models.Task;
import ru.strelkov.todolist.storage.TaskStorage;

import java.util.List;

@RestController
public class MainController {
    private final TaskStorage storage;

    @Autowired
    public MainController(TaskStorage storage) {
        this.storage = storage;
        storage.add(new Task("Max", "Task #1"));
        storage.add(new Task("Tin", "Task #2"));
        storage.add(new Task("Alex", "Task #3"));
        storage.add(new Task("Genri", "Task #5"));
    }

    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> list() {
        List<Task> taskList = storage.getAll();
        if (taskList.size() > 0)
            return new ResponseEntity<>(taskList, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable("id") int id) {
        Task task = storage.getById(id);
        if (task != null)
            return new ResponseEntity<>(task, HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/tasks")
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        storage.add(task);
        return new ResponseEntity<>(task, HttpStatus.CREATED);
    }

    @PostMapping("/tasks/{id}")
    public ResponseEntity<HttpStatus> notAllowed(@PathVariable("id") int id) {
        return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @DeleteMapping("/tasks")
    public ResponseEntity<HttpStatus> deleteAll() {
        storage.deleteAll();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Integer> deleteTask(@PathVariable(name = "id") int id) {
        if (storage.delete(id) != -1)
            return new ResponseEntity<>(id, HttpStatus.OK);
        return new ResponseEntity<>(id, HttpStatus.NOT_FOUND);
    }

    @PutMapping("/tasks")
    public ResponseEntity<HttpStatus> editMoreThanOne(@RequestBody List<Task> taskList) {
        for (Task task : taskList)
            if (storage.getById(task.getId()) == null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        taskList.forEach(storage::edit);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<HttpStatus> edit(@RequestBody Task task, @PathVariable("id") int id) {
        task.setId(id);
        if (storage.edit(task))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
