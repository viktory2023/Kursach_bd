package com.example.Airline.Controllers;

import com.example.Airline.Models.Aircraft_class;
import com.example.Airline.Models.Aircraft_model;
import com.example.Airline.Repo.IClass;
import com.example.Airline.Repo.IModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("model")
public class Aircraft_modelController {
    private final IModel iModel;
    private final IClass iClass;
    public Aircraft_modelController(IModel iModel, IClass iClass) {
        this.iModel = iModel;
        this.iClass = iClass;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Aircraft_model> aircraftModelIterable = iModel.findAll();
        Iterable<Aircraft_class> aircraft_class = iClass.findAll();
        model.addAttribute("aircraftModelIterable", aircraftModelIterable);
        model.addAttribute("aircraft_class", aircraft_class);
        return "model";
    }
    @PostMapping("add")
    public String add(@RequestParam int number_of_pass,
                      @RequestParam int exploitation,
                      @RequestParam Aircraft_class aircraft_class) {
        Aircraft_model aircraft_model = new Aircraft_model(number_of_pass, exploitation, aircraft_class);
        iModel.save(aircraft_model);
        return "redirect:/model";
    }
    @PostMapping("delete/{id_model}")
    public String delete(@PathVariable(value = "id_model") Long id_model) {
        Aircraft_model aircraft_model = iModel.findById(id_model).orElseThrow();
        iModel.delete(aircraft_model);
        return "redirect:/model";
    }
    @GetMapping("{id_model}")
    public String one(@PathVariable(value = "id_model") Long id_model,
                      Model model) {
        Aircraft_model aircraft_model = iModel.findById(id_model).orElseThrow();
        Iterable<Aircraft_model> aircraftModelIterable = iModel.findAll();
        Iterable<Aircraft_class> aircraft_class = iClass.findAll();
        model.addAttribute("aircraft_model", aircraft_model);
        model.addAttribute("aircraftModelIterable", aircraftModelIterable);
        model.addAttribute("aircraft_class", aircraft_class);
        return "model-creation";
    }
    @PostMapping("{Id_flying}")
    public String update(@PathVariable(value = "id_model") Long id_model,
                         @RequestParam int number_of_pass,
                         @RequestParam int exploitation,
                         @RequestParam Aircraft_class aircraft_class) {
        Aircraft_model aircraft_model = iModel.findById(id_model).orElseThrow();
        aircraft_model.setNumber_of_pass(number_of_pass);
        aircraft_model.setExploitation(exploitation);
        aircraft_model.setAircraft_class(aircraft_class);
        iModel.save(aircraft_model);
        return "redirect:/model";
    }

}
