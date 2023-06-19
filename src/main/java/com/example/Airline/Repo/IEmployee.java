package com.example.Airline.Repo;

import com.example.Airline.Models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployee extends JpaRepository<Employee, Long> {
}
