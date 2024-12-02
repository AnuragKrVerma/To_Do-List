package com.w3villa.to_do_list.repositories;

import com.w3villa.to_do_list.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repository interface for managing User entities.
 * Extends JpaRepository to provide CRUD operations and query methods for User.
 */
public interface UserRepository extends JpaRepository<User, Long> {

   /**
    * Finds a User by their email address.
    * it takes parameter email the email address of the user
    * return Optional containing the User if found, or empty if not
    */
   Optional<User> findByEmail(String email);


}
