package com.example.globallogicchallenge.controllers;

import com.example.globallogicchallenge.exeptions.BadRequestException;
import com.example.globallogicchallenge.exeptions.NotFoundException;
import com.example.globallogicchallenge.exeptions.UnauthorizedException;
import com.example.globallogicchallenge.exeptions.UserAlreadyRegisteredException;
import com.example.globallogicchallenge.utils.ApiException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class AdviseController {

    @ExceptionHandler(UnauthorizedException.class)
    protected ResponseEntity<String> handleUnauthorizedException(UnauthorizedException e) {

        ApiException apiException = new ApiException("Unauthorized", e.getMessage(), e.getHttpStatusCode().value(), Instant.now().toEpochMilli());
        return ResponseEntity.status(apiException.getStatusCode())
                .contentType(MediaType.APPLICATION_JSON)
                .body(apiException.toJson());
    }

    @ExceptionHandler(BadRequestException.class)
    protected ResponseEntity<String> handleBadRequestException(BadRequestException e) {

        ApiException apiException = new ApiException("bad_request", e.getMessage(), e.getHttpStatusCode().value(), Instant.now().toEpochMilli());
        return ResponseEntity.status(apiException.getStatusCode())
                .contentType(MediaType.APPLICATION_JSON)
                .body(apiException.toJson());
    }

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<String> handleNotFoundException(NotFoundException e) {

        ApiException apiException = new ApiException("Not Found", e.getMessage(), e.getHttpStatusCode().value(), Instant.now().toEpochMilli());
        return ResponseEntity.status(apiException.getStatusCode())
                .contentType(MediaType.APPLICATION_JSON)
                .body(apiException.toJson());
    }

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    protected ResponseEntity<String> handleUserAlreadyRegisteredException(UserAlreadyRegisteredException e) {

        ApiException apiException = new ApiException("User already registered", e.getMessage(), e.getHttpStatusCode().value(), Instant.now().toEpochMilli());
        return ResponseEntity.status(apiException.getStatusCode())
                .contentType(MediaType.APPLICATION_JSON)
                .body(apiException.toJson());
    }

}
