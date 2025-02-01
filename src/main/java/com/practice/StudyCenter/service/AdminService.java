package com.practice.StudyCenter.service;

import com.practice.StudyCenter.DTO.requestDTO.StudyCenterDTOForRequest;
import com.practice.StudyCenter.DTO.responseDTO.StudyCenterDTOForResponse;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.mapper.StudyCenterMapper;
import com.practice.StudyCenter.repository.StudyCenterRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class AdminService {

    @Autowired
    private StudyCenterRepository stcRepository;

    @Autowired
    private TeacherService teachService;

    private StudyCenterMapper stcMapper = new StudyCenterMapper();

    final private Logger logger = LogManager.getLogger(AdminService.class);

    @Autowired
    public AdminService(StudyCenterRepository stcRepository) {
        this.stcRepository = stcRepository;
    }

    public StudyCenterDTOForResponse createStudyCenter(StudyCenterDTOForRequest studyCenterDTOForRequest) {
        if (studyCenterDTOForRequest.getPhoneNumber() == null || studyCenterDTOForRequest.getName() == null) {
            logger.error("Maydon null bo'la olmaydi: from addStudyCenter");
            throw new AllExceptions.NullPointerException("Maydon null bo'la olmaydi");
        }
        if (teachService.isValidPhoneNumber(studyCenterDTOForRequest.getPhoneNumber())) {
            logger.error("Telefon nomer xato: from addStudyCenter PhoneNumber: {}", studyCenterDTOForRequest.getPhoneNumber());
            throw new AllExceptions.IllegalArgumentException("Telefon nomer xato: " + studyCenterDTOForRequest.getPhoneNumber());
        }
        logger.info("Yangi o'quv markaz qo'shildi: from addStudyCenter Name: " + studyCenterDTOForRequest.getName());
        return stcMapper.toDTO(stcRepository.save(stcMapper.toModel(studyCenterDTOForRequest)));
    }

    public List<StudyCenterDTOForResponse> getAllStudyCenters() {
        return stcMapper.toDTO(stcRepository.findAll());
    }
}
