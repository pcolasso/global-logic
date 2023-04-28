package com.example.globallogicchallenge.repositories;

import com.example.globallogicchallenge.entities.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Integer> {

}
