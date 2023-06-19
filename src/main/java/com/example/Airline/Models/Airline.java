package com.example.Airline.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import jakarta.persistence.Id;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Airline {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_comp;
    @NotNull
    private int number_of_emp;
    @NotNull
    private int number_of_trips;
    @NotNull
    private int annual_revenue;
    @NotNull
    private int damages;
}
