package com.example.globallogicchallenge.controllers;

import com.example.globallogicchallenge.exeptions.UnauthorizedException;
import com.example.globallogicchallenge.models.PhoneRequest;
import com.example.globallogicchallenge.models.SingUpRequest;
import com.example.globallogicchallenge.models.UserResp;
import com.example.globallogicchallenge.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
@ExtendWith(MockitoExtension.class)
class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    @Test
    public void testCreateUserAndGetUserCreated() throws Exception {

        SingUpRequest singUpRequest = SingUpRequest.builder()
                .email("pablo@pablo.com")
                .password("a2asfGfdfdf4")
                .name("pablo")
                .phones(Collections.singletonList(new PhoneRequest(1L, 1, "AR")))
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String singUpRequestJson = objectMapper.writeValueAsString(singUpRequest);

        when(userService.createUser(singUpRequest)).
                thenReturn(new UserResp(UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now(), true, "token", "pablo",
                        "pablo@pablo.com", "a2asfGfdfdf4",Collections.singletonList(new PhoneRequest(1L, 1, "AR"))));

        ResultActions response = mockMvc.perform(post("/users/sing-up")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(singUpRequestJson))
                .andExpect(status().isOk());

    }


    @Test
    public void testNoBodyRequestAndUserNotCreated() throws Exception {

        ResultActions response = mockMvc.perform(post("/users/sing-up"));

        response.andExpect(status().is(400));

    }



    @Test
    public void testLoginOk() throws Exception {

        String jwt = "token";

        when(userService.authenticateUser(jwt)).
                thenReturn(new UserResp(UUID.randomUUID(), LocalDateTime.now(), LocalDateTime.now(), true, "token", "pablo",
                        "pablo@pablo.com", "a2asfGfdfdf4",Collections.singletonList(new PhoneRequest(1L, 1, "AR"))));

        ResultActions response = mockMvc.perform(post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("x-auth-token", jwt));

        response.andExpect(status().isOk());

    }

    @Test
    public void testLoginWithInvalidToken() throws Exception {

        String jwt = "tokenInvalid";

        when(userService.authenticateUser(jwt)).
                thenThrow(new UnauthorizedException("token not valid"));

        ResultActions response = mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .header("x-auth-token", jwt));

        response.andExpect(status().is(401));

    }

}