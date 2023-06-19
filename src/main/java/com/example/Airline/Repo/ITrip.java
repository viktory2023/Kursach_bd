package com.example.Airline.Repo;

import com.example.Airline.Models.Passengers;
import com.example.Airline.Models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ITrip extends JpaRepository<Trip, Long> {
    Iterable<Trip> findByPassengers(Passengers passengers);
}