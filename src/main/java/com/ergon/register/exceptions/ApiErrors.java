package com.ergon.register.exceptions;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

public class ApiErrors {

    @Getter
    private final List<String> errors;

    ApiErrors(List<String> errors){
        this.errors = errors;
    }
    ApiErrors(String error){
        this.errors = Collections.singletonList(error);
    }
}
