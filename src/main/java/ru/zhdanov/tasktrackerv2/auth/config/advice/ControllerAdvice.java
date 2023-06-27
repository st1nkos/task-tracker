package ru.zhdanov.tasktrackerv2.auth.config.advice;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.zhdanov.tasktrackerv2.auth.utils.ExceptionBody;
import ru.zhdanov.tasktrackerv2.auth.utils.UserErrorResponse;
import ru.zhdanov.tasktrackerv2.auth.utils.UserNotCreatedException;
import ru.zhdanov.tasktrackerv2.tasks.utils.ResourceNotFoundException;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(UserNotCreatedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public UserErrorResponse handlerResourceNotFound(UserNotCreatedException e) {
        return new UserErrorResponse(e.getMessage());
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public UserErrorResponse handlerResourceNotFound(UsernameNotFoundException e) {
        return new UserErrorResponse(e.getMessage());
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionBody handlerResourceNotFound(ResourceNotFoundException e) {
        return new ExceptionBody(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionBody handleAllOtherException(Exception e) {
        return new ExceptionBody("Internal error");
    }
}
