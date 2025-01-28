package com.practice.StudyCenter.DTO.responseDTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudyCenterDTOForResponse {

    private Integer id;

    private String name;

    private String phoneNumber;

    private LocalDate created_at;
}
