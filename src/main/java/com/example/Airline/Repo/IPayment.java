package com.example.Airline.Repo;

import com.example.Airline.Models.Making_a_payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPayment extends JpaRepository<Making_a_payment, Long> {
}
