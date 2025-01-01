package com.practice.StudyCenter.service;

import com.practice.StudyCenter.DTO.requestDTO.AttandanceDTOforReq;
import com.practice.StudyCenter.DTO.requestDTO.GroupDTOforReq;
import com.practice.StudyCenter.DTO.requestDTO.UserListAsNumber;
import com.practice.StudyCenter.DTO.response.GroupDTOforRes;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.mapper.GroupMapper;
import com.practice.StudyCenter.model.Group;
import com.practice.StudyCenter.model.Student;
import com.practice.StudyCenter.model.Teacher;
import com.practice.StudyCenter.model.attandance.Attandance;
import com.practice.StudyCenter.repository.AttandanceRepository;
import com.practice.StudyCenter.repository.GroupRepository;
import com.practice.StudyCenter.repository.StudentRepository;
import com.practice.StudyCenter.repository.TeacherRepository;
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

    final private GroupMapper grpMapper = new GroupMapper();

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

}
