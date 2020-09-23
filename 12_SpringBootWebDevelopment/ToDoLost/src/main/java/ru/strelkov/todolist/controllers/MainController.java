package ru.strelkov.todolist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.strelkov.todolist.service.TaskService;

@Controller
public class MainController {
    private final TaskService taskService;

    @Autowired
    public MainController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping()
    public String list(Model model) {
        model.addAttribute("taskList", taskService.getAll());
        return "index";
    }
}
