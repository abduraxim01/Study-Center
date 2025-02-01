package com.practice.StudyCenter.DTO.requestDTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HomeworkDTOForRequest {

    private String status;

    private int student_id;
}
