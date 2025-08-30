package com.zenin.todoapp.controller.todoitem;

import com.zenin.todoapp.exception.BaseApplicationException;
import com.zenin.todoapp.model.controller.RestResponse;
import com.zenin.todoapp.model.todoitem.TodoItem;
import com.zenin.todoapp.model.user.User;
import com.zenin.todoapp.repository.UserRepository;
import com.zenin.todoapp.service.todoitem.TodoItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todo-items")
public class TodoItemController {

    private final TodoItemService todoItemService;
    private final UserRepository userRepository;

    public TodoItemController(TodoItemService todoItemService, UserRepository userRepository) {
        this.todoItemService = todoItemService;
        this.userRepository = userRepository;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<RestResponse> getAllByUser(@PathVariable Long userId) throws BaseApplicationException {
        List<TodoItem> list = todoItemService.getAllByUserId(userId);
        return RestResponse.success(list);
    }

    @PostMapping
    public ResponseEntity<RestResponse> create(@RequestBody TodoItem item,
                                                                   Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("Authenticated user not found: " + username));
        item.setUser(user);
        TodoItem saved = todoItemService.save(item);
        return RestResponse.success(saved);
    }

}
