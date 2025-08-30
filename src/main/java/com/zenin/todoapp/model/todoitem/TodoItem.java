package com.zenin.todoapp.model.todoitem;

import com.zenin.todoapp.model.user.User;
import jakarta.persistence.*;
import lombok.Data;

import static com.zenin.todoapp.utils.Constants.FALSE;
import static com.zenin.todoapp.utils.Constants.NO_DATA;

@Entity
@Data
@Table(name = "todo_item")
public class TodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title = NO_DATA;
    private String details = NO_DATA;
    private boolean done = FALSE;

    @ManyToOne
    private User user;


}
