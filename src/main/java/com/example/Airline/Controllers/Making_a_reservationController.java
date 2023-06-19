package com.example.Airline.Controllers;

import com.example.Airline.Models.Making_a_reservation;
import com.example.Airline.Models.Passengers;
import com.example.Airline.Models.Travel_agency;
import com.example.Airline.Repo.IAirline;
import com.example.Airline.Repo.IPass;
import com.example.Airline.Repo.IReservation;
import com.example.Airline.Repo.ITravel_agency;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

@Controller
@RequestMapping ("reserv")
public class Making_a_reservationController {
    private final IReservation iReservation;
    private final ITravel_agency iTravelAgency;
    private final IPass iPass;
    public Making_a_reservationController(IReservation iReservation, ITravel_agency iTravelAgency, IPass iPass) {
        this.iReservation = iReservation;
        this.iTravelAgency = iTravelAgency;
        this.iPass = iPass;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Making_a_reservation> making_a_reservationIterable = iReservation.findAll();
        Iterable<Travel_agency> travel_agency = iTravelAgency.findAll();
        Iterable<Passengers> passengers = iPass.findAll();
        model.addAttribute("making_a_reservationIterable", making_a_reservationIterable);
        model.addAttribute("travel_agency", travel_agency);
        model.addAttribute("passengers", passengers);
        return "reserv";
    }
    @PostMapping("add")
    public String add(@RequestParam int price,
                      @RequestParam @DateTimeFormat(pattern="HH:mm") LocalTime reservation_time,
                      @RequestParam Travel_agency travel_agency,
                      @RequestParam Passengers passengers) {
        Making_a_reservation making_a_reservation = new Making_a_reservation(price, reservation_time,
                travel_agency, passengers);
        iReservation.save(making_a_reservation);
        return "redirect:/reserv";
    }
    @PostMapping("delete/{Id_buy}")
    public String delete(@PathVariable(value = "Id_buy") Long Id_buy) {
        Making_a_reservation making_a_reservation = iReservation.findById(Id_buy).orElseThrow();
        iReservation.delete(making_a_reservation);
        return "redirect:/reserv";
    }
    @GetMapping("{id_pass}")
    public String one(@PathVariable(value = "id_pass") Long id_pass,
                      Model model) {
        Making_a_reservation making_a_reservation = iReservation.findById(id_pass).orElseThrow();
        Iterable<Making_a_reservation> making_a_reservationIterable = iReservation.findAll();
        Iterable<Travel_agency> travel_agency = iTravelAgency.findAll();
        Iterable<Passengers> passengers = iPass.findAll();
        model.addAttribute("making_a_reservation", making_a_reservation);
        model.addAttribute("making_a_reservationIterable", making_a_reservationIterable);
        model.addAttribute("making_a_reservationIterable", making_a_reservationIterable);
        model.addAttribute("travel_agency", travel_agency);
        model.addAttribute("passengers", passengers);
        return "reserv-creation";
    }
    @PostMapping("{Id_buy}")
    public String update(@PathVariable(value = "Id_buy") Long Id_buy,
                         @RequestParam int price,
                         @RequestParam @DateTimeFormat (pattern="HH:mm") LocalTime reservation_time,
                         @RequestParam Travel_agency travel_agency,
                         @RequestParam Passengers passengers) {
        Making_a_reservation making_a_reservation = iReservation.findById(Id_buy).orElseThrow();
        making_a_reservation.setPrice(price);
        making_a_reservation.setReservation_time(reservation_time);
        making_a_reservation.setTravel_agency(travel_agency);
        making_a_reservation.setPassengers(passengers);
        iReservation.save(making_a_reservation);
        return "redirect:/reserv";
    }
}
