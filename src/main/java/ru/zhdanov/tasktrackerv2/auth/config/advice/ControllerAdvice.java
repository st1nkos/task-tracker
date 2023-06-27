package ru.zhdanov.tasktrackerv2.auth.config.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.zhdanov.tasktrackerv2.auth.utils.ExceptionBody;
import ru.zhdanov.tasktrackerv2.auth.utils.UserErrorResponse;
import ru.zhdanov.tasktrackerv2.auth.utils.UserNotCreatedException;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(UserNotCreatedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public UserErrorResponse handlerResourceNotFound(UserNotCreatedException e) {
        return new UserErrorResponse(e.getMessage());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionBody handleAllOtherException(Exception e) {
        return new ExceptionBody("Internal error");
    }
}
