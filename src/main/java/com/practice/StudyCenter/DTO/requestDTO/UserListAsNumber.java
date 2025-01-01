package com.practice.StudyCenter.DTO.requestDTO;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserListAsNumber {
    List<Integer> teacherList;
}
