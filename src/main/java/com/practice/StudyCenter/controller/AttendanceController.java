package com.practice.StudyCenter.controller;

import com.practice.StudyCenter.DTO.requestDTO.AttendanceDTOForRequest;
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

    @PreAuthorize(value = "hasRole('ADMIN')")
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

    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping(value = "/getAttendanceByGroupId/{groupId}")
    public ResponseEntity<?> getAttendanceByGroupId(@PathVariable int groupId) {
        try {
            return ResponseEntity.ok(attService.getAttendanceByGroupId(groupId));
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','USER')")
    @GetMapping(value = "/getAttendanceByGroupAndStudentId/{group_id}/{student_id}")
    public ResponseEntity<?> getAttendanceByGroupAndStudentId(@PathVariable int group_id, @PathVariable int student_id) {
        try {
            return ResponseEntity.ok(attService.getAttendanceByGroupAndStudentId(group_id, student_id));
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }
}
