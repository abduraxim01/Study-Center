package com.practice.StudyCenter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.practice.StudyCenter.model.attendance.Attendance;
import com.practice.StudyCenter.model.homework.Homework;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "group_table")
@Getter
@Setter
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

    @CreationTimestamp
    private LocalDate created_at;

    private LocalTime time;

    private boolean isAvailable;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> days;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "teacher_group",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id"))
    @JsonBackReference
    private List<Teacher> teacherList;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "student_group",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    @JsonBackReference
    private List<Student> studentList;

    @ManyToOne
    @JoinColumn(name = "study_center_id")
    @JsonBackReference
    private StudyCenter studyCenter;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Result> resultList;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Homework> homeworkList;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Attendance> attendanceList;
}
