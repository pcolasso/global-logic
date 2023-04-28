package com.example.globallogicchallenge.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{

    public BadRequestException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }
}
