package com.practice.StudyCenter.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudyCenterDTOForCreate {

    private String name;

    private String phoneNumber;
}
