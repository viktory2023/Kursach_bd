package com.example.Airline.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Table (name = "airplanes")
public class Airplane {
    @OneToMany(mappedBy = "airplane", cascade = CascadeType.REMOVE)
    private Set<Trip> tripSet = new LinkedHashSet<Trip>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_plane;
    @NotNull
    private int seating;
    @NotNull
    private int crew;
    @NotNull
    @OneToOne
    @JoinColumn (name="fk_model_id")
    private Aircraft_model aircraft_model;
}
