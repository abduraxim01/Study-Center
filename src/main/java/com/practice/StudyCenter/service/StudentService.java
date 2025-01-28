package com.practice.StudyCenter.service;

import com.practice.StudyCenter.DTO.requestDTO.StudentDTOForRequest;
import com.practice.StudyCenter.DTO.requestDTO.IdsList;
import com.practice.StudyCenter.DTO.responseDTO.PaymentDTOForResponse;
import com.practice.StudyCenter.DTO.responseDTO.StudentDTOForResponse;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.mapper.PaymentMapper;
import com.practice.StudyCenter.mapper.StudentMapper;
import com.practice.StudyCenter.model.Group;
import com.practice.StudyCenter.model.Student;
import com.practice.StudyCenter.model.StudyCenter;
import com.practice.StudyCenter.repository.GroupRepository;
import com.practice.StudyCenter.repository.StudentRepository;
import com.practice.StudyCenter.repository.StudyCenterRepository;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository stdRepository;

    @Autowired
    private StudyCenterRepository stcRepository;

    @Autowired
    private GroupRepository grpRepository;

    final private Logger logger = LogManager.getLogger(StudentService.class);

    final private StudentMapper stdMapper = new StudentMapper();

    final private PaymentMapper pymMapper = new PaymentMapper();

    public Student createStudent(StudentDTOForRequest studentDTOForRequest, int study_center_id) {
        if (isValidPhoneNumber(studentDTOForRequest.getPhoneNumber())) {
            logger.error("Telefon nomer xato: from createStudent PhoneNumber: {}", studentDTOForRequest.getPhoneNumber());
            throw new AllExceptions.IllegalArgumentException("Telefon nomer xato: " + studentDTOForRequest.getPhoneNumber());
        }
        if (stdRepository.existsStudentByUsername(studentDTOForRequest.getUsername()) || studentDTOForRequest.getUsername().length() < 7) {
            logger.error("Username oldin ro'yhatdan o'tgan yoki 8 ta belgidan kam: from createStudent Username: {}", studentDTOForRequest.getUsername());
            throw new AllExceptions.UsernameAlreadyTakenException("Username oldin ro'yhatdan o'tgan yoki 8 ta belgidan kam:" + studentDTOForRequest.getUsername());
        }
        if (studentDTOForRequest.getPassword().length() < 7) {
            logger.error("Password 8 belgidan kam: from createStudent Password: {}", studentDTOForRequest.getPassword());
            throw new AllExceptions.IllegalArgumentException("Password 8 belgidan kam: " + studentDTOForRequest.getPassword());
        }
        StudyCenter studyCenter = stcRepository.findStudyCenterById(study_center_id);
        if (studyCenter == null) {
            logger.error("O'quv markazi topilmadi Id: {}", study_center_id);
            throw new AllExceptions.EntityNotFoundException("O'quv markazi topilmadi Id: " + study_center_id);
        }
        logger.info("Yangi talaba Username: {} , O'quv markaz Id: {}", studentDTOForRequest.getUsername(), study_center_id);
        return stdRepository.save(stdMapper.toModel(studentDTOForRequest, studyCenter));
    }

    public boolean isUserInGroup(Group group, UserDetails user) {
        return group.getTeacherList().contains(user);
    }

    public void assignStudentToGroup(IdsList studentListAsNumber, int groupId) throws AllExceptions.NoSuchElementException {
        Group group = grpRepository.findById(groupId).orElseThrow(() -> new AllExceptions.EntityNotFoundException("Group topilmadi Id: " + groupId));
        List<Student> studentListFromReq = stdRepository.findAllById(studentListAsNumber.getIdsList());
        studentListFromReq.stream()
                .filter(student -> isUserInGroup(group, student));
        List<Student> studentListFromGroup = group.getStudentList();
        studentListFromGroup.addAll(studentListFromReq);
        group.setStudentList(studentListFromGroup);
        grpRepository.save(group);
    }

    public List<?> getStudentsByGroupId(int groupId) {
        Group group = grpRepository.findById(groupId).orElseThrow(() -> new AllExceptions.EntityNotFoundException("Group topilmadi Id: " + groupId));
        return group.getStudentList().stream()
                .map(stdMapper::toDTO)
                .toList();
    }

    public List<?> getStudentsByStudyCenterId(int study_center_id) {
        StudyCenter studyCenter = stcRepository.findById(study_center_id).orElseThrow(() -> new AllExceptions.EntityNotFoundException("StudyCenter topilmadi Id: " + study_center_id));
        return studyCenter.getStudentList().stream()
                .map(stdMapper::toDTO)
                .toList();
    }

    public StudentDTOForResponse deleteStudent(int student_id) {
        Student student = stdRepository.findById(student_id).orElseThrow(() -> new AllExceptions.EntityNotFoundException("Student topilmadi Id: " + student_id));
        student.setAvailable(false);
        return stdMapper.toDTO(stdRepository.save(student));
    }

    public void softDeleteStudent(int student_id) {
        if (stdRepository.existsById(student_id)) {
            stdRepository.deleteById(student_id);
        } else {
            throw new AllExceptions.EntityNotFoundException("Student topilmadi Id: " + student_id);
        }
    }

    public StudentDTOForResponse restoreStudent(int student_id) {
        Student student = stdRepository.findById(student_id).orElseThrow(() -> new AllExceptions.EntityNotFoundException("Student topilmadi Id: " + student_id));
        student.setAvailable(true);
        return stdMapper.toDTO(stdRepository.save(student));
    }

//    public List<GroupDTOForResponse> getGroupsByStudentId(int student_id) {
//        Student student = stdRepository.findById(student_id).orElseThrow(() -> new AllExceptions.EntityNotFoundException("Student topilmadi Id: " + student_id));
//        return grpMapper.toDTO(student.getGroupList());
//    }

    public List<PaymentDTOForResponse> getPayments(int student_id) {
        Student student = stdRepository.findById(student_id).orElseThrow(() -> new AllExceptions.EntityNotFoundException("Student topilmadi Id: " + student_id));
        return pymMapper.toDTO(student.getPaymentList());
    }


    public boolean isValidPhoneNumber(String phoneNumber) { // if number is true  method return false , number is false method return true
        String regex = "^\\+998(90|91|92|94|95)\\d{7}$";
        return !phoneNumber.trim().matches(regex);
    }
}
