package com.practice.StudyCenter.DTO.requestDTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTOforReq {

    private String name;

    private String surname;

    private String phoneNumber;

    private String username;

    private String password;

    private String nameOfParent;
}
