package com.practice.StudyCenter.controller.authController;

import com.practice.StudyCenter.DTO.requestDTO.LoginForRequest;
import com.practice.StudyCenter.exception.AllExceptions;
import com.practice.StudyCenter.service.authService.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class Login {

    @Autowired
    private AuthService authService;

    @PostMapping(value = "/login")
    public ResponseEntity<?> login(@RequestBody LoginForRequest loginForRequest) {
        try {
            return ResponseEntity.ok(authService.login(loginForRequest));
        } catch (AllExceptions.UsernameNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }   catch (AllExceptions.IllegalArgumentException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }
}
