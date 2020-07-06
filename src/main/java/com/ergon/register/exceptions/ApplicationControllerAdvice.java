package com.ergon.register.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApplicationControllerAdvice {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleValidationErrors(MethodArgumentNotValidException methodNVE) {
        BindingResult bindingResult = methodNVE.getBindingResult();
        List<String> messages = bindingResult.getAllErrors().
                stream().map(error -> error.getDefaultMessage()).
                collect(Collectors.toList());
        return new ApiErrors(messages);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity handleResponseValidationErrors(ResponseStatusException responseVE) {
        String message = responseVE.getReason();
        HttpStatus httpStatus = responseVE.getStatus();
        ApiErrors apiErrors = new ApiErrors(message);
        return new ResponseEntity(apiErrors, httpStatus);
    }
}
