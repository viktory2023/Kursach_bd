package com.example.Airline.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Aircraft_class {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_class;
    @NotNull
    private int range;
    @NotNull
    private int length;
}
