package com.practice.StudyCenter.mapper;

import com.practice.StudyCenter.DTO.requestDTO.PaymentDTOforReq;
import com.practice.StudyCenter.model.Payment;
import com.practice.StudyCenter.model.Student;

public class PaymentMapper {

    public Payment toModel(PaymentDTOforReq paymentDTOforReq, Student student) {
        return Payment.builder()
                .amount(paymentDTOforReq.getAmount())
                .student(student)
                .build();
    }
}
