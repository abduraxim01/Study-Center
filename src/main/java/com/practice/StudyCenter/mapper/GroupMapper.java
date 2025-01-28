package com.practice.StudyCenter.mapper;

import com.practice.StudyCenter.DTO.requestDTO.GroupDTOForRequest;
import com.practice.StudyCenter.DTO.responseDTO.GroupDTOForResponse;
import com.practice.StudyCenter.model.Group;
import com.practice.StudyCenter.model.StudyCenter;

import java.util.ArrayList;
import java.util.List;


public class GroupMapper {

    public Group toModel(GroupDTOForRequest groupDTOForRequest, StudyCenter studyCenter) {
        return Group.builder()
                .name(groupDTOForRequest.getName())
                .time(groupDTOForRequest.getTime())
                .days(groupDTOForRequest.getDays())
                .studyCenter(studyCenter)
                .isAvailable(true)
                .build();
    }

    public GroupDTOForResponse toDTO(Group group) {
        return GroupDTOForResponse.builder()
                .id(group.getId())
                .name(group.getName())
                .time(group.getTime())
                .days(group.getDays())
                .isAvailable(group.isAvailable())
                .created_at(group.getCreated_at())
                .teacherList(group.getTeacherList())
                .studentList(group.getStudentList())
                .build();
    }

    public List<GroupDTOForResponse> toDTO(List<Group> groups) {
        if (groups == null) return new ArrayList<>();
        return groups.stream()
                .map(this::toDTO)
                .toList();
    }
}
