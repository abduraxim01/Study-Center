package com.practice.StudyCenter.mapper;

import com.practice.StudyCenter.DTO.requestDTO.GroupDTOforReq;
import com.practice.StudyCenter.DTO.response.GroupDTOforRes;
import com.practice.StudyCenter.model.Group;
import com.practice.StudyCenter.model.Teacher;


public class GroupMapper {

    public Group toModel(GroupDTOforReq groupDTOforReq) {
        return Group.builder()
                .name(groupDTOforReq.getName())
                .time(groupDTOforReq.getTime())
                .days(groupDTOforReq.getDays())
                .build();
    }

    public GroupDTOforRes toDTO(Group group) {
        return GroupDTOforRes.builder()
                .name(group.getName())
                .time(group.getTime())
                .days(group.getDays())
                .teacherList(group.getTeacherList())
                .attandanceList(group.getAttandanceList())
                .resultList(group.getResultList())
                .studentList(group.getStudentList())
                .build();
    }
}
