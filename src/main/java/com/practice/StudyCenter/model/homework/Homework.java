package com.practice.StudyCenter.model.homework;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.practice.StudyCenter.model.Group;
import com.practice.StudyCenter.model.Student;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Homework {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    private LocalDate localDate;

    @Enumerated(EnumType.STRING)
    private HomeworkStatus status;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private Student student;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @JsonBackReference
    private Group group;
}
