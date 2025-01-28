package com.practice.StudyCenter.service;

import com.practice.StudyCenter.DTO.requestDTO.PaymentDTOForRequest;
import com.practice.StudyCenter.DTO.responseDTO.PaymentDTOForResponse;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.mapper.PaymentMapper;
import com.practice.StudyCenter.model.Payment;
import com.practice.StudyCenter.repository.PaymentRepository;
import com.practice.StudyCenter.repository.StudentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository pymRepository;

    @Autowired
    private StudentRepository stdRepository;

    final private PaymentMapper pymMapper = new PaymentMapper();

    public Payment postPayment(PaymentDTOForRequest paymentDTOForRequest) {
        return pymRepository.save(pymMapper.toModel(paymentDTOForRequest,
                stdRepository.findById(paymentDTOForRequest.getStudent_id()).orElseThrow(() -> new AllExceptions.EntityNotFoundException("Student topilmadi Id: " + paymentDTOForRequest.getStudent_id()))));
    }

    public void softDeletePayment(int paymentId) {
        if (pymRepository.existsById(paymentId)) pymRepository.deleteById(paymentId);
        else throw new AllExceptions.EntityNotFoundException("Payment topilmadi Id: " + paymentId);
    }

    public PaymentDTOForResponse updatePayment(PaymentDTOForRequest paymentDTO, int paymentId) {
        Payment payment =  pymRepository.findById(paymentId).orElseThrow(() -> new AllExceptions.EntityNotFoundException("Payment topilmadi Id: " + paymentId));
        payment.setAmount(paymentDTO.getAmount());
        return pymMapper.toDTO(pymRepository.save(payment));
    }
}
