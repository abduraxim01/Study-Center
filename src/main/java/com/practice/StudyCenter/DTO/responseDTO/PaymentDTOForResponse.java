package com.practice.StudyCenter.DTO.responseDTO;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTOForResponse {

    private int id;

    private LocalDate payment_time;

    private float amount;
}
