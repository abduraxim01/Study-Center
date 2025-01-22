package com.practice.StudyCenter.mapper;

import com.practice.StudyCenter.DTO.requestDTO.PaymentDTOforReq;
import com.practice.StudyCenter.DTO.response.PaymentDTOforRes;
import com.practice.StudyCenter.model.Payment;
import com.practice.StudyCenter.model.Student;

import java.util.List;

public class PaymentMapper {

    public Payment toModel(PaymentDTOforReq paymentDTOforReq, Student student) {
        return Payment.builder()
                .amount(paymentDTOforReq.getAmount())
                .student(student)
                .build();
    }

    public PaymentDTOforRes toDTO(Payment payment) {
        return PaymentDTOforRes.builder()
                .amount(payment.getAmount())
                .payment_time(payment.getPayment_time())
                .build();
    }

    public List<PaymentDTOforRes> toDTO(List<Payment> paymentList) {
        if (paymentList.isEmpty()) return null;
        return paymentList.stream()
                .map(this::toDTO)
                .toList();
    }
}
