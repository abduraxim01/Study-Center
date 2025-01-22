package com.practice.StudyCenter.mapper;

import com.practice.StudyCenter.DTO.response.AttendanceDTOForResponce;
import com.practice.StudyCenter.model.attendance.Attendance;

import java.util.List;

public class AttendanceMapper {

    public AttendanceDTOForResponce toDTO(Attendance attendance) {
        return AttendanceDTOForResponce.builder()
                .localDate(attendance.getLocalDate())
                .status(attendance.getStatus())
                .build();
    }

    public List<AttendanceDTOForResponce> toDTO(List<Attendance> attendanceList) {
        if (attendanceList.isEmpty()) return null;
        return attendanceList.stream()
                .map(this::toDTO)
                .toList();
    }

}
