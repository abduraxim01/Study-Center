package com.practice.StudyCenter.DTO.response;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.practice.StudyCenter.model.Result;
import com.practice.StudyCenter.model.Student;
import com.practice.StudyCenter.model.Teacher;
import com.practice.StudyCenter.model.attandance.Attandance;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

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

    private List<Result> resultList;

    private List<Attandance> attandanceList;

    private List<Student> studentList;
}
