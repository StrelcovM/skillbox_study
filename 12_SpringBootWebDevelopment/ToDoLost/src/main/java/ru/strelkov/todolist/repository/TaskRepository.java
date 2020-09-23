package ru.strelkov.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.strelkov.todolist.models.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
}
