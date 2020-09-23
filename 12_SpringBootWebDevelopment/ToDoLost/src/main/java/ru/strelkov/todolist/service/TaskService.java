package ru.strelkov.todolist.service;

import ru.strelkov.todolist.models.Task;

import java.util.List;

public interface TaskService {
    Task getById(Integer id);

    List<Task> getAll();

    Task save(Task task);

    boolean deleteById(Integer id);

    void deleteAll();

    boolean edit(Task task);
}
