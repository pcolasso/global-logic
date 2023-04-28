package com.example.globallogicchallenge.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message) {
        super(message);
    }

    public HttpStatus getHttpStatusCode() {
        return HttpStatus.UNAUTHORIZED;
    }

}
