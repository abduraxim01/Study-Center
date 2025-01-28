package com.practice.StudyCenter.service;

import com.practice.StudyCenter.DTO.requestDTO.IdsList;
import com.practice.StudyCenter.DTO.requestDTO.ResultDTOForRequest;
import com.practice.StudyCenter.DTO.responseDTO.ResultDTOForResponse;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.mapper.ResultMapper;
import com.practice.StudyCenter.model.Group;
import com.practice.StudyCenter.model.Payment;
import com.practice.StudyCenter.model.Result;
import com.practice.StudyCenter.repository.GroupRepository;
import com.practice.StudyCenter.repository.ResultRepository;
import com.practice.StudyCenter.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ResultService {

    @Autowired
    private GroupRepository grpRepository;

    @Autowired
    private StudentRepository stdRepository;

    @Autowired
    private ResultRepository rstRepository;

    final private ResultMapper rstMapper = new ResultMapper();

    public List<Result> postResult(List<ResultDTOForRequest> resultDTOForRequestList, int groupId) {
        Group group = grpRepository.findById(groupId).orElseThrow(() -> new AllExceptions.EntityNotFoundException("Group topilamdi Id: " + groupId));
        List<Result> resultList = new ArrayList<>();
        resultDTOForRequestList.forEach(result -> resultList.add(Result.builder()
                .grade(result.getGrade())
                .group(group)
                .student((stdRepository.findById(result.getStudent_id()).orElseThrow(() -> new AllExceptions.EntityNotFoundException("Student topilmadi Id: " + result.getStudent_id()))))
                .build()));
        return rstRepository.saveAll(resultList);
    }

    public List<?> getResultByGroupId(int groupId) {
        Group group = grpRepository.findById(groupId).orElseThrow(() -> new AllExceptions.EntityNotFoundException("Group topilamdi Id: " + groupId));
        return group.getResultList();
    }

    public void softDeleteResult(IdsList idsList) {
        idsList.getIdsList().forEach(id -> rstRepository.findById(id).orElseThrow(() -> new AllExceptions.EntityNotFoundException("Result topilmadi Id: " + id)));
        idsList.getIdsList().forEach(id -> rstRepository.deleteById(id));
    }

    public List<ResultDTOForResponse> updateResult(Map<Integer, Float> updatedResultList) {
        List<Result> resultList = new ArrayList<>();
        updatedResultList.keySet().forEach(resultId -> resultList.add(rstRepository.findById(resultId).orElseThrow(() -> new AllExceptions.EntityNotFoundException("Result topilmadi Id: " + resultId))));
        resultList.forEach(result -> result.setGrade(updatedResultList.get(result.getId())));
        return rstMapper.toDTO(rstRepository.saveAll(resultList));
    }
}
