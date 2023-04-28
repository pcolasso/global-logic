package com.example.globallogicchallenge.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "phones")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "number")
    private long number;
    @Column(name = "city_code")
    private int cityCode;
    @Column(name = "country_code")
    private String countryCode;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Phone() {
    }
}
