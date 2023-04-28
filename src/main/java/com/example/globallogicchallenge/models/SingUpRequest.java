package com.example.globallogicchallenge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@ToString
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class SingUpRequest {

    private String name;
    @NotNull(message = "email is mandatory")
    @NotBlank(message = "email should not be blank")
    private String email;
    @NotNull(message = "password is mandatory")
    @NotBlank(message = "password should not be blank")
    private String password;
    private List<PhoneRequest> phones;

}
