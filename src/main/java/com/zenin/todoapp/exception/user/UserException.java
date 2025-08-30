package com.zenin.todoapp.exception.user;

import com.zenin.todoapp.exception.BaseApplicationException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatusCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserException extends BaseApplicationException {

    String message;

    Exception exception;

    HttpStatusCode httpStatusCode;

}
