package com.example.globallogicchallenge.services;

import com.example.globallogicchallenge.Authorization.JWServiceImpl;
import com.example.globallogicchallenge.entities.Phone;
import com.example.globallogicchallenge.entities.User;
import com.example.globallogicchallenge.exeptions.BadRequestException;
import com.example.globallogicchallenge.models.SingUpRequest;
import com.example.globallogicchallenge.models.UserResp;
import com.example.globallogicchallenge.repositories.PhoneRepository;
import com.example.globallogicchallenge.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;
    @Mock
    private JWServiceImpl jwtService;

    @Mock
    UserRepository userRepository;
    @Mock
    PhoneRepository phoneRepository;

    @Test
    public void testCreateUserInvalidFormatPassword() {
        SingUpRequest user = new SingUpRequest("pablo", "pablo@pablo.com", "sarasa", Collections.EMPTY_LIST);

        assertThrows(BadRequestException.class, () -> {
            this.userServiceImpl.createUser(user);
        });

    }

    @Test
    public void testCreateUserInvalidFormatEmail() {
        SingUpRequest user = new SingUpRequest("pablo", "pablo.com", "sarasa", Collections.EMPTY_LIST);

        assertThrows(BadRequestException.class, () -> {
            this.userServiceImpl.createUser(user);
        });

    }

    @Test
    public void testCreateUserWithNullEmail() {
        SingUpRequest user = new SingUpRequest("pablo", null, "sarasa", Collections.EMPTY_LIST);

        assertThrows(BadRequestException.class, () -> {
            this.userServiceImpl.createUser(user);
        });

    }

    @Test
    public void testAuthenticateUserCreated() throws Exception {
        UUID userId = UUID.randomUUID();
        String password = "password";
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoiOTdiMTZiYzAtODU1MC00ZWY3LWIzNTAtMjY0MjkwMjY4MGI4IiwicGFzc3dvcmQiOiJhMmFzZkdmZGZkZjQiLCJzdWIiOiI5N2IxNmJjMC04NTUwLTRlZjctYjM1MC0yNjQyOTAyNjgwYjgiLCJqdGkiOiIyZTU1ZjMxZi0yYjFhLTQ5Y2QtOTMyNC0wMzM3ODJiNjI5ZjUiLCJpYXQiOjE2ODI2NTI0NzgsImV4cCI6MTY4MjY1Mjk3OH0.ESWl6vOJHdlKu00qgCvcRrnwPvEwJxpZgwF8AUmOoqI";
        User user = new User(userId, LocalDateTime.now(), LocalDateTime.now(),true, "pablo", "test@example.com", password, "tokenVersion" , Collections.singletonList(new Phone()));

        when(jwtService.validateJwt(token)).thenReturn(user);
        when(jwtService.createJWT(any(), any())).thenReturn("eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoiOTdiMTZiYzAtODU1MC00ZWY3LWIzNTAtMjY0MjkwMjY4MGI4IiwicGFzc3dvcmQiOiJhMmFzZkdmZGZkZjQiLCJzdWIiOiI5N2IxNmJjMC04NTUwLTRlZjctYjM1MC0yNjQyOTAyNjgwYjgiLCJqdGkiOiIyZTU1ZjMxZi0yYjFhLTQ5Y2QtOTMyNC0wMzM3ODJiNjI5ZjUiLCJpYXQiOjE2ODI2NTI0NzgsImV4cCI6MTY4MjY1Mjk3OH0.ESWl6vOJHdlKu00qgCvcRrnwPvEwJxpZgwF8AUmOoqI");

        UserResp result = userServiceImpl.authenticateUser(token);
        assertEquals(result.getName(), "pablo");
        assertEquals(result.getEmail(), "test@example.com");
        assertEquals(result.getIsActive(), true);

    }



    @Test
    public void testCreateUserSuccessful() throws Exception {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoiOTdiMTZiYzAtODU1MC00ZWY3LWIzNTAtMjY0MjkwMjY4MGI4IiwicGFzc3dvcmQiOiJhMmFzZkdmZGZkZjQiLCJzdWIiOiI5N2IxNmJjMC04NTUwLTRlZjctYjM1MC0yNjQyOTAyNjgwYjgiLCJqdGkiOiIyZTU1ZjMxZi0yYjFhLTQ5Y2QtOTMyNC0wMzM3ODJiNjI5ZjUiLCJpYXQiOjE2ODI2NTI0NzgsImV4cCI6MTY4MjY1Mjk3OH0.ESWl6vOJHdlKu00qgCvcRrnwPvEwJxpZgwF8AUmOoqI";
        SingUpRequest singUpRequest = new SingUpRequest("pablo", "pablo@pablo.com", "a2asfGfdfdf4", Collections.EMPTY_LIST);

        when(jwtService.createJWT(any(), any())).thenReturn(token);

        UserResp result = userServiceImpl.createUser(singUpRequest);

        assertEquals(result.getName(), "pablo");
        assertEquals(result.getEmail(), "pablo@pablo.com");
        assertEquals(result.getIsActive(), true);

    }

    @Test
    public void testInvalidPasswordFormat() {
        SingUpRequest singUpRequest = new SingUpRequest("pablo", "pablo@pablo.com", "sarasa", Collections.EMPTY_LIST);

        assertThrows(BadRequestException.class, () -> {
            this.userServiceImpl.createUser(singUpRequest);
        });

    }

    @Test
    public void testInvalidEmailFormat() {
        SingUpRequest singUpRequest = new SingUpRequest("pablo", "pablo.com", "a2asfGfdfdf4", Collections.EMPTY_LIST);


        assertThrows(BadRequestException.class, () -> {
            this.userServiceImpl.createUser(singUpRequest);
        });

    }


    @Test
    public void testNullEmailOnRequestShouldThrowException() {
        assertThrows(BadRequestException.class, () -> {
            this.userServiceImpl.validData(null, "pablo@pablo.com");
        });

    }

    @Test
    public void testNullPasswordOnRequestShouldThrowException() {
        assertThrows(BadRequestException.class, () -> {
            this.userServiceImpl.validData(null, "pablo@pablo.com");
        });

    }


}