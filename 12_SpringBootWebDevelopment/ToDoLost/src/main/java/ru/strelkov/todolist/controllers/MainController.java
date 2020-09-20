package ru.strelkov.todolist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.strelkov.todolist.models.Task;
import ru.strelkov.todolist.storage.TaskStorage;

@Controller
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

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("taskList", storage.getAll());
        return "index";
    }

    @GetMapping("/add")
    public String getAddPage() {
        return "adding";
    }

    @PostMapping("/add")
    public String addNewTask(@RequestParam(name = "name") String name,
                             @RequestParam(name = "description") String description) {
        storage.add(new Task(name, description));
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable(name = "id") int id) {
        storage.delete(id);
        return "redirect:/";
    }

}
