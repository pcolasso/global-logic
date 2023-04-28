package com.example.globallogicchallenge.controllers;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

    @Operation(summary = "This controller returns PONG " +
            "to show the service is up and running, is for internal use, " +
            "should not be public to avoid external consumers")
    @GetMapping(value = "/ping")
    public String ping() {

        return "pong";
    }
}
