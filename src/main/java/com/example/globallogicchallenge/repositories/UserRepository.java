package com.example.globallogicchallenge.repositories;

import com.example.globallogicchallenge.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findById(final UUID id);
}
