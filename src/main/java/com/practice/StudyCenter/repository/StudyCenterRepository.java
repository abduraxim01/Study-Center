package com.practice.StudyCenter.repository;

import com.practice.StudyCenter.model.StudyCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyCenterRepository extends JpaRepository<StudyCenter, Integer> {
    StudyCenter findStudyCenterById(int id);
}
