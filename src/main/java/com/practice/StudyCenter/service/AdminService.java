package com.practice.StudyCenter.service;

import com.practice.StudyCenter.DTO.requestDTO.StudyCenterDTOforReq;
import com.practice.StudyCenter.DTO.response.StudyCenterDTOforRes;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.mapper.StudyCenterMapper;
import com.practice.StudyCenter.repository.StudyCenterRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class AdminService {

    final private Logger logger = LogManager.getLogger(AdminService.class);

    private StudyCenterMapper studyCenterMapper = new StudyCenterMapper();

    private StudyCenterRepository repository;

    @Autowired
    private TeacherService teachService;

    @Autowired
    public AdminService(StudyCenterRepository repository) {
        this.repository = repository;
    }

    public StudyCenterDTOforRes addStudyCenter(StudyCenterDTOforReq studyCenterDTOforReq) {
        if (studyCenterDTOforReq.getPhoneNumber() == null || studyCenterDTOforReq.getName() == null) {
            logger.error("Maydon null bo'la olmaydi: from addStudyCenter");
            throw new AllExceptions.NullPointerException("Maydon null bo'la olmaydi");
        }
        if (teachService.phoneNumberChecker(studyCenterDTOforReq.getPhoneNumber())) {
            logger.error("Telefon nomer xato: from addStudyCenter PhoneNumber: {}", studyCenterDTOforReq.getPhoneNumber());
            throw new AllExceptions.IllegalArgumentException("Telefon nomer xato: " + studyCenterDTOforReq.getPhoneNumber());
        }
        logger.info("Yangi o'quv markaz qo'shildi: from addStudyCenter Name: " + studyCenterDTOforReq.getName());
        return studyCenterMapper.toDTO(repository.save(studyCenterMapper.toModel(studyCenterDTOforReq)));
    }
}
