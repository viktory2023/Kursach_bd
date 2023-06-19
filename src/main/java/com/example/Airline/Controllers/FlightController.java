package com.example.Airline.Controllers;

import com.example.Airline.Models.Flight;
import com.example.Airline.Repo.IFlight;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Controller
@RequestMapping ("flight")
public class FlightController {
    private final IFlight iFlight;
    public FlightController(IFlight iFlight) {
        this.iFlight = iFlight;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Flight> flightIterable = iFlight.findAll();
        model.addAttribute("flightIterable", flightIterable);
        return "flight";
    }
    @PostMapping("add")
    public String add(@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date_of_flight,
                      @RequestParam @DateTimeFormat(pattern="HH:mm") LocalTime time_of_flight,
                      @RequestParam String name_comp,
                      @RequestParam int reception_desk,
                      @RequestParam String waiting_status,
                      @RequestParam String aircraft_number) {
        Flight flight = new Flight(date_of_flight,time_of_flight,name_comp,
                reception_desk, waiting_status, aircraft_number);
        iFlight.save(flight);
        return "redirect:/flight";
    }
    @PostMapping("delete/{number_of_flight}")
    public String delete(@PathVariable(value = "number_of_flight") Long number_of_flight) {
        Flight flight = iFlight.findById(number_of_flight).orElseThrow();
        iFlight.delete(flight);
        return "redirect:/flight";
    }
    @GetMapping("{number_of_flight}")
    public String one(@PathVariable(value = "number_of_flight") Long number_of_flight,
                      Model model) {
        Flight flight = iFlight.findById(number_of_flight).orElseThrow();
        Iterable<Flight> flightIterable = iFlight.findAll();
        model.addAttribute("flight", flight);
        model.addAttribute("flightIterable", flightIterable);
        return "flight-creation";
    }
    @PostMapping("{number_of_flight}")
    public String update(@PathVariable(value = "number_of_flight") Long number_of_flight,
                         @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate date_of_flight,
                         @RequestParam @DateTimeFormat (pattern="HH:mm") LocalTime time_of_flight,
                         @RequestParam String name_comp,
                         @RequestParam int reception_desk,
                         @RequestParam String waiting_status,
                         @RequestParam String aircraft_number) {
        Flight flight = iFlight.findById(number_of_flight).orElseThrow();
        flight.setDate_of_flight(date_of_flight);
        flight.setTime_of_flight(time_of_flight);
        flight.setName_comp(name_comp);
        flight.setReception_desk(reception_desk);
        flight.setWaiting_status(waiting_status);
        flight.setAircraft_number(aircraft_number);
        iFlight.save(flight);
        return "redirect:/flight";
    }
}
