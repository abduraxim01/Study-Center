package com.practice.StudyCenter.service.authService;

import com.practice.StudyCenter.DTO.requestDTO.LoginForRequest;
import com.practice.StudyCenter.DTO.responseDTO.LoginForResponse;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.model.Student;
import com.practice.StudyCenter.model.Teacher;
import com.practice.StudyCenter.repository.StudentRepository;
import com.practice.StudyCenter.repository.TeacherRepository;
import com.practice.StudyCenter.service.jwtService.JwtUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private TeacherRepository teachRepository;

    @Autowired
    private StudentRepository stdRepository;

    final private Logger logger = LogManager.getLogger(AuthService.class);

    final private PasswordEncoder encoder = new BCryptPasswordEncoder();

    final private JwtUtil jwtUtil = new JwtUtil();

    @Override
    public UserDetails loadUserByUsername(String username) {
        Teacher teacher = teachRepository.findTeacherByUsername(username);
        if (teacher != null) return teacher;

        Student student = stdRepository.findStudentByUsername(username);
        if (student != null) return student;

        throw new AllExceptions.UsernameNotFoundException("Foydalanuvchi topilmadi: from loadByUsername");
    }

    public LoginForResponse login(LoginForRequest login) {
        UserDetails user = loadUserByUsername(login.getUsername());
        String role = null;
        Map<String, List<String>> permissionsMap = new HashMap<>();
        if (encoder.matches(login.getPassword(), user.getPassword())) {
            logger.info("Username: {} muvafaqqiyatli login qildi", user.getUsername());
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
            int index;
            String authorityName;

            for (GrantedAuthority authority : authorities) {
                authorityName = authority.getAuthority();
                index = authorityName.indexOf("_");
                if (!authorityName.startsWith("ROLE_")) {
                    permissionsMap.computeIfAbsent(authorityName.substring(0, index), k -> new ArrayList<>()).add(authorityName.substring(index + 1));
                } else role = authorityName.substring(index + 1);
            }

            return LoginForResponse.builder()
                    .username(user.getUsername())
//                    .studyCenterId(user)
                    .permissions(permissionsMap)
                    .role(role)
                    .token(jwtUtil.encode(login.getUsername(), user.getAuthorities()))
                    .build();
        }
        throw new AllExceptions.UsernameNotFoundException("Username yoki parol xato: from login");
    }
}
