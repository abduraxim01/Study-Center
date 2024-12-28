package com.practice.StudyCenter.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.practice.StudyCenter.model.attandance.Attandance;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(min = 5, message = "Name must be at least 5 characters")
    private String name;

    @Size(min = 5, message = "Surname must be at least 5 characters")
    private String surname;

    private String phoneNumber;

    @Size(min = 8, message = "Username must be at least 8 characters")
    private String username;

    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    private String nameOfParent;

    @CreationTimestamp
    private LocalDate created_at;

    @Enumerated(EnumType.STRING)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "study_center_id")
    private StudyCenter studyCenter;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @JsonManagedReference
    private List<Attandance> attandanceList;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    @JsonManagedReference
    private List<Result> resultList;

    @ManyToMany
    @JoinTable(name = "student_group",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    @ToString.Exclude
    @JsonManagedReference
    private List<Group> groupList;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+role.name()));
    }
}