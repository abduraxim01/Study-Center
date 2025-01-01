package com.practice.StudyCenter.service;

import com.practice.StudyCenter.DTO.requestDTO.TeacherDTOforReq;
import com.practice.StudyCenter.DTO.response.TeacherDTOforRes;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.mapper.TeacherMapper;
import com.practice.StudyCenter.repository.StudyCenterRepository;
import com.practice.StudyCenter.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teachRepository;

    @Autowired
    private StudyCenterRepository stdRepository;

    final private TeacherMapper teachMapper = new TeacherMapper();

    final private Logger logger = LogManager.getLogger(TeacherService.class);

    final private List<String> phoneOperators = List.of("90", "91");

    public TeacherDTOforRes addTeacher(TeacherDTOforReq teacherDTOforReq, int id) {
        if (phoneNumberChecker(teacherDTOforReq.getPhoneNumber())) {
            logger.error("Telefon nomer xato: from addTeacher PhoneNumber: {}", teacherDTOforReq.getPhoneNumber());
            throw new AllExceptions.IllegalArgumentException("Telefon nomer xato: " + teacherDTOforReq.getPhoneNumber());
        }
        if (teachRepository.existsTeacherByUsername(teacherDTOforReq.getUsername()) || teacherDTOforReq.getUsername().length() < 7) {
            logger.error("Username oldin ro'yhatdan o'tgan yoki 8 ta belgidan kam: from addTeacher Username: {}", teacherDTOforReq.getUsername());
            throw new AllExceptions.UsernameAlreadyTakenException("Username oldin ro'yhatdan o'tgan yoki 8 ta belgidan kam:" + teacherDTOforReq.getUsername());
        }
        if (teacherDTOforReq.getPassword().length() < 7) {
            logger.error("Password 8 belgidan kam: from addTeacher Password: {}", teacherDTOforReq.getPassword());
            throw new AllExceptions.IllegalArgumentException("Password 8 belgidan kam: " + teacherDTOforReq.getPassword());
        }
        logger.info("Yangi o'qituvchi Username: {} , O'quv markaz Id: {}", teacherDTOforReq.getUsername(), id);
        return teachMapper.toDTO(teachRepository.save(teachMapper.toModel(teacherDTOforReq, stdRepository.findStudyCenterById(id))));
    }

    public boolean phoneNumberChecker(String number) {  // if number is true  method return false , number is false method return true
        if (number.length() != 13) return true;
        return !phoneOperators.contains(number.substring(4, 6));
    }
}
