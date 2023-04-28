package com.example.globallogicchallenge.Authorization;

import com.example.globallogicchallenge.entities.User;

import java.util.UUID;

public interface JWTService {

    String createJWT(UUID usr, String password);

    User validateJwt(String authHeader) throws RuntimeException;

}
