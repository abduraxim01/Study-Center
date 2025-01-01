package com.practice.StudyCenter.repository;

import com.practice.StudyCenter.model.attandance.Attandance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttandanceRepository extends JpaRepository<Attandance, Integer> {
}
