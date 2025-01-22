package com.practice.StudyCenter.controller;

import com.practice.StudyCenter.DTO.requestDTO.StudentDTOforReq;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/student")
public class StudentController {

    @Autowired
    private StudentService stdService;

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PostMapping(value = "/createStudent/{study_center_id}")
    public ResponseEntity<?> createStudent(@RequestBody StudentDTOforReq studentDTOforReq, @PathVariable int study_center_id) {
        try {
            return ResponseEntity.ok(stdService.createStudent(studentDTOforReq, study_center_id));
        } catch (AllExceptions.IllegalArgumentException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        } catch (AllExceptions.UsernameAlreadyTakenException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        } catch (AllExceptions.InvalidJwtException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasAnyRole('SUPERADMIN','USER')")
    @GetMapping(value = "/getGroupsByStudentId/{student_id}")
    public ResponseEntity<?> getGroupsByStudentId(@PathVariable int student_id) {
        return ResponseEntity.ok(stdService.getGroupsByStudentId(student_id));
    }

    @PreAuthorize(value = "hasAnyRole('SUPERADMIN','USER')")
    @GetMapping(value = "/getPayments/{student_id}")
    public ResponseEntity<?> getPayments(@PathVariable int student_id) {
        return ResponseEntity.ok(stdService.getPayments(student_id));
    }
}
