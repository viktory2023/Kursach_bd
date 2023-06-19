package com.example.Airline.Controllers;


import com.example.Airline.Models.Airports;
import com.example.Airline.Models.Trip;
import com.example.Airline.Repo.IAirports;
import com.example.Airline.Repo.ITrip;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping ("airports")
public class AiroportsController {

    private final IAirports iAirports;
    private final ITrip iTrip;
    public AiroportsController(IAirports iAirports, ITrip iTrip) {
        this.iAirports = iAirports;
        this.iTrip = iTrip;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Airports> airportsIterable = iAirports.findAll();
        Iterable<Trip> trip = iTrip.findAll();
        model.addAttribute("airportsIterable", airportsIterable);
        model.addAttribute("trip", trip);
        return "airports";
    }
    @PostMapping("add")
    public String add(@RequestParam String city_dep,
                      @RequestParam String city_arr,
                      @RequestParam Trip trips) {
        Airports airports = new Airports(city_dep, city_arr, trips);
        iAirports.save(airports);
        return "redirect:/airports";
    }
    @PostMapping("delete/{ID}")
    public String delete(@PathVariable(value = "ID") Long ID) {
        Airports airports = iAirports.findById(ID).orElseThrow();
        iAirports.delete(airports);
        return "redirect:/airports";
    }
    @GetMapping("{ID}")
    public String one(@PathVariable(value = "ID") Long ID,
                      Model model) {
        Airports airports = iAirports.findById(ID).orElseThrow();
        Iterable<Airports> airportsIterable = iAirports.findAll();
        Iterable<Trip> trip = iTrip.findAll();
        model.addAttribute("airports", airports);
        model.addAttribute("airportsIterable", airportsIterable);
        model.addAttribute("trip", trip);
        return "airports-creation";
    }
    @PostMapping("{ID}")
    public String update(@PathVariable(value = "ID") Long ID,
                         @RequestParam String city_dep,
                         @RequestParam String city_arr,
                         @RequestParam Trip trips) {
        Airports airports = iAirports.findById(ID).orElseThrow();
        airports.setCity_dep(city_dep);
        airports.setCity_arr(city_arr);
        airports.setTrips(trips);
        iAirports.save(airports);
        return "redirect:/airports";
    }
}
