package com.example.globallogicchallenge.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private UserResp user;
    private Error error;

    public UserResponse(UserResp user, Error error) {
        this.user = user;
        this.error = error;
    }

    public UserResp getUser() {
        return user;
    }

    public void setUser(UserResp user) {
        this.user = user;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

}
