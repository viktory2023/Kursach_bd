package com.example.Airline.Controllers;


import com.example.Airline.Models.Aircraft_model;
import com.example.Airline.Models.Airplane;
import com.example.Airline.Repo.IAirplane;
import com.example.Airline.Repo.IModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping ("airplane")
public class AirplaneController {
    private final IAirplane iAirplane;
    private final IModel iModel;
    public AirplaneController(IAirplane iAirplane, IModel iModel) {
        this.iAirplane = iAirplane;
        this.iModel = iModel;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Airplane> airplaneIterable = iAirplane.findAll();
        Iterable<Aircraft_model> aircraft_model = iModel.findAll();
        model.addAttribute("airplaneIterable", airplaneIterable);
        model.addAttribute("aircraft_model", aircraft_model);
        return "airplane";
    }
    @PostMapping("add")
    public String add(@RequestParam int seating,
                      @RequestParam int crew,
                      @RequestParam Aircraft_model aircraft_model) {
        Airplane airplane = new Airplane(seating, crew, aircraft_model);
        iAirplane.save(airplane);
        return "redirect:/airplane";
    }
    @PostMapping("delete/{id_plane}")
    public String delete(@PathVariable(value = "id_plane") Long id_plane) {
        Airplane airplane = iAirplane.findById(id_plane).orElseThrow();
        iAirplane.delete(airplane);
        return "redirect:/airplane";
    }
    @GetMapping("{id_plane}")
    public String one(@PathVariable(value = "id_plane") Long id_plane,
                      Model model) {
        Airplane airplane = iAirplane.findById(id_plane).orElseThrow();
        Iterable<Airplane> airplaneIterable = iAirplane.findAll();
        Iterable<Aircraft_model> aircraft_model = iModel.findAll();
        model.addAttribute("airplane", airplane);
        model.addAttribute("airplaneIterable", airplaneIterable);
        model.addAttribute("aircraft_model", aircraft_model);
        return "airplane-creation";
    }
    @PostMapping("{id_plane}")
    public String update(@PathVariable(value = "id_plane") Long id_plane,
                         @RequestParam int seating,
                         @RequestParam int crew,
                         @RequestParam Aircraft_model aircraft_model) {
        Airplane airplane = iAirplane.findById(id_plane).orElseThrow();
        airplane.setSeating(seating);
        airplane.setCrew(crew);
        airplane.setAircraft_model(aircraft_model);
        iAirplane.save(airplane);
        return "redirect:/airplane";
    }
}
