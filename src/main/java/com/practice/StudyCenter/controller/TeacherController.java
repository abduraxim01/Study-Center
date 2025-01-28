package com.practice.StudyCenter.controller;

import com.practice.StudyCenter.DTO.requestDTO.*;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.service.GroupService;
import com.practice.StudyCenter.service.StudentService;
import com.practice.StudyCenter.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize(value = "hasAnyRole('SUPERADMIN','ADMIN') and hasAuthority('ADMIN_CREATE')")
    @PostMapping(value = "/createTeacher/{study_center_id}")
    public ResponseEntity<Object> createTeacher(@RequestBody TeacherDTOForRequest teacherDTOForRequest, @PathVariable int study_center_id) {
        try {
            return ResponseEntity.ok(teachService.createTeacher(teacherDTOForRequest, study_center_id));
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

    @PreAuthorize(value = "hasAnyRole('SUPERADMIN','ADMIN') and hasAuthority('ADMIN_MODIFY')")
    @PutMapping(value = "/setPermissions/{teacher_id}")
    public ResponseEntity<?> setPermissions(@RequestBody Set<String> permissions, @PathVariable int teacher_id) {
        try {
            return ResponseEntity.ok(teachService.setPermission(permissions, teacher_id));
        } catch (AllExceptions.IllegalArgumentException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

//    @PreAuthorize(value = "hasAnyRole('ADMIN')")
//    @PostMapping(value = "/createGroup")
//    public ResponseEntity<?> createGroup(@RequestBody GroupDTOforReq groupDTOforReq) {
//        try {
//            return ResponseEntity.ok(grpService.createGroup(groupDTOforReq));
//        } catch (AllExceptions.InvalidJwtException exception) {
//            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
//        }
//    }

//    @PreAuthorize(value = "hasAnyRole('SUPERADMIN','ADMIN')")
//    @GetMapping(value = "/getGroupsByStudyCenterId/{study_center_id}")
//    public ResponseEntity<?> getGroupsByStudyCenterId(@PathVariable int study_center_id) {
//        return ResponseEntity.ok(teachService.getGroupsByStudyCenterId(study_center_id));
//    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')  and hasAuthority('GROUP_UPDATE')")
    @PutMapping(value = "/assignTeachersToGroup/{groupId}")
    public ResponseEntity<String> assignTeachersToGroup(@RequestBody IdsList idsList, @PathVariable int groupId) {
        try {
            grpService.assignTeachersToGroup(idsList, groupId);
            return ResponseEntity.ok("O'zgartirishlar muvafaqqiyatli saqlandi");
        } catch (AllExceptions.InvalidJwtException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        } catch (AllExceptions.NoSuchElementException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','SUPERADMIN') and hasAuthority('GROUP_UPDATE')")
    @PutMapping(value = "/assignStudentsToGroup/{groupId}")
    public ResponseEntity<String> assignStudentsToGroup(@RequestBody IdsList idsList, @PathVariable int groupId) {
        try {
            stdService.assignStudentToGroup(idsList, groupId);
            return ResponseEntity.ok("O'zgartirishlar muvafaqqiyatli saqlandi");
        } catch (AllExceptions.InvalidJwtException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        } catch (AllExceptions.NoSuchElementException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

//    @PreAuthorize(value = "hasAnyRole('ADMIN')")
//    @PostMapping(value = "/createStudent/{study_center_id}")
//    public ResponseEntity<?> createStudent(@RequestBody StudentDTOforReq studentDTOforReq, @PathVariable int study_center_id) {
//        try {
//            return ResponseEntity.ok(stdService.createStudent(studentDTOforReq, study_center_id));
//        } catch (AllExceptions.IllegalArgumentException exception) {
//            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
//        } catch (AllExceptions.UsernameAlreadyTakenException exception) {
//            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
//        } catch (AllExceptions.InvalidJwtException exception) {
//            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
//        } catch (AllExceptions.EntityNotFoundException exception) {
//            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
//        }
//    }

    @PreAuthorize(value = "hasAnyRole('SUPERADMIN','ADMIN') and hasAuthority('STUDENT_SHOW')")
    @GetMapping(value = "/getStudentsByGroupId/{groupId}")
    public ResponseEntity<?> getStudentsByGroupId(@PathVariable int groupId) {
        try {
            return ResponseEntity.ok(stdService.getStudentsByGroupId(groupId));
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasAnyRole('SUPERADMIN','ADMIN') and hasAuthority('STUDENT_SHOW')")
    @GetMapping(value = "/getStudentsByStudyCenterId/{studyCenterId} ")
    public ResponseEntity<?> getStudentsByStudyCenterId(@PathVariable int studyCenterId) {
        try {
            return ResponseEntity.ok(stdService.getStudentsByStudyCenterId(studyCenterId));
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        } catch (Exception e) {
            return new ResponseEntity<>("Noma'lum sabab tufayli xato", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    @PreAuthorize(value = "hasAnyRole('ADMIN')")
//    @PostMapping(value = "/markAttandance/{groupId}")
//    public ResponseEntity<?> markAttandance(@RequestBody List<AttandanceDTOforReq> attandanceDTOforReqList, @PathVariable int groupId) {
//        try {
//            return ResponseEntity.ok(grpService.markAttandance(attandanceDTOforReqList, groupId));
//        } catch (AllExceptions.NoSuchElementException exception) {
//            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
//        }
//    }

//    @PreAuthorize(value = "hasAnyRole('ADMIN')")
//    @PostMapping(value = "/markPayment")
//    public ResponseEntity<?> markPayment(@RequestBody PaymentDTOforReq paymentDTOforReq) {
//        try {
//            return ResponseEntity.ok(grpService.markPayment(paymentDTOforReq));
//        } catch (AllExceptions.EntityNotFoundException exception) {
//            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
//        } catch (AllExceptions.InvalidJwtException exception) {
//            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
//        }
//    }

//    @PreAuthorize(value = "hasAnyRole('ADMIN')")
//    @PostMapping(value = "/postResult/{groupId}")
//    public ResponseEntity<?> postResult(@RequestBody List<ResultDTOforReq> resultDTOforReqList, @PathVariable int groupId) {
//        try {
//            return ResponseEntity.ok(grpService.postResult(resultDTOforReqList, groupId));
//        } catch (AllExceptions.NoSuchElementException exception) {
//            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
//        }
//    }
}