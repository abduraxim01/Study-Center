package com.practice.StudyCenter.mapper;

import com.practice.StudyCenter.DTO.requestDTO.PaymentDTOForRequest;
import com.practice.StudyCenter.DTO.responseDTO.PaymentDTOForResponse;
import com.practice.StudyCenter.model.Payment;
import com.practice.StudyCenter.model.Student;

import java.util.List;

public class PaymentMapper {

    public Payment toModel(PaymentDTOForRequest paymentDTOForRequest, Student student) {
        return Payment.builder()
                .amount(paymentDTOForRequest.getAmount())
                .student(student)
                .build();
    }

    public PaymentDTOForResponse toDTO(Payment payment) {
        return PaymentDTOForResponse.builder()
                .id(payment.getId())
                .amount(payment.getAmount())
                .payment_time(payment.getPayment_time())
                .build();
    }

    public List<PaymentDTOForResponse> toDTO(List<Payment> paymentList) {
        if (paymentList.isEmpty()) return null;
        return paymentList.stream()
                .map(this::toDTO)
                .toList();
    }
}
