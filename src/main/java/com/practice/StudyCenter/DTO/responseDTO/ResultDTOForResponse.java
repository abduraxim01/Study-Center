package com.practice.StudyCenter.DTO.responseDTO;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultDTOForResponse {

    private  int id;

    private LocalDate localDate;

    private Float grade;
}
