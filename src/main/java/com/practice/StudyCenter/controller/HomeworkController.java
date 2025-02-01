package com.practice.StudyCenter.controller;

import com.practice.StudyCenter.DTO.requestDTO.HomeworkDTOForRequest;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/homework")
public class HomeworkController {

    @Autowired
    private HomeworkService homService;

    @PreAuthorize(value = "hasRole('ADMIN') and hasAuthority('HOMEWORK_CREATE')")
    @PostMapping(value = "/postHomework/{groupId}")
    public ResponseEntity<?> postHomework(@RequestBody List<HomeworkDTOForRequest> homeworkDTOForRequestList, @PathVariable int groupId) {
        try {
            return ResponseEntity.ok(homService.postHomework(homeworkDTOForRequestList, groupId));
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasRole('ADMIN') and hasAuthority('HOMEWORK_SHOW')")
    @GetMapping(value = "/getHomeworksByGroupId/{groupId}")
    public ResponseEntity<?> getHomeworksByGroupId(@PathVariable int groupId) {
        try {
            return ResponseEntity.ok(homService.getHomeworksByGroupId(groupId));
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','USER') and hasAuthority('HOMEWORK_SHOW')")
    @GetMapping(value = "/getHomeworksByGroupAndStudentId/{groupId}/{studentId}")
    public ResponseEntity<?> getAttendanceByGroupAndStudentId(@PathVariable int groupId, @PathVariable int studentId) {
        try {
            return ResponseEntity.ok(homService.getHomeworksByGroupAndStudentId(groupId, studentId));
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasRole('ADMIN') and hasAuthority('HOMEWORK_UPDATE')")
    @PutMapping(value = "/updateHomework/{homeworkId}")
    public ResponseEntity<?> updateHomework(@RequestBody HomeworkDTOForRequest homeworkDTOForRequest, @PathVariable int homeworkId) {
        try {
            return ResponseEntity.ok(homService.updateHomework(homeworkDTOForRequest, homeworkId));
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasRole('ADMIN') and hasAuthority('HOMEWORK_SOFT_DELETE')")
    @DeleteMapping(value = "/softDeleteHomework/{homeworkId}")
    public ResponseEntity<String> softDeleteHomework(@PathVariable int homeworkId) {
        try {
            homService.softDeleteHomework(homeworkId);
            return ResponseEntity.ok("Homework muvafaqqiyatli o'chirildi Id: " + homeworkId);
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }
}
