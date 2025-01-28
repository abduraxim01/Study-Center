package com.practice.StudyCenter.mapper;

import com.practice.StudyCenter.DTO.responseDTO.AttendanceDTOForResponse;
import com.practice.StudyCenter.model.attendance.Attendance;

import java.util.List;

public class AttendanceMapper {

    public AttendanceDTOForResponse toDTO(Attendance attendance) {
        return AttendanceDTOForResponse.builder()
                .name(attendance.getStudent().getName())
                .localDate(attendance.getLocalDate())
                .status(attendance.getStatus())
                .build();
    }

    public List<AttendanceDTOForResponse> toDTO(List<Attendance> attendanceList) {
        if (attendanceList.isEmpty()) return null;
        return attendanceList.stream()
                .map(this::toDTO)
                .toList();
    }

}
