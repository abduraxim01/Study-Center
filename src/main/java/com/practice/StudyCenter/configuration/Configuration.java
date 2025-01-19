package com.practice.StudyCenter.configuration;

import com.practice.StudyCenter.service.authService.AuthService;
import com.practice.StudyCenter.service.jwtService.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@org.springframework.context.annotation.Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class Configuration {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtFilter jwtFilter;

    final private String TEACHER_API = "/api/teacher";

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requestsConfigurer -> {
                    requestsConfigurer
                            .requestMatchers("/api/admin/addStudyCenter").hasAuthority("ROLE_SUPERADMIN")
                            .requestMatchers(TEACHER_API + "/createTeacher/{study_center_id}").hasAnyRole("ADMIN", "SUPERADMIN")
                            .requestMatchers(TEACHER_API + "/createGroup").hasAnyAuthority("ROLE_ADMIN")
                            .requestMatchers(TEACHER_API + "/assignTeachersToGroup/{groupId}").hasAnyAuthority("ROLE_ADMIN")
                            .requestMatchers(TEACHER_API + "/createStudent/{study_center_id}").hasAnyAuthority("ROLE_ADMIN")
                            .requestMatchers(TEACHER_API + "/assignStudentsToGroup/{groupId}").hasAnyAuthority("ROLE_ADMIN")
                            .requestMatchers(TEACHER_API + "/markAttandance/{groupId}").hasAnyAuthority("ROLE_ADMIN")
                            .requestMatchers(TEACHER_API + "/markPayment").hasAnyAuthority("ROLE_ADMIN")
                            .requestMatchers(TEACHER_API + "/postResult/{groupId}").hasAnyAuthority("ROLE_ADMIN")
                            .anyRequest().permitAll();
                })
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(authService)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
