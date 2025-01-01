package com.practice.StudyCenter.DTO.requestDTO;

import com.practice.StudyCenter.model.attandance.AttandanceStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttandanceDTOforReq {

    private AttandanceStatus status;

    private Integer student_id;
}
