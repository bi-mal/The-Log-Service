package com.openHouseAI.logs.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LogResourceNotFoundException extends RuntimeException{
    public LogResourceNotFoundException(String message) {
        super(message);
    }
}
