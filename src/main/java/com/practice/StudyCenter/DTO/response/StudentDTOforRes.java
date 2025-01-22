package com.practice.StudyCenter.DTO.response;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTOforRes {

    private String name;

    private String surname;

    private String phoneNumber;

    private String nameOfParent;

    private LocalDate created_at;

}
