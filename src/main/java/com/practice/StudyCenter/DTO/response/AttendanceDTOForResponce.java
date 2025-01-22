package com.practice.StudyCenter.DTO.response;

import com.practice.StudyCenter.model.attendance.AttendanceStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AttendanceDTOForResponce {

    private LocalDate localDate;

    private AttendanceStatus status;
}
