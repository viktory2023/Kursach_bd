package com.example.Airline.Controllers;

import com.example.Airline.Models.*;
import com.example.Airline.Repo.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

@Controller
@RequestMapping("trip")
public class TripController {
    private final ITrip iTrip;
    private final IPass iPass;
    private final IAirplane iAirplane;
    private final IFlight iFlight;

    private final IReservation iReservation;

    public TripController(ITrip iTrip, IPass iPass, IAirplane iAirplane, IFlight iFlight,  IReservation iReservation) {
        this.iTrip = iTrip;
        this.iPass = iPass;
        this.iAirplane = iAirplane;
        this.iFlight = iFlight;
        this.iReservation = iReservation;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Trip> tripIterable = iTrip.findAll();
        Iterable<Passengers> passengers = iPass.findAll();
        Iterable<Airplane> airplane = iAirplane.findAll();
        Iterable<Flight> flight = iFlight.findAll();
        model.addAttribute("tripIterable", tripIterable);
        model.addAttribute("passengers", passengers);
        model.addAttribute("airplane", airplane);
        model.addAttribute("flight", flight);
        return "trip";
    }
    @PostMapping("add")
    public String add(@RequestParam int number_of_pass,
                      @RequestParam int number_of_hours,
                      @RequestParam @DateTimeFormat (pattern="HH:mm") LocalTime time_of_departure,
                      @RequestParam @DateTimeFormat (pattern="HH:mm") LocalTime time_of_arrival,
                        @RequestParam String departure,
                        @RequestParam String arrival,
                        @RequestParam int available_seats,
                        @RequestParam Passengers passengers,
                        @RequestParam Airplane airplane,
                        @RequestParam Flight flight) {
        Trip trip = new Trip(number_of_pass, number_of_hours, time_of_departure,
                time_of_arrival, departure, arrival, available_seats,
                passengers, airplane, flight);
        iTrip.save(trip);
        return "redirect:/trip";
    }
    @PostMapping("delete/{Id_flying}")
    public String delete(@PathVariable(value = "Id_flying") Long Id_flying) {
        Trip trip = iTrip.findById(Id_flying).orElseThrow();
        iTrip.delete(trip);
        return "redirect:/trip";
    }
    @GetMapping("{Id_flying}")
    public String one(@PathVariable(value = "Id_flying") Long Id_flying,
                      Model model) {
        Trip trip = iTrip.findById(Id_flying).orElseThrow();
        Iterable<Trip> tripIterable = iTrip.findAll();
        Iterable<Passengers> passengers = iPass.findAll();
        Iterable<Airplane> airplane = iAirplane.findAll();
        Iterable<Flight> flight = iFlight.findAll();
        model.addAttribute("trip", trip);
        model.addAttribute("tripIterable", tripIterable);
        model.addAttribute("passengers", passengers);
        model.addAttribute("airplane", airplane);
        model.addAttribute("flight", flight);
        return "trip-creation";
    }
    @GetMapping("/filter")
    public String filter(@RequestParam(required = false) Passengers passengers,
                         Model model) {
        Iterable<Trip> trips = iTrip.findAll();
        Iterable<Passengers> passengersIterable = iPass.findAll();
        Iterable<Making_a_reservation> makingAReservations = iReservation.findAll();

        if(passengers != null) {
            trips = iTrip.findByPassengers(passengers);
        }

        model.addAttribute("passengersIterable", passengersIterable);
        model.addAttribute("trips", trips);
        model.addAttribute("makingAReservations", makingAReservations);

        return "trip";
    }

    @PostMapping("{Id_flying}")
    public String update(@PathVariable(value = "Id_flying") Long Id_flying,
                       @RequestParam int number_of_pass,
                       @RequestParam int number_of_hours,
                       @RequestParam @DateTimeFormat (pattern="HH:mm") LocalTime time_of_departure,
                       @RequestParam @DateTimeFormat (pattern="HH:mm") LocalTime time_of_arrival,
                       @RequestParam String departure,
                       @RequestParam String arrival,
                       @RequestParam int available_seats,
                         @RequestParam Passengers passengers,
                         @RequestParam Airplane airplane,
                         @RequestParam Flight flight) {
        Trip trip = iTrip.findById(Id_flying).orElseThrow();
        trip.setNumber_of_pass(number_of_pass);
        trip.setNumber_of_hours(number_of_hours);
        trip.setTime_of_departure(time_of_departure);
        trip.setTime_of_arrival(time_of_arrival);
        trip.setDeparture(departure);
        trip.setArrival(arrival);
        trip.setAvailable_seats(available_seats);
        trip.setPassengers(passengers);
        trip.setAirplane(airplane);
        trip.setFlight(flight);
        iTrip.save(trip);
        return "redirect:/trip";
    }

}
