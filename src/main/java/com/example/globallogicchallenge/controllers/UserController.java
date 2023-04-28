package com.example.globallogicchallenge.controllers;

import com.example.globallogicchallenge.models.SingUpRequest;
import com.example.globallogicchallenge.models.UserResp;
import com.example.globallogicchallenge.models.UserResponse;
import com.example.globallogicchallenge.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Tag(name = "User Creation")
public class UserController {

    @Autowired
    UserService userService;


    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary="User login")
    public ResponseEntity<UserResponse> login(
            @RequestHeader(value = "x-auth-token", required = false) String token) {
        UserResp authToken = userService.authenticateUser(token);
        return new ResponseEntity<>(new UserResponse(authToken, null), HttpStatus.OK);

    }


    @PostMapping(value = "/sing-up", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary="User registration")
    public ResponseEntity<UserResponse> createUser(@RequestBody SingUpRequest user) {
        UserResp userEntity = userService.createUser(user);
        return new ResponseEntity<>(new UserResponse(userEntity, null), HttpStatus.OK);

    }


}
