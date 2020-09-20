package ru.strelkov.todolist.models;

import org.springframework.lang.NonNull;

import java.util.Date;

public class Task {
    private int id;
    private String authorName;
    private String description;
    private final Date dateOfAdding;

    public Task() {
        dateOfAdding = new Date();
    }

    public Task(@NonNull String authorName, @NonNull String description) {
        this.authorName = authorName;
        this.description = description;
        dateOfAdding = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateOfAdding() {
        return dateOfAdding;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", authorName='" + authorName + '\'' +
                ", description='" + description + '\'' +
                ", dateOfAdding=" + dateOfAdding +
                '}';
    }
}
