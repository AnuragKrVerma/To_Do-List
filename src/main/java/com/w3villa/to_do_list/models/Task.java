package com.w3villa.to_do_list.models;

import jakarta.persistence.*;
import lombok.*;

// Lombok annotations for boilerplate code
//@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
@Entity // Indicates that this class is a JPA entity
public class Task {

    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Title of the task
    private String title;

    // Indicates whether the task is completed or not
    private boolean completed;

    @ManyToOne // Defines a many-to-one relationship with the User entity
    // Specifies the foreign key column in the database
    @JoinColumn(name = "user_id")
    // The user associated with the task
    private User user;


    public Task() {

    }

    public Task(Long id, String title, boolean completed, User user) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                ", user=" + user +
                '}';
    }
}
