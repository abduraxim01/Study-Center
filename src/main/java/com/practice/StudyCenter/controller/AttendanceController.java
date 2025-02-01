package com.practice.StudyCenter.controller;

import com.practice.StudyCenter.DTO.requestDTO.AttendanceDTOForRequest;
import com.practice.StudyCenter.DTO.requestDTO.HomeworkDTOForRequest;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.service.AttendanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attService;

    @PreAuthorize(value = "hasRole('ADMIN') and hasAuthority('ATTENDANCE_CREATE')")
    @PostMapping(value = "/markAttendance/{groupId}")
    public ResponseEntity<?> markAttendance(@RequestBody List<AttendanceDTOForRequest> attendanceDTOForRequestList, @PathVariable int groupId) {
        try {
            return ResponseEntity.ok(attService.postAttendance(attendanceDTOForRequestList, groupId));
        } catch (AllExceptions.NoSuchElementException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasRole('ADMIN') and hasAuthority('ATTENDANCE_SHOW')")
    @GetMapping(value = "/getAttendanceByGroupId/{groupId}")
    public ResponseEntity<?> getAttendanceByGroupId(@PathVariable int groupId) {
        try {
            return ResponseEntity.ok(attService.getAttendanceByGroupId(groupId));
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','USER') and hasAuthority('ATTENDANCE_SHOW')")
    @GetMapping(value = "/getAttendanceByGroupAndStudentId/{group_id}/{student_id}")
    public ResponseEntity<?> getAttendanceByGroupAndStudentId(@PathVariable int group_id, @PathVariable int student_id) {
        try {
            return ResponseEntity.ok(attService.getAttendanceByGroupAndStudentId(group_id, student_id));
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasRole('ADMIN') and hasAuthority('ATTENDANCE_UPDATE')")
    @PutMapping(value = "/updateAttendance/{attendanceId}")
    public ResponseEntity<?> updateAttendance(@RequestBody AttendanceDTOForRequest attendanceDTOForRequest, @PathVariable int attendanceId) {
        try {
            return ResponseEntity.ok(attService.updateAttendance(attendanceDTOForRequest, attendanceId));
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasRole('ADMIN') and hasAuthority('ATTENDANCE_SOFT_DELETE')")
    @DeleteMapping(value = "/softDeleteAttendance/{attendanceId}")
    public ResponseEntity<String> softDeleteAttendance(@PathVariable int attendanceId) {
        try {
            attService.softDeleteAttendance(attendanceId);
            return ResponseEntity.ok("Homework muvafaqqiyatli o'chirildi Id: " + attendanceId);
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }
}
