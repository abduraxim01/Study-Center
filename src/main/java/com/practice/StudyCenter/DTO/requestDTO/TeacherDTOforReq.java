package com.practice.StudyCenter.DTO.requestDTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherDTOforReq {

    private String name;

    private String surname;

    private String phoneNumber;

    private String username;

    private String password;
}
