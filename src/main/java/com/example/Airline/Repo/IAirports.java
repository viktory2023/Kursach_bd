package com.example.Airline.Repo;

import com.example.Airline.Models.Airports;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAirports extends JpaRepository<Airports, Long> {
}
