package com.practice.StudyCenter.DTO.responseDTO;

import com.practice.StudyCenter.model.homework.HomeworkStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HomeworkDTOForResponse {

    private String name;

    private LocalDate time;

    private HomeworkStatus status;
}
