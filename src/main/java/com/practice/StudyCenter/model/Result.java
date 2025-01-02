package com.practice.StudyCenter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @CreationTimestamp
    private LocalDate localDate;

    private Float grade;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonBackReference
    private Student student;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @JsonBackReference
    private Group group;
}
