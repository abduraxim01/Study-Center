package com.practice.StudyCenter.controller;

import com.practice.StudyCenter.DTO.requestDTO.GroupDTOforReq;
import com.practice.StudyCenter.DTO.requestDTO.TeacherDTOforReq;
import com.practice.StudyCenter.DTO.requestDTO.TeacherListAsNumber;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.service.GroupService;
import com.practice.StudyCenter.service.TeacherService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teachService;

    @Autowired
    private GroupService grpService;

    @PreAuthorize(value = "hasAnyRole('SUPERADMIN','ADMIN')")
    @PostMapping(value = "/addTeacher/{id}")
    public ResponseEntity<Object> addTeacher(@RequestBody TeacherDTOforReq teacherDTOforReq, @PathVariable int id) {
        try {
            return ResponseEntity.ok(teachService.addTeacher(teacherDTOforReq, id));
        } catch (AllExceptions.IllegalArgumentException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        } catch (AllExceptions.UsernameAlreadyTakenException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        } catch (AllExceptions.InvalidJwtException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PostMapping(value = "/addGroup")
    public ResponseEntity<?> addGroup(@RequestBody GroupDTOforReq groupDTOforReq) {
        try {
            return ResponseEntity.ok(grpService.addGroup(groupDTOforReq));
        } catch (AllExceptions.InvalidJwtException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PostMapping(value = "/assignTeachersToGroup/{groupId}")
    public ResponseEntity<?> assignTeachersToGroup(@RequestBody TeacherListAsNumber teacherListAsNumber, @PathVariable int groupId) {
        try {
            return ResponseEntity.ok(grpService.assignTeachersToGroup(teacherListAsNumber, groupId));
        } catch (AllExceptions.InvalidJwtException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }
}