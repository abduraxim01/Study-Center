package com.practice.StudyCenter.controller;

import com.practice.StudyCenter.DTO.requestDTO.PaymentDTOForRequest;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService pymService;

    @PreAuthorize(value = "hasRole('ADMIN') and hasAuthority('PAYMENT_CREATE')")
    @PostMapping(value = "/markPayment")
    public ResponseEntity<?> markPayment(@RequestBody PaymentDTOForRequest paymentDTOForRequest) {
        try {
            return ResponseEntity.ok(pymService.postPayment(paymentDTOForRequest));
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasRole('ADMIN') and hasAuthority('PAYMENT_SOFT_DELETE')")
    @DeleteMapping(value = "/softDeletePayment/{paymentId}")
    public ResponseEntity<String> softDeletePayment(@PathVariable int paymentId) {
        try {
            pymService.softDeletePayment(paymentId);
            return ResponseEntity.ok("Payment muvafaqqiyatli o'chirildi");
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }

    @PreAuthorize(value = "hasRole('ADMIN') and hasAuthority('PAYMENT_UPDATE')")
    @PutMapping(value = "/updatePayment/{paymentId}")
    public ResponseEntity<?> updatePayment(@RequestBody PaymentDTOForRequest paymentDTOForRequest, @PathVariable int paymentId) {
        try {
            return ResponseEntity.ok(pymService.updatePayment(paymentDTOForRequest, paymentId));
        } catch (AllExceptions.EntityNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }
}
