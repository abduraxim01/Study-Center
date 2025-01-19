package com.practice.StudyCenter.mapper;

import com.practice.StudyCenter.DTO.requestDTO.StudentDTOforReq;
import com.practice.StudyCenter.model.privileges.Role;
import com.practice.StudyCenter.model.Student;
import com.practice.StudyCenter.model.StudyCenter;

public class StudentMapper {

    public Student toModel(StudentDTOforReq studentDTOforReq, StudyCenter studyCenter) {
        return Student.builder()
                .name(studentDTOforReq.getName())
                .surname(studentDTOforReq.getSurname())
                .username(studentDTOforReq.getUsername())
                .password(studentDTOforReq.getPassword())
                .nameOfParent(studentDTOforReq.getNameOfParent())
                .phoneNumber(studentDTOforReq.getPhoneNumber())
                .studyCenter(studyCenter)
                .role(Role.USER)
                .build();
    }
}
