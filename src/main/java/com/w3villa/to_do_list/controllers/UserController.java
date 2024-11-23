package com.w3villa.to_do_list.controllers;

import com.w3villa.to_do_list.models.User;
import com.w3villa.to_do_list.services.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller // Indicates that this class is a Spring MVC controller
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    // Constructor for dependency injection of UserService and BCryptPasswordEncoder
    public UserController(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Displays the login page.
     */
    @GetMapping("/login")
    public String login(){
        return "login"; // Return the login view
    }

    /**
     * Displays the signup page and adds a new User object to the model.
     */
    @GetMapping("/signup")
    public String signup(Model model){
        model.addAttribute("user", new User());
        return "signup"; // Return the signup view
    }

    /**
     * Handles the signup form submission, encodes the password, and adds the user.
     */
    @PostMapping("/signup")
    public String signupPost(@ModelAttribute User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.addUser(user);
        return "redirect:/login"; // Redirect to the login page
    }
}
