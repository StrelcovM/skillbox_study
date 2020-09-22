package ru.strelkov.todolist.storage;

import org.springframework.stereotype.Component;
import ru.strelkov.todolist.models.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
public class TaskStorage {
    private static int currentId = 1;
    private static final HashMap<Integer, Task> taskHashMap = new HashMap<>();

    public List<Task> getAll() {
        return new ArrayList<>(taskHashMap.values());
    }

    public Task getById(int id) {
        if (taskHashMap.containsKey(id))
            return taskHashMap.get(id);
        return null;
    }
    public void add(Task task) {
        int id = currentId++;
        task.setId(id);
        taskHashMap.put(id, task);
    }

    public int delete(int id) {
        if (taskHashMap.containsKey(id)) {
            taskHashMap.remove(id);
            return id;
        }
        return -1;
    }

    public void deleteAll() {
        taskHashMap.clear();
    }

    public boolean edit(Task task) {
        if (taskHashMap.containsKey(task.getId())) {
            taskHashMap.put(task.getId(), task);
            return true;
        }
        return false;
    }
}
