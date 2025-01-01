package com.practice.StudyCenter.model.attandance;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.practice.StudyCenter.model.Group;
import com.practice.StudyCenter.model.Student;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Attandance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDateTime dateTime;

    private AttandanceStatus status;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private Student student;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @JsonBackReference
    private Group group;
}