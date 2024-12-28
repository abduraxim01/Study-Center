package com.practice.StudyCenter.DTO.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherDTOforRes {

    private String name;

    private String surname;

    private String phoneNumber;

    private String username;

    private LocalDate created_at;
}
