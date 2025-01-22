package com.practice.StudyCenter.controller;

import com.practice.StudyCenter.DTO.requestDTO.GroupDTOforReq;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.service.GroupService;
import com.practice.StudyCenter.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/group")
public class GroupController {

    @Autowired
    private GroupService grpService;

    @Autowired
    private TeacherService teachService;

    @PreAuthorize(value = "hasAnyRole('ADMIN')")
    @PostMapping(value = "/createGroup")
    public ResponseEntity<?> createGroup(@RequestBody GroupDTOforReq groupDTOforReq) {
        try {
            return ResponseEntity.ok(grpService.createGroup(groupDTOforReq));
        } catch (AllExceptions.InvalidJwtException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasAnyRole('SUPERADMIN','ADMIN')")
    @GetMapping(value = "/getGroupsByStudyCenterId/{study_center_id}")
    public ResponseEntity<?> getGroupsByStudyCenterId(@PathVariable int study_center_id) {
        return ResponseEntity.ok(teachService.getGroupsByStudyCenterId(study_center_id));
    }
}
