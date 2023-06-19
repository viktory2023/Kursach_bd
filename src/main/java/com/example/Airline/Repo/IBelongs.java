package com.example.Airline.Repo;

import com.example.Airline.Models.Belongs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBelongs extends JpaRepository<Belongs, Long> {
}
