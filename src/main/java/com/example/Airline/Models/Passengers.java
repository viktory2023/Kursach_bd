package com.example.Airline.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "passenger")
public class Passengers {
    @OneToMany(mappedBy = "passengers", cascade = CascadeType.REMOVE)
    private Set<Trip> tripSet = new LinkedHashSet<Trip>();
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_pass;
    @NotNull
    private String full_name;
    @NotNull
    private int passport;
    @NotNull
    private String place_in_plane;
    @NotNull
    @Column(columnDefinition = "DATE")
    private LocalDate date_departure;
    @NotNull
    private String city_departure;
    @NotNull
    private String city_arrival;
    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

}
