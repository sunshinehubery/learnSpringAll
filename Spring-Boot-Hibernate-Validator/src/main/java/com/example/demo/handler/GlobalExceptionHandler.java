package com.example.demo.handler;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.List;
import java.util.Set;

@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)   //用于设置优先级，优先级  低 -> 高
public class GlobalExceptionHandler {

    @ExceptionHandler(value = ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleConstraintViolationException(ConstraintViolationException e){
        StringBuilder message = new StringBuilder();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation:violations) {
            Path path = violation.getPropertyPath();
            String[] strings = StringUtils.splitByWholeSeparatorPreserveAllTokens(path.toString(), ".");
            message.append(strings[1]).append(violation.getMessage()).append(",");
        }
        return message.toString();
    }

    @ExceptionHandler(value = BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String validExceptionHandler(BindException e){
        StringBuilder message = new StringBuilder();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        for (FieldError fieldError:fieldErrors) {
            message.append(fieldError.getField()).append(fieldError.getDefaultMessage()).append(",");
        }
        return message.toString();
    }
}
