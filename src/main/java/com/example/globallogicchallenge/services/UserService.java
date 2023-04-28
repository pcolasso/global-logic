package com.example.globallogicchallenge.services;

import com.example.globallogicchallenge.exeptions.UnauthorizedException;
import com.example.globallogicchallenge.models.SingUpRequest;
import com.example.globallogicchallenge.models.UserResp;

public interface UserService {

    UserResp createUser(SingUpRequest user);

    UserResp authenticateUser(String token);


}
