package com.practice.StudyCenter.DTO.response;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTOforRes {

    private LocalDate payment_time;

    private float amount;
}
