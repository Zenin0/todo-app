package com.zenin.todoapp.exception.user;

import com.zenin.todoapp.exception.BaseApplicationException;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserException extends BaseApplicationException {

    String message;

    Exception exception;

    HttpStatusCode httpStatusCode;

    public UserException(String userNotFound, HttpStatus httpStatus) {
        this.message = userNotFound;
        this.httpStatusCode = httpStatus;
        this.exception = null;
    }
}
