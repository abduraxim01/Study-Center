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

    @Getter
    public static class UsernameNotFoundException extends RuntimeException {
        private final HttpStatus status = HttpStatus.NOT_FOUND;

        public UsernameNotFoundException(String message) {
            super(message);
        }
    }

    @Getter
    public static class IllegalArgumentException extends RuntimeException {
        private final HttpStatus status = HttpStatus.BAD_REQUEST;

        public IllegalArgumentException(String message) {
            super(message);
        }
    }

    @Getter
    public static class UsernameAlreadyTakenException extends RuntimeException {
        private final HttpStatus status = HttpStatus.CONFLICT;

        public UsernameAlreadyTakenException(String message) {
            super(message);
        }
    }
}
