package com.practice.StudyCenter.mapper;

import com.practice.StudyCenter.DTO.requestDTO.StudyCenterDTOforReq;
import com.practice.StudyCenter.DTO.response.StudyCenterDTOforRes;
import com.practice.StudyCenter.model.StudyCenter;

public class StudyCenterMapper {

    public StudyCenter toModel(StudyCenterDTOforReq studyCenterDTOforReq) {
        return StudyCenter.builder()
                .name(studyCenterDTOforReq.getName())
                .phoneNumber(studyCenterDTOforReq.getPhoneNumber())
                .build();
    }

    public StudyCenterDTOforRes toDTO(StudyCenter studyCenter){
        return StudyCenterDTOforRes.builder()
                .name(studyCenter.getName())
                .phoneNumber(studyCenter.getPhoneNumber())
                .created_at(studyCenter.getCreated_at())
                .build();
    }
}
