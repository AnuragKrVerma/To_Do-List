package com.w3villa.to_do_list;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // Indicates that this is a Spring Boot application
public class ToDoListApplication {


//  The main method that serves as the entry point for the Spring Boot application.
    public static void main(String[] args) {
        // Launches the application
        SpringApplication.run(ToDoListApplication.class, args);
    }
}
