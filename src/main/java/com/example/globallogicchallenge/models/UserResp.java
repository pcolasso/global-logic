package com.example.globallogicchallenge.models;


import com.example.globallogicchallenge.entities.Phone;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResp {

    private UUID id;
    private LocalDateTime created;
    private LocalDateTime lastLogin;
    private Boolean isActive;
    private String token;
    private String name;
    private String email;
    private String password;
    List<PhoneRequest> phones = new ArrayList<>();

}
