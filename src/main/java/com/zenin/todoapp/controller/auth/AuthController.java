package com.zenin.todoapp.controller.auth;

import com.zenin.todoapp.exception.user.UserException;
import com.zenin.todoapp.model.auth.LoginRequest;
import com.zenin.todoapp.model.auth.LoginResponse;
import com.zenin.todoapp.service.auth.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) throws UserException {
        return new ResponseEntity<>(authService.login(req), HttpStatus.OK);
    }
}