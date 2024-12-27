package com.practice.StudyCenter.service;

import com.practice.StudyCenter.DTO.StudyCenterDTOForCreate;
import com.practice.StudyCenter.mapper.StudyCenterMapper;
import com.practice.StudyCenter.model.StudyCenter;
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
    public AdminService(StudyCenterRepository repository) {
        this.repository = repository;
    }

    public StudyCenter addStudyCenter(StudyCenterDTOForCreate studyCenterDTO) {
        if (studyCenterDTO.getPhoneNumber() == null || studyCenterDTO.getName() == null) {
            logger.warn("Maydon null bo'la olmaydi: from addStudyCenter");
            throw new NullPointerException("Maydon null bo'la olmaydi");
        }
        logger.info("Yangi o'quv markaz qo'shildi: from addStudyCenter");
        return repository.save(studyCenterMapper.toModel(studyCenterDTO));
    }
}
