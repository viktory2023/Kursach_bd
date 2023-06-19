package com.example.Airline.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Table (name = "flights")
public class Flight {
    @OneToMany(mappedBy = "flight", cascade = CascadeType.REMOVE)
    private Set<Trip> tripSet = new LinkedHashSet<Trip>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long number_of_flight;
    @NotNull
    private LocalDate date_of_flight;
    @NotNull
    private LocalTime time_of_flight;
    @NotNull
    private String name_comp;
    @NotNull
    private int reception_desk;
    @NotNull
    private String waiting_status;
    @NotNull
    private String aircraft_number;
}
