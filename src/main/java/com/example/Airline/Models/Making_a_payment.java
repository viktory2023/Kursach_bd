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
public class Making_a_payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id_pay;
    @NotNull
    private int salary;
    @NotNull
    @ManyToOne
    @JoinColumn (name="fk_comp_id")
    private Airline airline;
    @NotNull
    @ManyToOne
    @JoinColumn (name="fk_emp_id")
    private Employee employee;
}
