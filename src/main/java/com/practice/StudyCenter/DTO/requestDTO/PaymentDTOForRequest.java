package com.practice.StudyCenter.DTO.requestDTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDTOForRequest {

    private int student_id;

    private float amount;
}
