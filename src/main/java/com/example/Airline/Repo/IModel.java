package com.example.Airline.Repo;

import com.example.Airline.Models.Aircraft_model;
import com.example.Airline.Models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IModel extends JpaRepository<Aircraft_model, Long> {
}