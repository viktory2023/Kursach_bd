package com.example.Airline.Controllers;


import com.example.Airline.Models.Airline;
import com.example.Airline.Models.Employee;
import com.example.Airline.Models.Making_a_payment;
import com.example.Airline.Repo.IAirline;
import com.example.Airline.Repo.IEmployee;
import com.example.Airline.Repo.IPayment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping ("payment")
public class Making_a_paymentController {
    private final IPayment iPayment;
    private final IAirline iAirline;
    private final IEmployee iEmployee;
    public Making_a_paymentController(IPayment iPayment, IAirline iAirline, IEmployee iEmployee) {
        this.iPayment = iPayment;
        this.iAirline = iAirline;
        this.iEmployee = iEmployee;
    }
    @GetMapping
    public String all(Model model) {
        Iterable<Making_a_payment> making_a_paymentIterable = iPayment.findAll();
        Iterable<Airline> airline = iAirline.findAll();
        Iterable<Employee> employee = iEmployee.findAll();
        model.addAttribute("making_a_paymentIterable", making_a_paymentIterable);
        model.addAttribute("airline", airline);
        model.addAttribute("employee", employee);
        return "payment";
    }
    @PostMapping("add")
    public String add(@RequestParam int salary,
                      @RequestParam Airline airline,
                      @RequestParam Employee employee) {
        Making_a_payment making_a_payment = new Making_a_payment(salary,airline,employee);
        iPayment.save(making_a_payment);
        return "redirect:/payment";
    }
    @PostMapping("delete/{Id_pay}")
    public String delete(@PathVariable(value = "Id_pay") Long Id_pay) {
        Making_a_payment making_a_payment = iPayment.findById(Id_pay).orElseThrow();
        iPayment.delete(making_a_payment);
        return "redirect:/payment";
    }
    @GetMapping("{id_pass}")
    public String one(@PathVariable(value = "Id_pay") Long Id_pay,
                      Model model) {
        Making_a_payment making_a_payment = iPayment.findById(Id_pay).orElseThrow();
        Iterable<Making_a_payment> making_a_paymentIterable = iPayment.findAll();
        Iterable<Airline> airline = iAirline.findAll();
        Iterable<Employee> employee = iEmployee.findAll();
        model.addAttribute("making_a_payment", making_a_payment);
        model.addAttribute("making_a_paymentIterable", making_a_paymentIterable);
        model.addAttribute("airline", airline);
        model.addAttribute("employee", employee);
        return "payment-creation";
    }
    @PostMapping("{Id_pay}")
    public String update(@PathVariable(value = "Id_pay") Long Id_pay,
                         @RequestParam int salary,
                         @RequestParam Airline airline,
                         @RequestParam Employee employee) {
        Making_a_payment making_a_payment = iPayment.findById(Id_pay).orElseThrow();
        making_a_payment.setSalary(salary);
        making_a_payment.setAirline(airline);
        making_a_payment.setEmployee(employee);
        iPayment.save(making_a_payment);
        return "redirect:/payment";
    }
}
