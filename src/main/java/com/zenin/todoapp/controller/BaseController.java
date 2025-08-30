package com.zenin.todoapp.controller;

import com.zenin.todoapp.exception.user.UserException;
import com.zenin.todoapp.model.user.User;
import com.zenin.todoapp.repository.UserRepository;
import com.zenin.todoapp.service.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BaseController {
    public static final String BASE_URL = "/api/v1";
    private final UserService userService;

    public BaseController(UserService userService) {
        this.userService = userService;
    }

    public User getLoggedInUser(Authentication auth) throws UserException {
        try {
            String username = auth.getName();
            Optional<User> user = userService.findUserByUsername(username);

            if (user.isEmpty()) {
                throw new UserException("User not found", HttpStatus.NOT_FOUND);
            }

            return user.get();

        } catch (Exception e) {
            throw new UserException(e.getMessage(), e, HttpStatus.FORBIDDEN);
        }
    }

}
