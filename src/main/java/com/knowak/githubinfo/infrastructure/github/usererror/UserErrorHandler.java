package com.knowak.githubinfo.infrastructure.github.usererror;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserErrorHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public UserNotFoundExceptionHandlerDto handleUserNotFoundError(UserNotFoundException exception){
        String message = exception.getMessage();
        return new UserNotFoundExceptionHandlerDto(HttpStatus.NOT_FOUND.value(), message);
    }
}
