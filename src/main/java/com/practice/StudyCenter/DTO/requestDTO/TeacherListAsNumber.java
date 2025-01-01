package com.practice.StudyCenter.DTO.requestDTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeacherListAsNumber {
    List<Integer> teacherList;
}
