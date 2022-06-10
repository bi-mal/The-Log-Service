package com.openHouseAI.logs.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LogBadRequestException extends RuntimeException {
    public LogBadRequestException(String message) {
        super(message);
    }
}
