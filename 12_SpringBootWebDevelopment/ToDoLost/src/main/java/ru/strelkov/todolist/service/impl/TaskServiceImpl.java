package ru.strelkov.todolist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.strelkov.todolist.models.Task;
import ru.strelkov.todolist.repository.TaskRepository;
import ru.strelkov.todolist.service.TaskService;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task getById(Integer id) {
        if (taskRepository.existsById(id))
            return taskRepository.getOne(id);
        return null;
    }

    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public boolean deleteById(Integer id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
            return true;
        } else
            return false;
    }

    @Override
    public void deleteAll() {
        taskRepository.deleteAll();
    }

    @Override
    public boolean edit(Task task) {
        if (taskRepository.existsById(task.getId())) {
            taskRepository.save(task);
            return true;
        } else
            return false;
    }
}
