package com.w3villa.to_do_list.controllers;

import com.w3villa.to_do_list.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@ControllerAdvice // Indicates that this class provides global model attributes for controllers
public class UserExistController {

    private final UserService userService;

    // Constructor for dependency injection of UserService
    public UserExistController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Adds the logged-in user's information to the model for all controllers.
     * If the user is authenticated, it retrieves the user's name and adds it to the model.
     */
    @ModelAttribute
    public void addLoggedInUserToModule(Model model, Authentication authentication) {
        if (authentication == null) {
            return; // Exit if there is no authentication
        }
        // Get the authenticated user's email
        Optional<String> email = authentication.getName().describeConstable();
        // Add a flag indicating if the user is logged in
        model.addAttribute("loggedInUser", email.isPresent());

        // Add the logged-in user's name to the model
        model.addAttribute("loggedinUserName", userService.getUserByEmail(email.get()).get().getName());
    }
}
