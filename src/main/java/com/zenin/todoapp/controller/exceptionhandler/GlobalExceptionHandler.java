package com.zenin.todoapp.controller.exceptionhandler;

import com.zenin.todoapp.exception.BaseApplicationException;
import com.zenin.todoapp.exception.user.UserException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<Map<String, Object>> handleUserException(UserException ex, HttpServletRequest request) {
        int status = ex.getHttpStatusCode() != null ? ex.getHttpStatusCode().value() : 400;

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", status);
        body.put("error", "UserException");
        body.put("message", ex.getMessage());
        body.put("path", request.getRequestURI());

        log.error("UserException: {}", ex.getMessage(), ex);

        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(BaseApplicationException.class)
    public ResponseEntity<Map<String, Object>> handleBaseApplicationException(BaseApplicationException ex, HttpServletRequest request) {
        int status = ex.getHttpStatusCode() != null ? ex.getHttpStatusCode().value() : 400;

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", status);
        body.put("error", ex.getClass().getSimpleName());
        body.put("message", ex.getMessage());
        body.put("path", request.getRequestURI());

        log.error("BaseApplicationException: {}", ex.getMessage(), ex);

        return ResponseEntity.status(status).body(body);
    }


}
