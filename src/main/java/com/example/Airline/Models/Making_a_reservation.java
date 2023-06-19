package com.example.Airline.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import jakarta.persistence.Id;


import java.time.LocalTime;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Making_a_reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id_buy;
    @NotNull
    private int price;
    @NotNull
    private LocalTime reservation_time;
    @NotNull
    @ManyToOne
    @JoinColumn (name="fk_ag_id")
    private Travel_agency travel_agency;
    @NotNull
    @ManyToOne
    @JoinColumn (name="fk_pass_id")
    private Passengers passengers;
}
