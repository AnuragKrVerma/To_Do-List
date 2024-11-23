package com.w3villa.to_do_list.models;

import jakarta.persistence.*;
import lombok.*;

// Lombok annotations for boilerplate code
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString
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
}
