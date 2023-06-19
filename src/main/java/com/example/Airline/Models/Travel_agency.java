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
public class Travel_agency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id_ag;
    @NotNull
    private String number;
    @NotNull
    private String name_ag;
    @NotNull
    private String city;
}
