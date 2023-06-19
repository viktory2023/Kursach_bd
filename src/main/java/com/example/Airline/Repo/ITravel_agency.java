package com.example.Airline.Repo;

import com.example.Airline.Models.Travel_agency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITravel_agency extends JpaRepository<Travel_agency, Long> {
}
