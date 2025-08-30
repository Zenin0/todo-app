package com.zenin.todoapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "todo_item")
public class TodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String details;
    private boolean done;

    @ManyToOne
    private User user;


}
