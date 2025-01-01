package com.practice.StudyCenter.controller.authController;

import com.practice.StudyCenter.DTO.LoginDTO;
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
    public ResponseEntity<Object> login(@RequestBody LoginDTO login) {
        try {
            return ResponseEntity.ok(authService.login(login));
        } catch (AllExceptions.UsernameNotFoundException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }   catch (AllExceptions.IllegalArgumentException exception) {
            return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
        }
    }
}
