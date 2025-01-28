package com.practice.StudyCenter.mapper;

import com.practice.StudyCenter.DTO.responseDTO.ResultDTOForResponse;
import com.practice.StudyCenter.model.Result;

import java.util.List;

public class ResultMapper {

    public ResultDTOForResponse toDTO(Result result) {
        return ResultDTOForResponse.builder()
                .id(result.getId())
                .grade(result.getGrade())
                .localDate(result.getLocalDate())
                .build();
    }

    public List<ResultDTOForResponse> toDTO(List<Result> resultList) {
        if (resultList.isEmpty()) return null;
        return resultList.stream()
                .map(this::toDTO)
                .toList();
    }
}
