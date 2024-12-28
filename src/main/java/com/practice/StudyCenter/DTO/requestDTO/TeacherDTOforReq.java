package com.practice.StudyCenter.DTO.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
