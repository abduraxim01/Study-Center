package com.practice.StudyCenter.service;

import com.practice.StudyCenter.DTO.requestDTO.AttendanceDTOForRequest;
import com.practice.StudyCenter.DTO.responseDTO.AttendanceDTOForResponse;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.mapper.AttendanceMapper;
import com.practice.StudyCenter.model.Group;
import com.practice.StudyCenter.model.Student;
import com.practice.StudyCenter.model.attendance.Attendance;
import com.practice.StudyCenter.model.attendance.AttendanceStatus;
import com.practice.StudyCenter.repository.AttendanceRepository;
import com.practice.StudyCenter.repository.GroupRepository;
import com.practice.StudyCenter.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<AttendanceDTOForResponse> postAttendance(List<AttendanceDTOForRequest> attendanceDTOForRequestList, int groupId) throws AllExceptions.NoSuchElementException {
        Group group = grpRepository.findById(groupId).orElseThrow(() -> new AllExceptions.EntityNotFoundException("Group topilmadi Id: " + groupId));
        List<Attendance> attendanceList = new ArrayList<>();
        attendanceDTOForRequestList.forEach(attendance -> attendanceList.add(Attendance.builder()
                .status(AttendanceStatus.valueOf(attendance.getStatus()))
                .group(group)
                .student((stdRepository.findById(attendance.getStudent_id()).orElseThrow(() -> new AllExceptions.EntityNotFoundException("Student topilmadi Id: " + attendance.getStudent_id()))))
                .build()));
        return attMapper.toDTO(attRepository.saveAll(attendanceList));
    }

    public List<AttendanceDTOForResponse> getAttendanceByGroupId(int groupId) {
        Group group = grpRepository.findById(groupId).orElseThrow(() -> new AllExceptions.EntityNotFoundException("Group topilmadi Id: " + groupId));
        return attMapper.toDTO(group.getAttendanceList());
    }

    public List<AttendanceDTOForResponse> getAttendanceByGroupAndStudentId(int groupId, int student_id) {
        Student student = stdRepository.findById(student_id).orElseThrow(() -> new AllExceptions.EntityNotFoundException("Student topilmadi Id: " + groupId));
        Group group = grpRepository.findById(groupId).orElseThrow(() -> new AllExceptions.EntityNotFoundException("Group topilmadi Id: " + groupId));
        List<Attendance> attendanceList = group.getAttendanceList();
        attendanceList.removeIf(attendance -> !student.equals(attendance.getStudent()));
        return attMapper.toDTO(attendanceList);
    }

    public AttendanceDTOForResponse updateAttendance(AttendanceDTOForRequest attendanceDTO, int attendanceId) {
        Attendance attendance = attRepository.findById(attendanceId).orElseThrow(() -> new AllExceptions.EntityNotFoundException("Attendance topilmadi Id: " + attendanceId));
        if (stdRepository.existsById(attendanceDTO.getStudent_id())) {
            attendance.setStatus(AttendanceStatus.valueOf(attendanceDTO.getStatus()));
            return attMapper.toDTO(attRepository.save(attendance));
        } else
            throw new AllExceptions.EntityNotFoundException("Student topilmadi Id: " + attendanceDTO.getStudent_id());
    }

    public void softDeleteAttendance(int attendanceId) {
        if (attRepository.existsById(attendanceId)) attRepository.deleteById(attendanceId);
        else throw new AllExceptions.EntityNotFoundException("Attendance topilmadi Id: " + attendanceId);
    }
}
