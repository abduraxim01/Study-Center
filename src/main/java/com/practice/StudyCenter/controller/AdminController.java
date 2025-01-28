package com.practice.StudyCenter.controller;

import com.practice.StudyCenter.DTO.requestDTO.StudyCenterDTOForRequest;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.repository.StudyCenterRepository;
import com.practice.StudyCenter.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private StudyCenterRepository stdRepository;

    @PreAuthorize(value = "hasRole('SUPERADMIN')")
    @PostMapping(value = "/createStudyCenter")
    public ResponseEntity<Object> createStudyCenter(@RequestBody StudyCenterDTOForRequest studyCenterDTO) {
        try {
            return ResponseEntity.ok(adminService.createStudyCenter(studyCenterDTO));
        } catch (AllExceptions.NullPointerException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        } catch (AllExceptions.IllegalArgumentException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        } catch (AllExceptions.InvalidJwtException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

//    @GetMapping(value = "/all")
//    public List<StudyCenter> get(){
//     return stdRepository.findAll();
//    }
}
