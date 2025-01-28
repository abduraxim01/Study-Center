package com.practice.StudyCenter.model.privileges;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

    ADMIN_CREATE,

    ADMIN_MODIFY,

    STUDENT_SHOW,
    STUDENT_CREATE,
    STUDENT_RESTORE,
    STUDENT_DELETE,
    STUDENT_SOFT_DELETE,

    GROUP_SHOW,
    GROUP_CREATE,
    GROUP_UPDATE,
    GROUP_RESTORE,
    GROUP_DELETE,
    GROUP_SOFT_DELETE,

    PAYMENT_CREATE,
    PAYMENT_SHOW,
    PAYMENT_UPDATE,
    PAYMENT_SOFT_DELETE,

    RESULT_CREATE,
    RESULT_SHOW,
    RESULT_UPDATE,
    RESULT_SOFT_DELETE
}
