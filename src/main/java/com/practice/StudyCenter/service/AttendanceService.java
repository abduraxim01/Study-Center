package com.practice.StudyCenter.service;

import com.practice.StudyCenter.DTO.requestDTO.AttendanceDTOforReq;
import com.practice.StudyCenter.DTO.response.AttendanceDTOForResponce;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.mapper.AttendanceMapper;
import com.practice.StudyCenter.model.Group;
import com.practice.StudyCenter.model.Student;
import com.practice.StudyCenter.model.attendance.Attendance;
import com.practice.StudyCenter.repository.AttendanceRepository;
import com.practice.StudyCenter.repository.GroupRepository;
import com.practice.StudyCenter.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceService {

    @Autowired
    private GroupRepository grpRepository;

    @Autowired
    private AttendanceRepository attRepository;

    @Autowired
    private StudentRepository stdRepository;

    final private AttendanceMapper attMapper = new AttendanceMapper();

    public List<Attendance> markAttendance(List<AttendanceDTOforReq> attendanceDTOforReqList, int groupId) throws AllExceptions.NoSuchElementException {
        Group group = grpRepository.findById(groupId).get();
        List<Attendance> attendanceList = new ArrayList<>();
        attendanceDTOforReqList.forEach(attandance -> attendanceList.add(Attendance.builder()
                .status(attandance.getStatus())
                .group(group)
                .student((stdRepository.findById(attandance.getStudent_id()).get()))
                .build()));
        return attRepository.saveAll(attendanceList);
    }

    public List<?> getAttendanceByGroupId(int groupId) {
        return grpRepository.findById(groupId).get().getAttendanceList();
    }

    public List<AttendanceDTOForResponce> getAttendanceByGroupAndStudentId(int group_id, int student_id) {
        Student student =  stdRepository.findById(student_id).get();
        List<Attendance> attendanceList = grpRepository.findById(group_id).get().getAttendanceList();
        attendanceList.removeIf(attendance ->
                attendance.getStudent() == null || !student.equals(attendance.getStudent()));
        return attMapper.toDTO(attendanceList);
    }
}
