package com.practice.StudyCenter.controller;

import com.practice.StudyCenter.DTO.requestDTO.PaymentDTOforReq;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService pymService;

    @PreAuthorize(value = "hasRole('ADMIN')")
    @PostMapping(value = "/markPayment")
    public ResponseEntity<?> markPayment(@RequestBody PaymentDTOforReq paymentDTOforReq) {
        try {
            return ResponseEntity.ok(pymService.markPayment(paymentDTOforReq));
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        } catch (AllExceptions.InvalidJwtException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }
}
