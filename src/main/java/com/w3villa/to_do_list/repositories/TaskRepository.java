package com.w3villa.to_do_list.repositories;

import com.w3villa.to_do_list.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Task entities.
 * Extends JpaRepository to provide CRUD operations and query methods for Task.
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

}
