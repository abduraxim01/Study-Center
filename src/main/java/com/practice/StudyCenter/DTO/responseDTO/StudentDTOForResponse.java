package com.practice.StudyCenter.DTO.responseDTO;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTOForResponse {

    private String name;

    private String surname;

    private String phoneNumber;

    private String nameOfParent;

    private LocalDate created_at;

    private boolean isAvailable;

}
