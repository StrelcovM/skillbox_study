package ru.strelkov.todolist;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class MainController {
    @GetMapping(name = "/")
    public String index() {
        return new Date().toString() + " " + Math.random();
    }
}
