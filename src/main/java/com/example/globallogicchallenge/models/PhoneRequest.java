package com.example.globallogicchallenge.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhoneRequest {

    private long number;
    private int cityCode;
    private String countryCode;

}
