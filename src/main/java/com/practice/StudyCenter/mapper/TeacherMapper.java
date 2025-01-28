package com.practice.StudyCenter.mapper;

import com.practice.StudyCenter.DTO.requestDTO.TeacherDTOForRequest;
import com.practice.StudyCenter.DTO.responseDTO.TeacherDTOForResponse;
import com.practice.StudyCenter.model.privileges.Role;
import com.practice.StudyCenter.model.StudyCenter;
import com.practice.StudyCenter.model.Teacher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TeacherMapper {

    final private PasswordEncoder encoder = new BCryptPasswordEncoder();

    final private GroupMapper grpMapper = new GroupMapper();

    public Teacher toModel(TeacherDTOForRequest teacherDTOForRequest, StudyCenter studyCenter) {
        return Teacher.builder()
                .name(teacherDTOForRequest.getName())
                .surname(teacherDTOForRequest.getSurname())
                .username(teacherDTOForRequest.getUsername())
                .password(encoder.encode(teacherDTOForRequest.getPassword()))
                .phoneNumber(teacherDTOForRequest.getPhoneNumber())
                .studyCenter(studyCenter)
                .role(Role.ADMIN)
                .build();
    }

    public TeacherDTOForResponse toDTO(Teacher teacher) {
        return TeacherDTOForResponse.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .surname(teacher.getSurname())
                .username(teacher.getUsername())
                .phoneNumber(teacher.getPhoneNumber())
                .created_at(teacher.getCreated_at())
                .groupList(grpMapper.toDTO(teacher.getGroupList()))
                .build();
    }

//    public Set<Permission> setPermissions(Set<String> permissions, Teacher teacher) {
//        Set<Permission> permissionSet = new HashSet<>();
//        permissions.stream()
//                .forEach(permission -> permissionSet.add(Permission.valueOf(permission)));
//    }
}
