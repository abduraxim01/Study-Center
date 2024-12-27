package com.practice.StudyCenter.mapper;

import com.practice.StudyCenter.DTO.StudyCenterDTOForCreate;
import com.practice.StudyCenter.model.StudyCenter;

public class StudyCenterMapper {

    public StudyCenter toModel(StudyCenterDTOForCreate studyCenterDTO) {
        return StudyCenter.builder()
                .name(studyCenterDTO.getName())
                .phoneNumber(studyCenterDTO.getPhoneNumber())
                .build();
    }
}
