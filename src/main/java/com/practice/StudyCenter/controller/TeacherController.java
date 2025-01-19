package com.practice.StudyCenter.controller;

import com.practice.StudyCenter.DTO.requestDTO.*;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.model.privileges.Permission;
import com.practice.StudyCenter.service.GroupService;
import com.practice.StudyCenter.service.StudentService;
import com.practice.StudyCenter.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/api/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teachService;

    @Autowired
    private GroupService grpService;

    @Autowired
    private StudentService stdService;

    @PreAuthorize(value = "hasAnyRole('SUPERADMIN','ADMIN') and hasAuthority('CREATE_ADMIN')")
    @PostMapping(value = "/createTeacher/{study_center_id}")
    public ResponseEntity<Object> createTeacher(@RequestBody TeacherDTOforReq teacherDTOforReq, @PathVariable int study_center_id) {
        try {
            return ResponseEntity.ok(teachService.createTeacher(teacherDTOforReq, study_center_id));
        } catch (AllExceptions.IllegalArgumentException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        } catch (AllExceptions.UsernameAlreadyTakenException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        } catch (AllExceptions.InvalidJwtException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        } catch (AllExceptions.NullPointerException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasAnyRole('SUPERADMIN','ADMIN') and hasAuthority('MODIFY_ADMIN')")
    @PutMapping(value = "/setPermissions/{user_id}")
    public ResponseEntity<?> setPermissions(@RequestBody Set<String> permissions, @PathVariable int user_id) {
        try {
            return ResponseEntity.ok(teachService.setPermission(permissions, user_id));
        } catch (AllExceptions.IllegalArgumentException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }

    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PostMapping(value = "/createGroup")
    public ResponseEntity<?> createGroup(@RequestBody GroupDTOforReq groupDTOforReq) {
        try {
            return ResponseEntity.ok(grpService.createGroup(groupDTOforReq));
        } catch (AllExceptions.InvalidJwtException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PostMapping(value = "/assignTeachersToGroup/{groupId}")
    public ResponseEntity<?> assignTeachersToGroup(@RequestBody UserListAsNumber userListAsNumber, @PathVariable int groupId) {
        try {
            return ResponseEntity.ok(grpService.assignTeachersToGroup(userListAsNumber, groupId));
        } catch (AllExceptions.InvalidJwtException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        } catch (AllExceptions.NoSuchElementException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

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

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PostMapping(value = "/assignStudentsToGroup/{groupId}")
    public ResponseEntity<?> assignStudentsToGroup(@RequestBody UserListAsNumber userListAsNumber, @PathVariable int groupId) {
        try {
            return ResponseEntity.ok(grpService.assignStudentToGroup(userListAsNumber, groupId));
        } catch (AllExceptions.InvalidJwtException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        } catch (AllExceptions.NoSuchElementException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PostMapping(value = "/markAttandance/{groupId}")
    public ResponseEntity<?> markAttandance(@RequestBody List<AttandanceDTOforReq> attandanceDTOforReqList, @PathVariable int groupId) {
        try {
            return ResponseEntity.ok(grpService.markAttandance(attandanceDTOforReqList, groupId));
        } catch (AllExceptions.NoSuchElementException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PostMapping(value = "/markPayment")
    public ResponseEntity<?> markPayment(@RequestBody PaymentDTOforReq paymentDTOforReq) {
        try {
            return ResponseEntity.ok(grpService.markPayment(paymentDTOforReq));
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        } catch (AllExceptions.InvalidJwtException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PostMapping(value = "/postResult/{groupId}")
    public ResponseEntity<?> postResult(@RequestBody List<ResultDTOforReq> resultDTOforReqList, @PathVariable int groupId) {
        try {
            return ResponseEntity.ok(grpService.postResult(resultDTOforReqList, groupId));
        } catch (AllExceptions.NoSuchElementException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }
}