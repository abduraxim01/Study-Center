package com.practice.StudyCenter.controller;

import com.practice.StudyCenter.DTO.requestDTO.TeacherDTOforReq;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teachService;

    @PreAuthorize(value = "hasAnyRole('SUPERADMIN','ADMIN')")
    @PostMapping(value = "/addTeacher/{id}")
    public ResponseEntity<Object> addTeacher(@RequestBody TeacherDTOforReq teacherDTOforReq, @PathVariable int id) {
        try {
            return ResponseEntity.ok(teachService.addTeacher(teacherDTOforReq,id));
        } catch (AllExceptions.IllegalArgumentException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        } catch (AllExceptions.UsernameAlreadyTakenException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }
}