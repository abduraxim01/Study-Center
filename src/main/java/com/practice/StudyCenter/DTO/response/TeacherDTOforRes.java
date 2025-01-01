package com.practice.StudyCenter.DTO.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherDTOforRes {

    private String name;

    private String surname;

    private String phoneNumber;

    private String username;

    private LocalDate created_at;

    private List<GroupDTOforRes> groupList;
}
