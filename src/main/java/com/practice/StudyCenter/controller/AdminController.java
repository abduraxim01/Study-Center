package com.practice.StudyCenter.controller;

import com.practice.StudyCenter.DTO.requestDTO.StudyCenterDTOforReq;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PreAuthorize(value = "hasRole('SUPERADMIN')")
    @PostMapping(value = "/addStudyCenter")
    public ResponseEntity<Object> addStudyCenter(@RequestBody StudyCenterDTOforReq studyCenterDTO) {
        try {
            return ResponseEntity.ok(adminService.addStudyCenter(studyCenterDTO));
        } catch (AllExceptions.NullPointerException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        } catch (AllExceptions.IllegalArgumentException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }
}
