package com.example.Airline.Controllers;

import com.example.Airline.Models.Airline;
import com.example.Airline.Models.Making_a_reservation;
import com.example.Airline.Models.Passengers;
import com.example.Airline.Models.Trip;
import com.example.Airline.Repo.IPass;
import com.example.Airline.Repo.IReservation;
import com.example.Airline.Repo.ITrip;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping ("pass")
public class PassengersController {
    private final IPass iPass;
    public PassengersController(IPass iPass) {
        this.iPass = iPass;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Passengers> passengersIterable = iPass.findAll();
        model.addAttribute("passengersIterable", passengersIterable);
        return "pass";
    }
    @PostMapping("add")
    public String add(@RequestParam String full_name,
                      @RequestParam int passport,
                      @RequestParam String place_in_plane,
                      @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date_departure,
                      @RequestParam String city_departure,
                      @RequestParam String city_arrival) {
        Passengers passengers = new Passengers(full_name, passport, place_in_plane,
                date_departure, city_departure, city_arrival);
        iPass.save(passengers);
        return "redirect:/pass";
    }
    @PostMapping("delete/{id}")
    public String delete(@PathVariable(value = "id") Long id) {
        Passengers passengers = iPass.findById(id).orElseThrow();
        iPass.delete(passengers);
        return "redirect:/pass";
    }
    @GetMapping("{id}")
    public String one(@PathVariable(value = "id") Long id,
                      Model model) {
        Passengers passengers = iPass.findById(id).orElseThrow();
        Iterable<Passengers> passengersIterable = iPass.findAll();
        model.addAttribute("passengersIterable", passengersIterable);
        model.addAttribute("passengers", passengers);
        return "pass-creation";
    }

    @PostMapping("{id}")
    public String edit(@PathVariable(value = "id") Long id,
                         @RequestParam String full_name,
                         @RequestParam int passport,
                         @RequestParam String place_in_plane,
                         @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date_departure,
                         @RequestParam String city_departure,
                         @RequestParam String city_arrival) {
        Passengers passengers = iPass.findById(id).orElseThrow();
        passengers.setFull_name(full_name);
        passengers.setPassport(passport);
        passengers.setPlace_in_plane(place_in_plane);
        passengers.setDate_departure(date_departure);
        passengers.setCity_departure(city_departure);
        passengers.setCity_arrival(city_arrival);
        iPass.save(passengers);
        return "redirect:/pass";
    }
}
