package com.practice.StudyCenter.repository;

import com.practice.StudyCenter.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    Teacher findTeacherByUsername(String username);

    boolean existsTeacherByUsername(String username);
}
