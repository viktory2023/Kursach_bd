package com.example.Airline.Controllers;

import com.example.Airline.Models.Aircraft_class;
import com.example.Airline.Repo.IClass;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("class")
public class Aircraft_classController {
    private final IClass iClass;
    public Aircraft_classController(IClass iClass) {
        this.iClass = iClass;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Aircraft_class> aircraft_class = iClass.findAll();
        model.addAttribute("aircraft_class", aircraft_class);
        return "class";
    }
    @PostMapping("add")
    public String add(@RequestParam int range,
                      @RequestParam int length) {
        Aircraft_class aircraft_class = new Aircraft_class(range, length);
        iClass.save(aircraft_class);
        return "redirect:/class";
    }
    @PostMapping("delete/{id_class}")
    public String delete(@PathVariable(value = "id_class") Long id_class) {
        Aircraft_class aircraft_class = iClass.findById(id_class).orElseThrow();
        iClass.delete(aircraft_class);
        return "redirect:/class";
    }
    @GetMapping("{id_class}")
    public String one(@PathVariable(value = "id_class") Long id_class,
                      Model model) {
        Aircraft_class aircraft_class = iClass.findById(id_class).orElseThrow();
        Iterable<Aircraft_class> aircraft_classIterable = iClass.findAll();
        model.addAttribute("aircraft_class", aircraft_class);
        model.addAttribute("aircraft_classIterable", aircraft_classIterable);
        return "class-creation";
    }
    @PostMapping("{id_class}")
    public String update(@PathVariable(value = "id_class") Long id_class,
                         @RequestParam int range,
                         @RequestParam int length) {
        Aircraft_class aircraft_class = iClass.findById(id_class).orElseThrow();
        aircraft_class.setRange(range);
        aircraft_class.setLength(length);
        iClass.save(aircraft_class);
        return "redirect:/class";
    }

}
