package com.practice.StudyCenter.repository;

import com.practice.StudyCenter.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByUsername(String username);

    boolean existsStudentByUsername(String username);
}
