package com.practice.StudyCenter.DTO.response;

import com.practice.StudyCenter.model.Student;
import com.practice.StudyCenter.model.Teacher;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GroupDTOforRes {

    private String name;

    private LocalDate created_at;

    private LocalTime time;

    private Set<DayOfWeek> days;

    private List<Teacher> teacherList;

    private List<Student> studentList;
}
