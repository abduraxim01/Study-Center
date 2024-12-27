package com.practice.StudyCenter.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class AllExceptions {

    @Getter
    public static class NullPointerException extends RuntimeException {
        private final HttpStatus status = HttpStatus.BAD_REQUEST;

        public NullPointerException(String message) {
            super(message);
        }
    }
}
