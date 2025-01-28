package com.practice.StudyCenter.mapper;

import com.practice.StudyCenter.DTO.responseDTO.HomeworkDTOForResponse;
import com.practice.StudyCenter.model.homework.Homework;

import java.util.List;

public class HomeworkMapper {

    public HomeworkDTOForResponse toDTO(Homework homework) {
        return HomeworkDTOForResponse.builder()
                .name(homework.getStudent().getName())
                .status(homework.getStatus())
                .time(homework.getLocalDate())
                .build();

    }

    public List<HomeworkDTOForResponse> toDTO(List<Homework> homeworkList) {
        if (homeworkList == null || homeworkList.isEmpty()) return null;
        return homeworkList.stream()
                .map(this::toDTO)
                .toList();
    }
}
