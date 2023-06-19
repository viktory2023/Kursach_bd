package com.example.Airline.Controllers;

import com.example.Airline.Models.Travel_agency;
import com.example.Airline.Repo.ITravel_agency;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping ("travel_agency")
public class Travel_agentController {
    private final ITravel_agency iTravelAgency;
    public Travel_agentController(ITravel_agency iTravelAgency) {
        this.iTravelAgency = iTravelAgency;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Travel_agency> travel_agencyIterable = iTravelAgency.findAll();
        model.addAttribute("travel_agencyIterable", travel_agencyIterable);
        return "travel_agency";
    }
    @PostMapping("add")
    public String add(@RequestParam String number,
                      @RequestParam String name_ag,
                      @RequestParam String city) {
        Travel_agency travel_agency = new Travel_agency(number, name_ag, city);
        iTravelAgency.save(travel_agency);
        return "redirect:/travel_agency";
    }
    @PostMapping("delete/{id_ag}")
    public String delete(@PathVariable(value = "id_ag") Long id_ag) {
        Travel_agency travel_agency = iTravelAgency.findById(id_ag).orElseThrow();
        iTravelAgency.delete(travel_agency);
        return "redirect:/travel_agency";
    }
    @GetMapping("{id_ag}")
    public String one(@PathVariable(value = "id_ag") Long id_ag,
                      Model model) {
        Travel_agency travel_agency = iTravelAgency.findById(id_ag).orElseThrow();
        Iterable<Travel_agency> travel_agencyIterable = iTravelAgency.findAll();
        model.addAttribute("travel_agency", travel_agency);
        model.addAttribute("travel_agencyIterable", travel_agencyIterable);
        return "travel_agency-creation";
    }
    @PostMapping("{id_ag}")
    public String update(@PathVariable(value = "id_ag") Long id_ag,
                         @RequestParam String number,
                         @RequestParam String name_ag,
                         @RequestParam String city) {
        Travel_agency travel_agency = iTravelAgency.findById(id_ag).orElseThrow();
        travel_agency.setNumber(number);
        travel_agency.setName_ag(name_ag);
        travel_agency.setCity(city);
        iTravelAgency.save(travel_agency);
        return "redirect:/travel_agency";
    }
}
