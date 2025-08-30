package com.zenin.todoapp.service.todoitem;

import com.zenin.todoapp.exception.BaseApplicationException;
import com.zenin.todoapp.exception.user.UserException;
import com.zenin.todoapp.model.todoitem.TodoItem;
import com.zenin.todoapp.repository.TodoItemRepository;
import com.zenin.todoapp.service.user.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoItemService {

    private final TodoItemRepository todoItemRepository;
    private final UserService userService;

    public TodoItemService(TodoItemRepository todoItemRepository, UserService userService) {
        this.todoItemRepository = todoItemRepository;
        this.userService = userService;
    }

    public List<TodoItem> getAllByUserId(Long userId) throws BaseApplicationException {
        return todoItemRepository.getTodoItemByUser(userService.findById(userId));
    }

    public TodoItem save(TodoItem item) {
        return todoItemRepository.save(item);
    }
}
