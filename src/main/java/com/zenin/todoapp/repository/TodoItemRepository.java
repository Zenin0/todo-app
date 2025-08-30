package com.zenin.todoapp.repository;

import com.zenin.todoapp.model.todoitem.TodoItem;
import com.zenin.todoapp.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {

    List<TodoItem> getTodoItemByUser(User user);

    Long user(User user);
}
