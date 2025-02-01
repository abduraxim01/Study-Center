package com.practice.StudyCenter.DTO.responseDTO;

import com.practice.StudyCenter.model.attendance.AttendanceStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceDTOForResponse {

    private int id;

    private String name;

    private LocalDate localDate;

    private AttendanceStatus status;
}
