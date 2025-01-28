package com.practice.StudyCenter.service;

import com.practice.StudyCenter.DTO.requestDTO.*;
import com.practice.StudyCenter.DTO.responseDTO.GroupDTOForResponse;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.mapper.GroupMapper;
import com.practice.StudyCenter.mapper.StudentMapper;
import com.practice.StudyCenter.model.*;
import com.practice.StudyCenter.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository grpRepository;

    @Autowired
    private TeacherRepository teachRepository;

    @Autowired
    private StudyCenterRepository stcRepository;

    @Autowired
    private StudentRepository stdRepository;

//    @Autowired
//    private AttendanceRepository attRepository;

//    @Autowired
//    private ResultRepository rstRepository;

//    @Autowired
//    private PaymentRepository pymRepository;

    final private GroupMapper grpMapper = new GroupMapper();

//    final private PaymentMapper pymMapper = new PaymentMapper();

    final private StudentMapper stdMapper = new StudentMapper();

//    Pageable pageable = PageRequest.of(0, 10);

    public GroupDTOForResponse createGroup(GroupDTOForRequest groupDTOForRequest, int studyCenterId) {
        StudyCenter studyCenter = stcRepository.findById(studyCenterId).orElseThrow(() -> new AllExceptions.EntityNotFoundException("StudyCenter topilmadi Id: " + studyCenterId));
        return grpMapper.toDTO(grpRepository.save(
                grpMapper.toModel(groupDTOForRequest, studyCenter)));
    }

    public List<GroupDTOForResponse> getGroupsByStudentId(int student_id) {
        Student student = stdRepository.findById(student_id).orElseThrow(() -> new AllExceptions.EntityNotFoundException("Student topilmadi Id: " + student_id));
        return grpMapper.toDTO(student.getGroupList());
    }
//    public Page<?> getGroupsByStudyCenterId(int teacher_id) {
//        List<Group> groups = teachRepository.findById(teacher_id).get().getGroupList();
//        int start = (int) pageable.getOffset();
//        int end = Math.min((start + pageable.getPageSize()), groups.size());
//        return new PageImpl<>(groups.subList(start, end), pageable, groups.size());
//    }

    public GroupDTOForResponse deleteGroup(int groupId) {
        Group group = grpRepository.findById(groupId).orElseThrow(() -> new AllExceptions.EntityNotFoundException("Group topilmadi Id: " + groupId));
        group.setAvailable(false);
        return grpMapper.toDTO(grpRepository.save(group));
    }

    public GroupDTOForResponse restoreGroup(int groupId) {
        Group group = grpRepository.findById(groupId).orElseThrow(() -> new AllExceptions.EntityNotFoundException("Group topilmadi Id: " + groupId));
        group.setAvailable(true);
        return grpMapper.toDTO(grpRepository.save(group));
    }

    public void softDeleteGroup(int groupId) {
        grpRepository.findById(groupId).orElseThrow(() -> new AllExceptions.EntityNotFoundException("Group topilmadi Id: " + groupId));
        grpRepository.deleteById(groupId);
    }


    public void assignTeachersToGroup(IdsList idsList, int groupId) throws AllExceptions.NoSuchElementException {
        Group group = grpRepository.findById(groupId).orElseThrow(() -> new AllExceptions.EntityNotFoundException("Group topilmadi Id: " + groupId));
        List<Teacher> teacherListFromReq = teachRepository.findAllById(idsList.getIdsList());
        teacherListFromReq.stream()
                .filter(teacher -> isUserInGroup(group, teacher));
        List<Teacher> teacherListFromGroup = group.getTeacherList();
        teacherListFromGroup.addAll(teacherListFromReq);
        group.setTeacherList(teacherListFromGroup);
        grpRepository.save(group);
    }

    public boolean isUserInGroup(Group group, UserDetails user) {
        return group.getTeacherList().contains(user);
    }
//
//    public GroupDTOforRes assignStudentToGroup(UserListAsNumber studentListAsNumber, int groupId) throws AllExceptions.NoSuchElementException {
//        Group group = grpRepository.findById(groupId).get();
//        List<Student> studentListFromReq = stdRepository.findAllById(studentListAsNumber.getTeacherList());
//        studentListFromReq.stream()
//                .filter(student -> isUserInGroup(group, student));
//        List<Student> studentListFromGroup = group.getStudentList();
//        studentListFromGroup.addAll(studentListFromReq);
//        group.setStudentList(studentListFromGroup);
//        return grpMapper.toDTO(grpRepository.save(group));
//    }

//    public List<Attendance> markAttendance(List<AttendanceDTOforReq> attendanceDTOforReqList, int groupId) throws AllExceptions.NoSuchElementException {
//        Group group = grpRepository.findById(groupId).get();
//        List<Attendance> attendanceList = new ArrayList<>();
//        attendanceDTOforReqList.forEach(attandance -> attendanceList.add(Attendance.builder()
//                .status(attandance.getStatus())
//                .group(group)
//                .student((stdRepository.findById(attandance.getStudent_id()).get()))
//                .build()));
//        return attRepository.saveAll(attendanceList);
//    }

//    public Payment markPayment(PaymentDTOforReq paymentDTOforReq) {
//        return pymRepository.save(pymMapper.toModel(
//                paymentDTOforReq, stdRepository.findById(paymentDTOforReq.getStudent_id()).get()));
//    }

//    public List<Result> postResult(List<ResultDTOforReq> resultDTOforReqList, int groupId) {
//        Group group = grpRepository.findById(groupId).get();
//        List<Result> resultList = new ArrayList<>();
//        resultDTOforReqList.forEach(result -> resultList.add(Result.builder()
//                .grade(result.getGrade())
//                .group(group)
//                .student((stdRepository.findById(result.getStudent_id()).get()))
//                .build()));
//        return rstRepository.saveAll(resultList);
//    }
}
