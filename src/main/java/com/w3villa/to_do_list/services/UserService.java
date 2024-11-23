package com.w3villa.to_do_list.services;

import com.w3villa.to_do_list.models.User;
import com.w3villa.to_do_list.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service // Indicates that this class is a service component in the Spring context
public class UserService {
    private final UserRepository userRepository;

    // Constructor for dependency injection of UserRepository
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Adds a new user to the repository.
     *
     * it takes parameter user the User object to be added
     */
    public void addUser(User user) {
        userRepository.save(user);
    }

    /**
     * Retrieves a user by their email address.
     *
     * it takes parameter email the email address of the user
     * return an Optional containing the User if found, or empty if not
     */
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Retrieves a user by their ID.
     *
     * it takes parameter id the ID of the user
     * return the User object if found
     * throws RuntimeException if the user is not found
     */
    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }
}
