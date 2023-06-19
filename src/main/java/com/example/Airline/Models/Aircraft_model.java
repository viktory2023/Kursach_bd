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
public class Aircraft_model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_model;
    @NotNull
    private int number_of_pass;
    @NotNull
    private int exploitation;
    @NotNull
    @OneToOne
    @JoinColumn (name="fk_class_id")
    private Aircraft_class aircraft_class;
}
