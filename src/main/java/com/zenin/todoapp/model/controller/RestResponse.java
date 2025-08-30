package com.zenin.todoapp.model.controller;

import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;

@Component
@Data
public class RestResponse implements Serializable {
    
    @Serial
    private static final long serialVersionUID = 1L;
    
    private boolean success;
    
    private String message;
    
    private Object data;
    
    public static ResponseEntity<RestResponse> success(Object data) {
        RestResponse response = new RestResponse();
        response.setSuccess(true);
        response.setData(data);
        return ResponseEntity.ok(response);
    }
    
}
