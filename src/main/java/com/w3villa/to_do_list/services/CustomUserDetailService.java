package com.w3villa.to_do_list.services;

import com.w3villa.to_do_list.models.User;
import com.w3villa.to_do_list.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service // Indicates that this class is a service component in the Spring context
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    // Constructor for dependency injection of UserRepository
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads a user by their username (email in this case).
     *
     * it takes parameter username the email of the user
     * return UserDetails object containing user information
     * throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch the user by email and throw an exception if not found
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with this email"));

        // Return a UserDetails object with the user's email, password and role as empty
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Collections.emptyList());
    }
}
