package com.practice.StudyCenter.mapper;

import com.practice.StudyCenter.DTO.requestDTO.TeacherDTOforReq;
import com.practice.StudyCenter.DTO.response.TeacherDTOforRes;
import com.practice.StudyCenter.model.Role;
import com.practice.StudyCenter.model.StudyCenter;
import com.practice.StudyCenter.model.Teacher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TeacherMapper {

    final private PasswordEncoder encoder = new BCryptPasswordEncoder();

    public Teacher toModel(TeacherDTOforReq teacherDTOforReq, StudyCenter studyCenter) {
        return Teacher.builder()
                .name(teacherDTOforReq.getName())
                .surname(teacherDTOforReq.getSurname())
                .username(teacherDTOforReq.getUsername())
                .password(encoder.encode(teacherDTOforReq.getPassword()))
                .phoneNumber(teacherDTOforReq.getPhoneNumber())
                .studyCenter(studyCenter)
                .role(Role.ADMIN)
                .build();
    }

    public TeacherDTOforRes toDTO(Teacher teacher) {
        return TeacherDTOforRes.builder()
                .name(teacher.getName())
                .surname(teacher.getSurname())
                .username(teacher.getUsername())
                .phoneNumber(teacher.getPhoneNumber())
                .created_at(teacher.getCreated_at())
//                .groupList(teacher.getGroupList())
                .build();
    }
}
