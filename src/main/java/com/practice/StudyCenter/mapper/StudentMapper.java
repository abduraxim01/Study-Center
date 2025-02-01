package com.practice.StudyCenter.mapper;

import com.practice.StudyCenter.DTO.requestDTO.StudentDTOForRequest;
import com.practice.StudyCenter.DTO.responseDTO.StudentDTOForResponse;
import com.practice.StudyCenter.model.privileges.Permission;
import com.practice.StudyCenter.model.privileges.Role;
import com.practice.StudyCenter.model.Student;
import com.practice.StudyCenter.model.StudyCenter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

public class StudentMapper {

    final private PasswordEncoder encoder = new BCryptPasswordEncoder();

    public Student toModel(StudentDTOForRequest studentDTOForRequest, StudyCenter studyCenter) {
        return Student.builder()
                .name(studentDTOForRequest.getName())
                .surname(studentDTOForRequest.getSurname())
                .username(studentDTOForRequest.getUsername())
                .password(encoder.encode(studentDTOForRequest.getPassword()))
                .nameOfParent(studentDTOForRequest.getNameOfParent())
                .phoneNumber(studentDTOForRequest.getPhoneNumber())
                .studyCenter(studyCenter)
                .role(Role.USER)
                .permissions(Set.of(Permission.RESULT_SHOW,Permission.PAYMENT_SHOW,Permission.ATTENDANCE_SHOW,
                        Permission.GROUP_SHOW,Permission.HOMEWORK_SHOW))
                .isAvailable(true)
                .build();
    }

    public StudentDTOForResponse toDTO(Student student) {
        return StudentDTOForResponse.builder()
                .name(student.getName())
                .surname(student.getSurname())
                .nameOfParent(student.getNameOfParent())
                .phoneNumber(student.getPhoneNumber())
                .created_at(student.getCreated_at())
                .isAvailable(student.isAvailable())
                .build();
    }

}
