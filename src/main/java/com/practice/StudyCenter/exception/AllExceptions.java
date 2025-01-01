package com.practice.StudyCenter.exception;

import io.jsonwebtoken.ExpiredJwtException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@RestControllerAdvice
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

    @Getter
    public static class InvalidJwtException extends RuntimeException {
        private final HttpStatus status = HttpStatus.UNAUTHORIZED;

        public InvalidJwtException(String message) {
            super(message);
        }
    }

    @Getter
    public static class Exception extends RuntimeException {
        private final HttpStatus status = HttpStatus.BAD_GATEWAY;

        public Exception(String message) {
            super(message);
        }
    }

//    @ExceptionHandler(ExpiredJwtException.class)
//    public ResponseEntity<String> handleExpiredJwtException(ExpiredJwtException e) {
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT token has expired");
//    }
    @Getter
    public static class ExpiredJwtException extends RuntimeException {
        private final HttpStatus status = HttpStatus.BAD_GATEWAY;

        public ExpiredJwtException(String message) {
            super(message);
        }
    }
}
