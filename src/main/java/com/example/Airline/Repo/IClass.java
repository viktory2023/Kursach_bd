package com.example.Airline.Repo;

import com.example.Airline.Models.Aircraft_class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClass extends JpaRepository<Aircraft_class, Long> {
}