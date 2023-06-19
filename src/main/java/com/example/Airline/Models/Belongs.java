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
public class Belongs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id_have;
    @NotNull
    private int number_of_plane;
    @NotNull
    @ManyToOne
    @JoinColumn (name="fk_comp_id")
    private Airline airline;
    @NotNull
    @ManyToOne
    @JoinColumn (name="fk_airplane_id")
    private Airplane airplane;
}
