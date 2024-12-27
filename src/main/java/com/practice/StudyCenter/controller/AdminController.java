package com.practice.StudyCenter.controller;

import com.practice.StudyCenter.DTO.StudyCenterDTOForCreate;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.model.StudyCenter;
import com.practice.StudyCenter.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping(value = "/addStudyCenter")
    public ResponseEntity<Object> addStudyCenter(@RequestBody StudyCenterDTOForCreate studyCenterDTO) {
        try {
            return ResponseEntity.ok(adminService.addStudyCenter(studyCenterDTO));
        } catch (AllExceptions.NullPointerException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }
}
