package com.practice.StudyCenter.service;

import com.practice.StudyCenter.DTO.requestDTO.ResultDTOforReq;
import com.practice.StudyCenter.model.Group;
import com.practice.StudyCenter.model.Result;
import com.practice.StudyCenter.repository.GroupRepository;
import com.practice.StudyCenter.repository.ResultRepository;
import com.practice.StudyCenter.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultService {

    @Autowired
    private GroupRepository grpRepository;

    @Autowired
    private StudentRepository stdRepository;

    @Autowired
    private ResultRepository rstRepository;

    public List<Result> postResult(List<ResultDTOforReq> resultDTOforReqList, int groupId) {
        Group group = grpRepository.findById(groupId).get();
        List<Result> resultList = new ArrayList<>();
        resultDTOforReqList.forEach(result -> resultList.add(Result.builder()
                .grade(result.getGrade())
                .group(group)
                .student((stdRepository.findById(result.getStudent_id()).get()))
                .build()));
        return rstRepository.saveAll(resultList);
    }

    public List<?> getResultByGroupId(int groupId) {
        return grpRepository.findById(groupId).get().getResultList();
    }
}
