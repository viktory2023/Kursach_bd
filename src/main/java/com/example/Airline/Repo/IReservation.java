package com.example.Airline.Repo;

import com.example.Airline.Models.Making_a_reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReservation extends JpaRepository<Making_a_reservation, Long> {
}
