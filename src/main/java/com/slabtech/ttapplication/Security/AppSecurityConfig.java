package com.slabtech.ttapplication.Security;

import com.slabtech.ttapplication.Service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AppSecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder();}

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService){
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService); // set the custom user details service
        auth.setPasswordEncoder(passwordEncoder()); // set the password encoder - bcrypt
        return auth;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/add-user/**").hasRole("ADMIN")
                                .requestMatchers("/delete-user/**").hasRole("ADMIN")
                                .requestMatchers("/update-user/**").hasRole("ADMIN")
                                .requestMatchers("/delete-client").hasRole("ADMIN")
                                .requestMatchers("/update-receipt").hasRole("ADMIN")
                                .requestMatchers("/update-receipt").hasRole("ADMIN")
                                .requestMatchers("/list-user").hasRole("ADMIN")
                                .requestMatchers("/update-beneficiary").hasRole("ADMIN")
                                .requestMatchers("/society").hasRole("ADMIN")
                                .requestMatchers("/deleting-client-page").hasRole("ADMIN")
                                .requestMatchers("/resources/**").permitAll()
                                .anyRequest().authenticated()

                )
                .formLogin(form ->
                        form
                                .loginPage("/authentification")
                                .loginProcessingUrl("/authenticateTheUser")
                                .permitAll()

                )
                .logout(logout -> logout.permitAll()
                )
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied")
                );
        return http.build();
    }
}
