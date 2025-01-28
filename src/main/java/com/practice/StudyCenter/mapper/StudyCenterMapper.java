package com.practice.StudyCenter.mapper;

import com.practice.StudyCenter.DTO.requestDTO.StudyCenterDTOForRequest;
import com.practice.StudyCenter.DTO.responseDTO.StudyCenterDTOForResponse;
import com.practice.StudyCenter.model.StudyCenter;

public class StudyCenterMapper {

    public StudyCenter toModel(StudyCenterDTOForRequest studyCenterDTOForRequest) {
        return StudyCenter.builder()
                .name(studyCenterDTOForRequest.getName())
                .phoneNumber(studyCenterDTOForRequest.getPhoneNumber())
                .build();
    }

    public StudyCenterDTOForResponse toDTO(StudyCenter studyCenter) {
        return StudyCenterDTOForResponse.builder()
                .id(studyCenter.getId())
                .name(studyCenter.getName())
                .phoneNumber(studyCenter.getPhoneNumber())
                .created_at(studyCenter.getCreated_at())
                .build();
    }
}
