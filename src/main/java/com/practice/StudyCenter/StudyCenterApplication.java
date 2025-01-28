package com.practice.StudyCenter;

import com.practice.StudyCenter.model.Teacher;
import com.practice.StudyCenter.model.privileges.Permission;
import com.practice.StudyCenter.model.privileges.Role;
import com.practice.StudyCenter.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.EnumSet;

@SpringBootApplication
@EnableTransactionManagement
public class StudyCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudyCenterApplication.class, args);
    }

    @Autowired
    private TeacherService teachService;

    final private PasswordEncoder encoder = new BCryptPasswordEncoder();

    @Bean
    public CommandLineRunner startUp() {
        return args -> {
            teachService.createSuperAdmin(Teacher.builder()
                    .name("Abduraxim")
                    .username("abduraxim01")
                    .password(encoder.encode("abduraxim01"))
                    .role(Role.SUPERADMIN)
                    .permissions(EnumSet.allOf(Permission.class))
                    .studyCenter(null)
                    .build());
        };
    }
}
