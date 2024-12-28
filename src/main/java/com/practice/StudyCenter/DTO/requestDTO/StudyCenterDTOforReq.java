package com.practice.StudyCenter.DTO.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudyCenterDTOforReq {

    private String name;

    private String phoneNumber;
}
