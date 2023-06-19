package com.example.Airline.Models;

import jakarta.persistence.*;
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
public class Airports {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
    @NotNull
    private String city_dep;
    @NotNull
    private String city_arr;
    @NotNull
    @ManyToOne
    @JoinColumn (name="fl_trip_id")
    private Trip trips;
}
