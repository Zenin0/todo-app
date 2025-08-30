package com.zenin.todoapp.service.auth;

import com.zenin.todoapp.exception.user.UserException;
import com.zenin.todoapp.model.auth.LoginRequest;
import com.zenin.todoapp.model.auth.LoginResponse;
import com.zenin.todoapp.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;


@Service
public class AuthService {

    private final UserRepository userRepository;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, TokenService tokenService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponse login(final LoginRequest req) throws UserException {
        try {
            var user = userRepository.findUserByUsername(req.getUsername()).orElseThrow(() -> new InvalidParameterException("Invalid credentials"));
            if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
                throw new InvalidParameterException("Invalid credentials");
            }

            return new LoginResponse(tokenService.generateToken(user.getUsername()));
        } catch (Exception e) {
            throw new UserException(e.getMessage(), e, HttpStatus.NOT_FOUND);
        }
    }

}
