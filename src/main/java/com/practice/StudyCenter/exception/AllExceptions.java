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

    @Getter
    public static class InvalidJwtException extends RuntimeException {
        private final HttpStatus status = HttpStatus.UNAUTHORIZED;

        public InvalidJwtException(String message) {
            super(message);
        }
    }

    @Getter
    public static class SignatureException extends RuntimeException {
        private final HttpStatus status = HttpStatus.UNAUTHORIZED;

        public SignatureException(String message) {
            super(message);
        }
    }

    @Getter
    public static class EntityNotFoundException extends RuntimeException {
        private final HttpStatus status = HttpStatus.NOT_FOUND;

        public EntityNotFoundException(String message) {
            super(message);
        }
    }

    @Getter
    public static class NoSuchElementException extends RuntimeException {
        private final HttpStatus status = HttpStatus.NOT_FOUND;

        public NoSuchElementException(String message) {
            super(message);
        }
    }

    @Getter
    public static class ExpiredJwtException extends RuntimeException {
        private final HttpStatus status = HttpStatus.BAD_GATEWAY;

        public ExpiredJwtException(String message) {
            super(message);
        }
    }

    @Getter
    public static class MalformedJwtException extends RuntimeException {
        private final HttpStatus status = HttpStatus.BAD_GATEWAY;

        public MalformedJwtException(String message) {
            super(message);
        }
    }

    @Getter
    public static class InternalServerError extends RuntimeException {
        private final HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        public InternalServerError(String message) {
            super(message);
        }
    }

    @Getter
    public static class AccountExpiredException extends RuntimeException {
        private final HttpStatus status = HttpStatus.FORBIDDEN;

        public AccountExpiredException(String message) {
            super(message);
        }
    }
}
