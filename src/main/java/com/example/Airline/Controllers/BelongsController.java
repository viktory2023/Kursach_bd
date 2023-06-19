package com.example.Airline.Controllers;

import com.example.Airline.Models.Airline;
import com.example.Airline.Models.Airplane;
import com.example.Airline.Models.Belongs;
import com.example.Airline.Repo.IAirline;
import com.example.Airline.Repo.IAirplane;
import com.example.Airline.Repo.IBelongs;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping ("belongs")
public class BelongsController {

    private final IBelongs iBelongs;
    private final IAirline iAirline;
    private final IAirplane iAirplane;
    public BelongsController(IBelongs iBelongs, IAirline iAirline, IAirplane iAirplane) {
        this.iBelongs = iBelongs;
        this.iAirline = iAirline;
        this.iAirplane = iAirplane;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Belongs> belongsIterable = iBelongs.findAll();
        Iterable<Airline> airline = iAirline.findAll();
        Iterable<Airplane> airplane = iAirplane.findAll();
        model.addAttribute("belongsIterable", belongsIterable);
        model.addAttribute("airline", airline);
        model.addAttribute("airplane", airplane);
        return "belongs";
    }
    @PostMapping("add")
    public String add(@RequestParam int number_of_plane,
                      @RequestParam Airline airline,
                      @RequestParam Airplane airplane) {
        Belongs belongs = new Belongs(number_of_plane, airline, airplane);
        iBelongs.save(belongs);
        return "redirect:/belongs";
    }
    @PostMapping("delete/{Id_have}")
    public String delete(@PathVariable(value = "Id_have") Long Id_have) {
        Belongs belongs = iBelongs.findById(Id_have).orElseThrow();
        iBelongs.delete(belongs);
        return "redirect:/belongs";
    }
    @GetMapping("{Id_have}")
    public String one(@PathVariable(value = "Id_have") Long Id_have,
                      Model model) {
        Belongs belongs = iBelongs.findById(Id_have).orElseThrow();
        Iterable<Belongs> belongsIterable = iBelongs.findAll();
        Iterable<Airline> airline = iAirline.findAll();
        Iterable<Airplane> airplane = iAirplane.findAll();
        model.addAttribute("belongs", belongs);
        model.addAttribute("belongsIterable", belongsIterable);
        model.addAttribute("airline", airline);
        model.addAttribute("airplane", airplane);
        return "belongs-creation";
    }
    @PostMapping("{Id_have}")
    public String update(@PathVariable(value = "Id_have") Long Id_have,
                         @RequestParam int number_of_plane,
                         @RequestParam Airline airline,
                         @RequestParam Airplane airplane) {
        Belongs belongs = iBelongs.findById(Id_have).orElseThrow();
        belongs.setNumber_of_plane(number_of_plane);
        belongs.setAirline(airline);
        belongs.setAirplane(airplane);
        iBelongs.save(belongs);
        return "redirect:/belongs";
    }
}
