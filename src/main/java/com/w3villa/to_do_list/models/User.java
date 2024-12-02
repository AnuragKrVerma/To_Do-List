package com.w3villa.to_do_list.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

// Lombok annotations for boilerplate code
//@Getter @Setter @NoArgsConstructor  @AllArgsConstructor
@Entity // Indicates that this class is a JPA entity
public class User implements UserDetails { // Implements UserDetails for Spring Security
    /**
     *  Marks this field as the primary key
     *    Unique identifier for the user
     *    Specifies the generation strategy for the primary key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Ensures the email is unique in the database
    @Column(unique = true)
    private String email;

    private String password;

    // Defines a one-to-many relationship with the Task entity
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Task> tasks; // Set of tasks associated with the user

    /**
     * Returns the authorities granted to the user.
     * Currently returns an empty list as no roles are defined.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(); // No authorities assigned
    }

    /**
     * Returns the username used for authentication, which is the user's email.
     */
    @Override
    public String getUsername() {
        return this.email; // Return the email as the username
    }

    /**
     * Returns the password used for authentication.
     */
    @Override
    public String getPassword() {
        return this.password; // Return the password
    }

    /**
     * Indicates whether the user's account has expired.
     * Always returns true, meaning the account is not expired.
     */
    @Override
    public boolean isAccountNonExpired() {
        return true; // Account is not expired
    }

    /**
     * Indicates whether the user is locked.
     * Always returns true, meaning the account is not locked.
     */
    @Override
    public boolean isAccountNonLocked() {
        return true; // Account is not locked
    }

    /**
     * Indicates whether the user's credentials have expired.
     * Always returns true, meaning the credentials are not expired.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Credentials are not expired
    }

    /**
     * Indicates whether the user is enabled.
     * Always returns true, meaning the user is enabled.
     */
    @Override
    public boolean isEnabled() {
        return true; // User is enabled
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public User() {
    }

    public User(Long id, String name, String email, String password, Set<Task> tasks) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.tasks = tasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }


}
