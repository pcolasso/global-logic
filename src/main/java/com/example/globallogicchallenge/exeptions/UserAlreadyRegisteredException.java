package com.example.globallogicchallenge.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserAlreadyRegisteredException extends RuntimeException {

    public UserAlreadyRegisteredException(String exception) {
        super(exception);
    }

    public HttpStatus getHttpStatusCode() {
        return HttpStatus.BAD_REQUEST;
    }

}
