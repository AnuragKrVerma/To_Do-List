package com.w3villa.to_do_list.configuration;

import com.w3villa.to_do_list.services.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration // Indicates that this class provides Spring configuration
@EnableWebSecurity // Enables Spring Security’s web security support
public class SecurityConfig {

    private final CustomUserDetailService customUserDetailService;
    private final BCryptPasswordEncoder passwordEncoder;

    // Constructor for dependency injection of CustomUserDetailService and BCryptPasswordEncoder
    public SecurityConfig(CustomUserDetailService customUserDetailService, @Lazy BCryptPasswordEncoder passwordEncoder) {
        this.customUserDetailService = customUserDetailService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Configures the security filter chain for HTTP requests.
     * It specifies which endpoints are publicly accessible and sets up form-based login and logout.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/","/home", "/signup/**").permitAll() // Allow access to home and signup pages
                .anyRequest().authenticated() // Require authentication for all other requests
        ).formLogin(login -> login
                .loginPage("/login")
                .loginProcessingUrl("/authenticate")
                .defaultSuccessUrl("/home", true)
                .failureUrl("/login?error=true")
                .usernameParameter("email")
                .passwordParameter("password")
                .permitAll()
        ).logout(logout -> logout
                .logoutUrl("/logout") // URL to trigger logout
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()

        ).csrf(csrf -> csrf.disable());

        // Build and return the security filter chain
        return http.build();
    }

    /**
     * Provides a BCryptPasswordEncoder bean for password encoding.
     * This encoder is used to hash passwords securely.
     * Create and return a new BCryptPasswordEncoder instance
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Configures global authentication settings.
     * It sets the custom user details service and password encoder for authentication.
     */
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder);
    }
}
