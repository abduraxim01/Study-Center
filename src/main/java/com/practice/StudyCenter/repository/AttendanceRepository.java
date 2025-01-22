package com.practice.StudyCenter.repository;

import com.practice.StudyCenter.model.attendance.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {
}
