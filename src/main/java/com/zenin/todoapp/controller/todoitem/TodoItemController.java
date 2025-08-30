package com.zenin.todoapp.controller.todoitem;

import com.zenin.todoapp.controller.BaseController;
import com.zenin.todoapp.exception.BaseApplicationException;
import com.zenin.todoapp.exception.user.UserException;
import com.zenin.todoapp.model.controller.RestResponse;
import com.zenin.todoapp.model.todoitem.TodoItem;
import com.zenin.todoapp.service.todoitem.TodoItemService;
import com.zenin.todoapp.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(BaseController.BASE_URL + "/todo-items")
public class TodoItemController extends BaseController {

    private final TodoItemService todoItemService;

    public TodoItemController(TodoItemService todoItemService, UserService userService) {
        super(userService);
        this.todoItemService = todoItemService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<RestResponse> getAllByUser(@PathVariable Long userId) throws BaseApplicationException {
        List<TodoItem> list = todoItemService.getAllByUserId(userId);
        return RestResponse.success(list);
    }

    @PostMapping
    public ResponseEntity<RestResponse> create(@RequestBody TodoItem item, Authentication auth) throws UserException {
        item.setUser(getLoggedInUser(auth));
        TodoItem saved = todoItemService.save(item);
        return RestResponse.success(saved);
    }

}
