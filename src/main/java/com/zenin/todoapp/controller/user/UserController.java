package com.zenin.todoapp.controller.user;

import com.zenin.todoapp.exception.BaseApplicationException;
import com.zenin.todoapp.model.user.User;
import com.zenin.todoapp.service.user.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) throws BaseApplicationException {
        return userService.findById(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) throws BaseApplicationException {
        return userService.createUser(user);
    }
}
