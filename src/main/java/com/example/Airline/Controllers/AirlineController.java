package com.example.Airline.Controllers;

import com.example.Airline.Models.Airline;
import com.example.Airline.Repo.IAirline;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping ("airline")
public class AirlineController {
    private final IAirline iAirline;
    public AirlineController(IAirline iAirline) {
        this.iAirline = iAirline;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Airline> airlineIterable = iAirline.findAll();
        model.addAttribute("airlineIterable", airlineIterable);
        return "airline";
    }
    @PostMapping("add")
    public String add(@RequestParam int number_of_emp,
                      @RequestParam int number_of_trips,
                      @RequestParam int annual_revenue,
                      @RequestParam int damages) {
        Airline airline = new Airline(number_of_emp, number_of_trips, annual_revenue,
                damages);
        iAirline.save(airline);
        return "redirect:/airline";
    }
    @PostMapping("delete/{id_comp}")
    public String delete(@PathVariable(value = "id_comp") Long id_comp) {
        Airline airline = iAirline.findById(id_comp).orElseThrow();
        iAirline.delete(airline);
        return "redirect:/airline";
    }
    @GetMapping("{id_comp}")
    public String one(@PathVariable(value = "id_comp") Long id_comp,
                      Model model) {
        Airline airline = iAirline.findById(id_comp).orElseThrow();
        Iterable<Airline> airlineIterable = iAirline.findAll();
        model.addAttribute("airlineIterable", airlineIterable);
        return "airline-creation";
    }
    @PostMapping("{id_comp}")
    public String update(@PathVariable(value = "id_comp") Long id_comp,
                         @RequestParam int number_of_emp,
                         @RequestParam int number_of_trips,
                         @RequestParam int annual_revenue,
                         @RequestParam int damages) {
        Airline airline = iAirline.findById(id_comp).orElseThrow();
        airline.setNumber_of_emp(number_of_emp);
        airline.setNumber_of_trips(number_of_trips);
        airline.setAnnual_revenue(annual_revenue);
        airline.setDamages(damages);
        iAirline.save(airline);
        return "redirect:/airline";
    }
}
