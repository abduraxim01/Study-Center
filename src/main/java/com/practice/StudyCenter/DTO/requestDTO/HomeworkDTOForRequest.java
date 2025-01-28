package com.practice.StudyCenter.DTO.requestDTO;

import com.practice.StudyCenter.model.attendance.AttendanceStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HomeworkDTOForRequest {

    private AttendanceStatus status;

    private int student_id;
}
