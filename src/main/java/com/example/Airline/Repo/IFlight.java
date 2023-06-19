package com.example.Airline.Repo;

import com.example.Airline.Models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFlight extends JpaRepository<Flight, Long> {
}
