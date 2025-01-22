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

    final private String ATTENDANCE_API = "/api/attendance";

    final private String RESULT_API = "/api/result";

    final private String PAYMENT_API = "/api/payment";

    final private String GROUP_API = "/api/group";

    final private String STUDENT_API = "/api/student";

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(requestsConfigurer -> {
                    requestsConfigurer
                            .requestMatchers("/api/admin/addStudyCenter").hasRole("SUPERADMIN")
                            .requestMatchers(TEACHER_API + "/createTeacher/{study_center_id}").hasAnyRole("ADMIN", "SUPERADMIN")
                            .requestMatchers(TEACHER_API + "/setPermissions/{user_id}").hasAnyRole("ADMIN", "SUPERADMIN")
                            .requestMatchers(TEACHER_API + "/getStudentsByGroupId/{groupId}").hasAnyRole("ADMIN", "SUPERADMIN")
                            .requestMatchers(TEACHER_API + "/getStudentsByStudyCenterId/{study_center_id}").hasAnyRole("ADMIN", "SUPERADMIN")
                            .requestMatchers(TEACHER_API + " /assignStudentsToGroup/{groupId}").hasAnyRole("ADMIN", "SUPERADMIN")
                            .requestMatchers(TEACHER_API + "/assignTeachersToGroup/{groupId}").hasRole("ADMIN")
                            .requestMatchers(GROUP_API + "/createGroup").hasRole("ADMIN")
                            .requestMatchers(GROUP_API + "/getGroupsByStudyCenterId/{study_center_id}").hasAnyRole("ADMIN", "SUPERADMIN")
                            .requestMatchers(STUDENT_API + "/createStudent/{study_center_id}").hasRole("ADMIN")
                            .requestMatchers(STUDENT_API + "/getGroupsByStudentId/{student_id}").hasAnyRole("USER", "SUPERADMIN")
                            .requestMatchers(STUDENT_API + "/getPayments/{student_id}").hasAnyRole("USER", "SUPERADMIN")
                            .requestMatchers(PAYMENT_API + "/markPayment").hasRole("ADMIN")
                            .requestMatchers(ATTENDANCE_API + "/markAttendance/{groupId}").hasRole("ADMIN")
                            .requestMatchers(ATTENDANCE_API + "/getAttendanceByGroupId/{groupId}").hasRole("ADMIN")
                            .requestMatchers(ATTENDANCE_API + "/getAttendanceByGroupAndStudentId/{group_id}/{student_id}").hasAnyRole("ADMIN", "USER")
                            .requestMatchers(RESULT_API + "/postResult/{groupId}").hasRole("ADMIN")
                            .requestMatchers(RESULT_API + "/getResultByGroupId/{groupId}").hasRole("ADMIN")
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
