package com.practice.StudyCenter.controller;

import com.practice.StudyCenter.DTO.requestDTO.GroupDTOForRequest;
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

    @PreAuthorize(value = "hasRole('ADMIN') and hasAuthority('GROUP_CREATE')")
    @PostMapping(value = "/createGroup/{study_center_id}")
    public ResponseEntity<?> createGroup(@RequestBody GroupDTOForRequest groupDTOForRequest, @PathVariable int study_center_id) {
        try {
            return ResponseEntity.ok(grpService.createGroup(groupDTOForRequest, study_center_id));
        } catch (AllExceptions.InvalidJwtException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasAnyRole('SUPERADMIN','ADMIN') and hasAuthority('GROUP_SHOW')")
    @GetMapping(value = "/getGroupsByStudyCenterId/{study_center_id}")
    public ResponseEntity<?> getGroupsByStudyCenterId(@PathVariable int study_center_id) {
        try {
            return ResponseEntity.ok(teachService.getGroupsByStudyCenterId(study_center_id));
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasRole('ADMIN') and hasAuthority('GROUP_DELETE')")
    @DeleteMapping(value = "/deleteGroup/{group_id}")
    public ResponseEntity<?> deleteGroup(@PathVariable int group_id) {
        try {
            return ResponseEntity.ok(grpService.deleteGroup(group_id));
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasRole('ADMIN') and hasAuthority('GROUP_RESTORE')")
    @PostMapping(value = "/restoreGroup/{group_id}")
    public ResponseEntity<?> restoreGroup(@PathVariable int group_id) {
        try {
            return ResponseEntity.ok(grpService.restoreGroup(group_id));
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasRole('ADMIN') and hasAuthority('GROUP_SOFT_DELETE')")
    @DeleteMapping(value = "/softDeleteGroup/{group_id}")
    public ResponseEntity<String> softDeleteGroup(@PathVariable int group_id) {
        try {
            grpService.softDeleteGroup(group_id);
            return ResponseEntity.ok("Group muvafaqqiyatli o'chirildi Id: " + group_id);
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }
}
