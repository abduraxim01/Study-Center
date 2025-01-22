package com.practice.StudyCenter.service;

import com.practice.StudyCenter.DTO.requestDTO.PaymentDTOforReq;
import com.practice.StudyCenter.mapper.PaymentMapper;
import com.practice.StudyCenter.model.Payment;
import com.practice.StudyCenter.repository.PaymentRepository;
import com.practice.StudyCenter.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository pymRepository;

    @Autowired
    private StudentRepository stdRepository;

    final private PaymentMapper pymMapper = new PaymentMapper();

    public Payment markPayment(PaymentDTOforReq paymentDTOforReq) {
        return pymRepository.save(pymMapper.toModel(
                paymentDTOforReq, stdRepository.findById(paymentDTOforReq.getStudent_id()).get()));
    }
}
