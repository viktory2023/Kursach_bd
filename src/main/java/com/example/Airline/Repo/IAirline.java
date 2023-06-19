package com.example.Airline.Repo;

import com.example.Airline.Models.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAirline extends JpaRepository<Airline, Long> {
}
