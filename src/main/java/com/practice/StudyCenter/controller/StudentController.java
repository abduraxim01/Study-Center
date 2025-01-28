package com.practice.StudyCenter.controller;

import com.practice.StudyCenter.DTO.requestDTO.StudentDTOForRequest;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.service.GroupService;
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

    @Autowired
    private GroupService grpService;

    @PreAuthorize(value = "hasAnyRole('ADMIN') and hasAuthority('STUDENT_CREATE')")
    @PostMapping(value = "/createStudent/{study_center_id}")
    public ResponseEntity<?> createStudent(@RequestBody StudentDTOForRequest studentDTOForRequest, @PathVariable int study_center_id) {
        try {
            return ResponseEntity.ok(stdService.createStudent(studentDTOForRequest, study_center_id));
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

    @PreAuthorize(value = "hasAnyRole('SUPERADMIN','USER') and hasAuthority('GROUP_SHOW')")
    @GetMapping(value = "/getGroupsByStudentId/{student_id}")
    public ResponseEntity<?> getGroupsByStudentId(@PathVariable int student_id) {
        try {
            return ResponseEntity.ok(grpService.getGroupsByStudentId(student_id));
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','USER') and  hasAuthority('PAYMENT_SHOW')")
    @GetMapping(value = "/getPayments/{student_id}")
    public ResponseEntity<?> getPayments(@PathVariable int student_id) {
        try {
            return ResponseEntity.ok(stdService.getPayments(student_id));
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasAnyRole('SUPERADMIN','ADMIN') and hasAuthority('STUDENT_DELETE')")
    @DeleteMapping(value = "/deleteStudent/{student_id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int student_id) {
        try {
            return ResponseEntity.ok(stdService.deleteStudent(student_id));
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasAnyRole('SUPERADMIN','ADMIN') and hasAuthority('STUDENT_RESTORE')")
    @PostMapping(value = "/restoreStudent/{student_id}")
    public ResponseEntity<?> restoreStudent(@PathVariable int student_id) {
        try {
            return ResponseEntity.ok(stdService.restoreStudent(student_id));
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasAnyRole('SUPERADMIN','ADMIN') and hasAuthority('STUDENT_SOFT_DELETE')")
    @DeleteMapping(value = "/softDeleteStudent/{student_id}")
    public ResponseEntity<?> softDeleteStudent(@PathVariable int student_id) {
        try {
            stdService.softDeleteStudent(student_id);
            return ResponseEntity.ok("Student muvafaqqiyatli o'chirildi");
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }
}
