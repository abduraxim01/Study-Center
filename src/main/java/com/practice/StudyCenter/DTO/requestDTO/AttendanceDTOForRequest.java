package com.practice.StudyCenter.DTO.requestDTO;

import com.practice.StudyCenter.model.attendance.AttendanceStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceDTOForRequest {

    private AttendanceStatus status;

    private Integer student_id;
}
