package com.example.globallogicchallenge.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {

    @Id
    @Column(columnDefinition = "VARBINARY(16)")
    private UUID id;
    @Column(name = "created")
    private LocalDateTime created;
    @Column(name = "last_login")
    private LocalDateTime lastLogin;
    @Column(name = "is_active")
    private Boolean isActive;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "tokenVersion")
    private String tokenVersion;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Phone> phones = new ArrayList<>();

    public User() {
    }


    @JsonIgnore
    public List<Phone> getPhones() {
        return phones;
    }

    public void setPhones(List<Phone> phones) {
        this.phones = phones;
    }

    @PrePersist
    private void prePersist() { this.created = LocalDateTime.now(); } //caracteristica de java 8

}
