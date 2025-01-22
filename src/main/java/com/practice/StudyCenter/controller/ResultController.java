package com.practice.StudyCenter.controller;

import com.practice.StudyCenter.DTO.requestDTO.ResultDTOforReq;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/result")
public class ResultController {

    @Autowired
    private ResultService rstService;

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PostMapping(value = "/postResult/{groupId}")
    public ResponseEntity<?> postResult(@RequestBody List<ResultDTOforReq> resultDTOforReqList, @PathVariable int groupId) {
        try {
            return ResponseEntity.ok(rstService.postResult(resultDTOforReqList, groupId));
        } catch (AllExceptions.NoSuchElementException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @GetMapping(value = "/getResultByGroupId/{groupId}")
    public ResponseEntity<?> getResultByGroupId(@PathVariable int groupId) {
        return ResponseEntity.ok(rstService.getResultByGroupId(groupId));
    }
}
