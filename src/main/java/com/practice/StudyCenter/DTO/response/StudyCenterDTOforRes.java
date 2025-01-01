package com.practice.StudyCenter.DTO.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudyCenterDTOforRes {

    private Integer id;

    private String name;

    private String phoneNumber;

    private LocalDate created_at;
}
