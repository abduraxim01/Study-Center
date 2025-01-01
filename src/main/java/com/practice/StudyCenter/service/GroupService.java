package com.practice.StudyCenter.service;

import com.practice.StudyCenter.DTO.requestDTO.GroupDTOforReq;
import com.practice.StudyCenter.DTO.requestDTO.TeacherListAsNumber;
import com.practice.StudyCenter.DTO.response.GroupDTOforRes;
import com.practice.StudyCenter.mapper.GroupMapper;
import com.practice.StudyCenter.model.Group;
import com.practice.StudyCenter.model.Teacher;
import com.practice.StudyCenter.repository.GroupRepository;
import com.practice.StudyCenter.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository grpRepository;

    @Autowired
    private TeacherRepository teachRepository;

    final private GroupMapper grpMapper = new GroupMapper();

    public GroupDTOforRes addGroup(GroupDTOforReq groupDTOforReq) {
        return grpMapper.toDTO(grpRepository.save(
                grpMapper.toModel(groupDTOforReq)));
    }

    public GroupDTOforRes assignTeachersToGroup(TeacherListAsNumber teacherListAsNumber, int groupId) {
        List<Teacher> teachers = teachRepository.findAllById(teacherListAsNumber.getTeacherList());
        Group group = grpRepository.findById(groupId).get();
        List<Teacher> teacherList = group.getTeacherList();
        teacherList.addAll(teachers);
        group.setTeacherList(teacherList);
        return grpMapper.toDTO(grpRepository.save(group));
    }
}
