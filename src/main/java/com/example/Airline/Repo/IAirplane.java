package com.example.Airline.Repo;

import com.example.Airline.Models.Airplane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAirplane extends JpaRepository<Airplane, Long> {
}
