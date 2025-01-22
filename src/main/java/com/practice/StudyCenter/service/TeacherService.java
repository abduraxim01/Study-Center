package com.practice.StudyCenter.service;

import com.practice.StudyCenter.DTO.requestDTO.TeacherDTOforReq;
import com.practice.StudyCenter.DTO.response.TeacherDTOforRes;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.mapper.TeacherMapper;
import com.practice.StudyCenter.model.Group;
import com.practice.StudyCenter.model.StudyCenter;
import com.practice.StudyCenter.model.Teacher;
import com.practice.StudyCenter.model.privileges.Permission;
import com.practice.StudyCenter.repository.StudyCenterRepository;
import com.practice.StudyCenter.repository.TeacherRepository;
import com.practice.StudyCenter.service.smsService.SendSMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TeacherService {

    @Autowired
    private TeacherRepository teachRepository;

    @Autowired
    private StudyCenterRepository stcRepository;

    @Autowired
    private SendSMSService smsService;

    final private TeacherMapper teachMapper = new TeacherMapper();

    final private Logger logger = LogManager.getLogger(TeacherService.class);

    final private Pageable pageable = PageRequest.of(0, 10);

    public TeacherDTOforRes createTeacher(TeacherDTOforReq teacherDTOforReq, int study_center_id) throws AllExceptions.NullPointerException {
        if (isValidPhoneNumber(teacherDTOforReq.getPhoneNumber())) {
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
        StudyCenter studyCenter = stcRepository.findStudyCenterById(study_center_id);
        if (studyCenter == null) {
            logger.error("O'quv markazi topilmadi Id: {}", study_center_id);
            throw new AllExceptions.EntityNotFoundException("O'quv markazi topilmadi Id: " + study_center_id);
        }
        logger.info("Yangi o'qituvchi Username: {} , O'quv markaz Id: {}", teacherDTOforReq.getUsername(), study_center_id);
        smsService.sendSMSForAuth(validationPhoneNumber(teacherDTOforReq.getPhoneNumber()));
        return teachMapper.toDTO(teachRepository.save(teachMapper.toModel(teacherDTOforReq, studyCenter)));
    }

    public Teacher setPermission(Set<String> permissions, int user_id) {
        Teacher teacher = teachRepository.findById(user_id).get();
        Set<Permission> permissionSet = new HashSet<>();
        try {
            permissions.forEach(permission -> permissionSet.add(Permission.valueOf(permission)));
        } catch (IllegalArgumentException exception) {
            logger.error("Noto'g'ri ruxsat mavjud: {}", permissions.toArray());
            throw new AllExceptions.IllegalArgumentException("Noto'g'ri ruxsat mavjud: " + Arrays.toString(permissions.toArray()));
        }
        teacher.setPermissions(permissionSet);
        return teachRepository.save(teacher);
    }

    public Page<?> getGroupsByStudyCenterId(int study_center_id) {
        List<Group> groups = stcRepository.findById(study_center_id).get().getGroupList();
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), groups.size());
        return new PageImpl<>(groups.subList(start, end), pageable, groups.size());
    }

    public boolean isValidPhoneNumber(String phoneNumber) { // if number is true  method return false , number is false method return true
        String regex = "^\\+998(20|33|90|91|93|94|99)\\d{7}$";
        return !phoneNumber.trim().matches(regex);
    }

    public String validationPhoneNumber(String phoneNumber) {
        return phoneNumber.trim().substring(4);
    }
}
