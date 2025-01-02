package com.practice.StudyCenter.mapper;

import com.practice.StudyCenter.DTO.requestDTO.GroupDTOforReq;
import com.practice.StudyCenter.DTO.response.GroupDTOforRes;
import com.practice.StudyCenter.model.Group;

import java.util.ArrayList;
import java.util.List;


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
                .studentList(group.getStudentList())
                .build();
    }

    public List<GroupDTOforRes> toDTO(List<Group> groups) {
        if (groups == null) return new ArrayList<>();
        return groups.stream()
                .map(this::toDTO)
                .toList();
    }
}
