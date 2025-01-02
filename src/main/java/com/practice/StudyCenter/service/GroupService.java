package com.practice.StudyCenter.service;

import com.practice.StudyCenter.DTO.requestDTO.*;
import com.practice.StudyCenter.DTO.response.GroupDTOforRes;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.mapper.GroupMapper;
import com.practice.StudyCenter.mapper.PaymentMapper;
import com.practice.StudyCenter.model.*;
import com.practice.StudyCenter.model.attandance.Attandance;
import com.practice.StudyCenter.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository grpRepository;

    @Autowired
    private TeacherRepository teachRepository;

    @Autowired
    private StudentRepository stdRepository;

    @Autowired
    private AttandanceRepository attRepository;

    @Autowired
    private ResultRepository rstRepository;

    @Autowired
    private PaymentRepository pymRepository;

    final private GroupMapper grpMapper = new GroupMapper();

    final private PaymentMapper pymMapper = new PaymentMapper();

    public GroupDTOforRes createGroup(GroupDTOforReq groupDTOforReq) {
        return grpMapper.toDTO(grpRepository.save(
                grpMapper.toModel(groupDTOforReq)));
    }

    public GroupDTOforRes assignTeachersToGroup(UserListAsNumber userListAsNumber, int groupId) throws AllExceptions.NoSuchElementException {
        Group group = grpRepository.findById(groupId).get();
        List<Teacher> teacherListFromReq = teachRepository.findAllById(userListAsNumber.getTeacherList());
        teacherListFromReq.stream()
                .filter(teacher -> isUserInGroup(group, teacher));
        List<Teacher> teacherListFromGroup = group.getTeacherList();
        teacherListFromGroup.addAll(teacherListFromReq);
        group.setTeacherList(teacherListFromGroup);
        return grpMapper.toDTO(grpRepository.save(group));
    }

    public boolean isUserInGroup(Group group, UserDetails user) {
        return group.getTeacherList().contains(user);
    }

    public GroupDTOforRes assignStudentToGroup(UserListAsNumber studentListAsNumber, int groupId) throws AllExceptions.NoSuchElementException {
        Group group = grpRepository.findById(groupId).get();
        List<Student> studentListFromReq = stdRepository.findAllById(studentListAsNumber.getTeacherList());
        studentListFromReq.stream()
                .filter(student -> isUserInGroup(group, student));
        List<Student> studentListFromGroup = group.getStudentList();
        studentListFromGroup.addAll(studentListFromReq);
        group.setStudentList(studentListFromGroup);
        return grpMapper.toDTO(grpRepository.save(group));
    }

    public List<Attandance> markAttandance(List<AttandanceDTOforReq> attandanceDTOforReqList, int groupId) throws AllExceptions.NoSuchElementException {
        Group group = grpRepository.findById(groupId).get();
        List<Attandance> attandanceList = new ArrayList<>();
        attandanceDTOforReqList.forEach(attandance -> attandanceList.add(Attandance.builder()
                .status(attandance.getStatus())
                .group(group)
                .student((stdRepository.findById(attandance.getStudent_id()).get()))
                .build()));
        return attRepository.saveAll(attandanceList);
    }

    public Payment markPayment(PaymentDTOforReq paymentDTOforReq) {
        return pymRepository.save(pymMapper.toModel(
                paymentDTOforReq, stdRepository.findById(paymentDTOforReq.getStudent_id()).get()));
    }

    public List<Result> postResult(List<ResultDTOforReq> resultDTOforReqList, int groupId) {
        Group group = grpRepository.findById(groupId).get();
        List<Result> resultList = new ArrayList<>();
        resultDTOforReqList.forEach(result -> resultList.add(Result.builder()
                .grade(result.getGrade())
                .group(group)
                .student((stdRepository.findById(result.getStudent_id()).get()))
                .build()));
        return rstRepository.saveAll(resultList);
    }
}
