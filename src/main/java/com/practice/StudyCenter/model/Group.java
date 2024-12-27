package com.practice.StudyCenter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "group_table")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Valid
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 5, message = "Name must be at least 5 characters")
    private String name;

    private String phoneNumber;

    private LocalTime time;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> days;

    @ManyToMany(mappedBy = "groupList")
    @ToString.Exclude
    @JsonBackReference
    private List<Teacher> teacherList;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @JsonManagedReference
    private List<Result> resultList;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @JsonManagedReference
    private List<Attandance> attandanceList;

    @ManyToMany(mappedBy = "groupList")
    @ToString.Exclude
    @JsonBackReference
    private List<Student> studentList;
}
