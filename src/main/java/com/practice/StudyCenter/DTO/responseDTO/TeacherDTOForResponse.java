package com.practice.StudyCenter.DTO.responseDTO;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherDTOForResponse {

    private int id;

    private String name;

    private String surname;

    private String phoneNumber;

    private String username;

    private LocalDate created_at;

    private List<GroupDTOForResponse> groupList;
}
