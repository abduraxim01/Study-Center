package com.practice.StudyCenter.repository;

import com.practice.StudyCenter.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    boolean existsById(int paymentId);
}
