package com.practice.StudyCenter.controller;

import com.practice.StudyCenter.DTO.requestDTO.IdsList;
import com.practice.StudyCenter.DTO.requestDTO.ResultDTOForRequest;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/result")
public class ResultController {

    @Autowired
    private ResultService rstService;

    @PreAuthorize(value = "hasRole('ADMIN') and hasAuthority('RESULT_CREATE')")
    @PostMapping(value = "/postResult/{groupId}")
    public ResponseEntity<?> postResult(@RequestBody List<ResultDTOForRequest> resultDTOForRequestList, @PathVariable int groupId) {
        try {
            return ResponseEntity.ok(rstService.postResult(resultDTOForRequestList, groupId));
        } catch (AllExceptions.NoSuchElementException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','USER') and hasAuthority('RESULT_SHOW')")
    @GetMapping(value = "/getResultByGroupId/{groupId}")
    public ResponseEntity<?> getResultByGroupId(@PathVariable int groupId) {
        try {
            return ResponseEntity.ok(rstService.getResultByGroupId(groupId));
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasRole('ADMIN') and hasAuthority('RESULT_SOFT_DELETE')")
    @DeleteMapping(value = "/softDeleteResult")
    public ResponseEntity<String> softDeleteResult(@RequestBody IdsList idsList) {
        try {
            rstService.softDeleteResult(idsList);
            return ResponseEntity.ok("Result muvafaqqiyatli o'chirildi");
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasRole('ADMIN') and hasAuthority('RESULT_UPDATE')")
    @PutMapping(value = "/updateResult")
    public ResponseEntity<?> updateResult(@RequestBody Map<Integer, Float> updatedResultList) {
        try {
            return ResponseEntity.ok(rstService.updateResult(updatedResultList));
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }
}
