package com.practice.StudyCenter.service;

import com.practice.StudyCenter.DTO.requestDTO.StudentDTOforReq;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.mapper.StudentMapper;
import com.practice.StudyCenter.model.Student;
import com.practice.StudyCenter.model.StudyCenter;
import com.practice.StudyCenter.repository.StudentRepository;
import com.practice.StudyCenter.repository.StudyCenterRepository;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.LogManager;

@Service
public class StudentService {

    @Autowired
    private StudentRepository stdRepository;

    @Autowired
    private StudyCenterRepository stcRepository;

    final private Logger logger = LogManager.getLogger(StudentService.class);

    final private StudentMapper stdMapper = new StudentMapper();

    public Student createStudent(StudentDTOforReq studentDTOforReq, int study_center_id) {
        if (isValidPhoneNumber(studentDTOforReq.getPhoneNumber())) {
            logger.error("Telefon nomer xato: from createStudent PhoneNumber: {}", studentDTOforReq.getPhoneNumber());
            throw new AllExceptions.IllegalArgumentException("Telefon nomer xato: " + studentDTOforReq.getPhoneNumber());
        }
        if (stdRepository.existsStudentByUsername(studentDTOforReq.getUsername()) || studentDTOforReq.getUsername().length() < 7) {
            logger.error("Username oldin ro'yhatdan o'tgan yoki 8 ta belgidan kam: from createStudent Username: {}", studentDTOforReq.getUsername());
            throw new AllExceptions.UsernameAlreadyTakenException("Username oldin ro'yhatdan o'tgan yoki 8 ta belgidan kam:" + studentDTOforReq.getUsername());
        }
        if (studentDTOforReq.getPassword().length() < 7) {
            logger.error("Password 8 belgidan kam: from createStudent Password: {}", studentDTOforReq.getPassword());
            throw new AllExceptions.IllegalArgumentException("Password 8 belgidan kam: " + studentDTOforReq.getPassword());
        }
        StudyCenter studyCenter = stcRepository.findStudyCenterById(study_center_id);
        if (studyCenter == null) {
            logger.error("O'quv markazi topilmadi Id: {}", study_center_id);
            throw new AllExceptions.EntityNotFoundException("O'quv markazi topilmadi Id: " + study_center_id);
        }
        logger.info("Yangi talaba Username: {} , O'quv markaz Id: {}", studentDTOforReq.getUsername(), study_center_id);
        return stdRepository.save(stdMapper.toModel(studentDTOforReq, studyCenter));
    }

    public boolean isValidPhoneNumber(String phoneNumber) { // if number is true  method return false , number is false method return true
        String regex = "^\\+998(90|91|92|94|95)\\d{7}$";
        return !phoneNumber.trim().matches(regex);
    }
}
