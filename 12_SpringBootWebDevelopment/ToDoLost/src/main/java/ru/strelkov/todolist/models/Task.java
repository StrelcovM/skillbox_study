package ru.strelkov.todolist.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "task")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "author_name")
    private String authorName;
    private String description;
    @Column(name = "date_of_adding")
    private final Date dateOfAdding;

    public Task() {
        dateOfAdding = new Date();
    }

    public Task(@NonNull String authorName, @NonNull String description) {
        this.authorName = authorName;
        this.description = description;
        dateOfAdding = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
