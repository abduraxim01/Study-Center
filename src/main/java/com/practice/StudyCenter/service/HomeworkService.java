package com.practice.StudyCenter.service;

import com.practice.StudyCenter.DTO.requestDTO.HomeworkDTOForRequest;
import com.practice.StudyCenter.DTO.responseDTO.HomeworkDTOForResponse;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.mapper.HomeworkMapper;
import com.practice.StudyCenter.model.Group;
import com.practice.StudyCenter.model.homework.Homework;
import com.practice.StudyCenter.model.homework.HomeworkStatus;
import com.practice.StudyCenter.repository.GroupRepository;
import com.practice.StudyCenter.repository.HomeworkRepository;
import com.practice.StudyCenter.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeworkService {

    @Autowired
    private HomeworkRepository homRepository;

    @Autowired
    private StudentRepository stdRepository;

    @Autowired
    private GroupRepository grpRepository;

    final private HomeworkMapper homMapper = new HomeworkMapper();

    public List<HomeworkDTOForResponse> postHomework(List<HomeworkDTOForRequest> homeworkDTOForRequestList, int groupId) {
        Group group = grpRepository.findById(groupId).orElseThrow(() -> new AllExceptions.EntityNotFoundException("Group topilmadi Id: " + groupId));
        List<Homework> homeworkList = homeworkDTOForRequestList.stream()
                .map(homework -> Homework.builder()
                        .student(stdRepository.findById(
                                homework.getStudent_id()).orElseThrow(() -> new AllExceptions.EntityNotFoundException("Student topilmadi Id: " + homework.getStudent_id())))
                        .group(group)
                        .status(HomeworkStatus.valueOf(String.valueOf(homework.getStatus())))
                        .build())
                .toList();
        return homMapper.toDTO(homRepository.saveAll(homeworkList));
    }

    public List<HomeworkDTOForResponse> getHomeworksByGroupId(int groupId) {
        Group group = grpRepository.findById(groupId).orElseThrow(() -> new AllExceptions.EntityNotFoundException("Group topilmadi Id: " + groupId));
        return homMapper.toDTO(homRepository.findAll().stream()
                .filter(homework -> homework.getGroup().equals(group))
                .toList());
    }
}
