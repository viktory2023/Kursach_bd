package com.example.Airline.Controllers;

import com.example.Airline.Models.Employee;
import com.example.Airline.Repo.IEmployee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping ("employee")
public class EmployeeController {
    private final IEmployee iEmployee;
    public EmployeeController(IEmployee iEmployee) {
        this.iEmployee = iEmployee;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Employee> employeeIterable = iEmployee.findAll();
        model.addAttribute("employeeIterable", employeeIterable);
        return "employee";
    }
    @PostMapping("add")
    public String add(@RequestParam String full_name_emp,
                      @RequestParam int passport_emp,
                      @RequestParam int number_of_air_hours,
                      @RequestParam int pay) {
        Employee employee = new Employee(full_name_emp, passport_emp,
                number_of_air_hours, pay);
        iEmployee.save(employee);
        return "redirect:/employee";
    }
    @PostMapping("delete/{ip_emp}")
    public String delete(@PathVariable(value = "ip_emp") Long ip_emp) {
        Employee employee = iEmployee.findById(ip_emp).orElseThrow();
        iEmployee.delete(employee);
        return "redirect:/employee";
    }
    @GetMapping("{ip_emp}")
    public String one(@PathVariable(value = "ip_emp") Long ip_emp,
                      Model model) {
        Employee employee = iEmployee.findById(ip_emp).orElseThrow();
        Iterable<Employee> employeeIterable = iEmployee.findAll();
        model.addAttribute("employee", employee);
        model.addAttribute("employeeIterable", employeeIterable);
        return "employee-creation";
    }
    @PostMapping("{ip_emp}")
    public String update(@PathVariable(value = "ip_emp") Long ip_emp,
                         @RequestParam String full_name_emp,
                         @RequestParam int passport_emp,
                         @RequestParam int number_of_air_hours,
                         @RequestParam int pay) {
        Employee employee = iEmployee.findById(ip_emp).orElseThrow();
        employee.setFull_name_emp(full_name_emp);
        employee.setPay(pay);
        employee.setPassport_emp(passport_emp);
        employee.setNumber_of_air_hours(number_of_air_hours);
        iEmployee.save(employee);
        return "redirect:/employee";
    }
}
