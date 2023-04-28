package com.example.globallogicchallenge.Authorization;

import com.example.globallogicchallenge.entities.Phone;
import com.example.globallogicchallenge.entities.User;
import com.example.globallogicchallenge.exeptions.UnauthorizedException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JWServiceImplTest {

    @InjectMocks
    private JWServiceImpl jwtService;

    @Mock
    UserRepository userRepository;
    @Mock
    PhoneRepository phoneRepository;


    @Test
    public void testCreateJWTSuccessful() {
        UUID user = UUID.randomUUID();

        String result = jwtService.createJWT(user, "a2asfGfdfdf4");

        assertNotNull(result);

    }

    @Test
    public void testExpiredToken() {
        String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjoiZDAxNTE4NDAtMTAyNi00NzA5LWE3ZTEtZTJhNjVjYzY4YzM3IiwicGFzc3dvcmQiOiJhMmFzZkdmZGZkZjQiLCJzdWIiOiJkMDE1MTg0MC0xMDI2LTQ3MDktYTdlMS1lMmE2NWNjNjhjMzciLCJqdGkiOiIyMTQyNmM0OS03MWE2LTQxYmUtOTU4MS01NzEwZDJkMGIxMDUiLCJpYXQiOjE2ODI3MDU1ODgsImV4cCI6MTY4MjcwNjA4OH0.XShb-KtglLPPvfADsWc-7t3Ds-BYeUKcEDoTt_FOQSE";
        UUID userId = UUID.randomUUID();
        User user = new User(userId, LocalDateTime.now(), LocalDateTime.now(),true, "pablo", "test@example.com", "a2asfGfdfdf4", "21426c49-71a6-41be-9581-5710d2d0b105" , Collections.singletonList(new Phone()));


        when(this.userRepository.findById(UUID.fromString("d0151840-1026-4709-a7e1-e2a65cc68c37"))).thenReturn(user);

        User result = jwtService.validateJwt(token);
        assertNull(result);
    }

    @Test
    public void testUnauthorizedByTokenVersion() {

        UUID userId = UUID.randomUUID();
        String token = jwtService.createJWT(userId, "a2asfGfdfdf4");
        User user = new User(userId, LocalDateTime.now(), LocalDateTime.now(),true, "pablo", "test@example.com", "a2asfGfdfdf4", "21426c49-71a6-41be-9581-5710d2d0b105" , Collections.singletonList(new Phone()));

        when(this.userRepository.findById(any(UUID.class))).thenReturn(user);

        assertThrows(UnauthorizedException.class, () -> {
            this.jwtService.validateJwt("Bearer "+token);
        });

    }

}