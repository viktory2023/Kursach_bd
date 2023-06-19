package com.example.Airline.Models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import java.time.LocalTime;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id_flying;
    @NotNull
    private int number_of_pass;
    @NotNull
    private int number_of_hours;
    @NotNull
    private LocalTime time_of_departure;
    @NotNull
    private LocalTime time_of_arrival;
    @NotNull
    private String departure;
    @NotNull
    private String arrival;
    @NotNull
    private int available_seats;
    @NotNull
    @ManyToOne
    @JoinColumn (name="fk_passengers_id")
    private Passengers passengers;
    @NotNull
    @ManyToOne
    @JoinColumn (name="fk_airplane_id")
    private Airplane airplane;
    @NotNull
    @ManyToOne
    @JoinColumn (name="fk_flight_id")
    private Flight flight;
}
