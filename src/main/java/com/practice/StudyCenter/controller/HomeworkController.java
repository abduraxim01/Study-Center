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

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PostMapping(value = "/postHomework/{groupId}")
    public ResponseEntity<?> postHomework(@RequestBody List<HomeworkDTOForRequest> homeworkDTOForRequestList, @PathVariable int groupId) {
        try {
            return ResponseEntity.ok(homService.postHomework(homeworkDTOForRequestList, groupId));
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping(value = "/getHomeworksByGroupId/{groupId}")
    public ResponseEntity<?> getHomeworksByGroupId(@PathVariable int groupId) {
        try {
            return ResponseEntity.ok(homService.getHomeworksByGroupId(groupId) );
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }


}
