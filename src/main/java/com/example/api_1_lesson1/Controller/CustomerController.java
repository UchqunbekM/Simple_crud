package com.example.api_1_lesson1.Controller;

import com.example.api_1_lesson1.Entity.Customers;
import com.example.api_1_lesson1.Payload.ApiResponse;
import com.example.api_1_lesson1.Payload.CustomerDto;
import com.example.api_1_lesson1.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    @Autowired
    CustomerService service;

    @GetMapping
    public List<Customers> getCustomer() {
        List<Customers> customer = service.getCustomer();
        return customer;
    }

    @GetMapping("/{id}")
    public Customers getCustombyId(@PathVariable int id) {
        return service.getCustomerBYid(id);
    }

    @PostMapping
    public ApiResponse addCustomer(@RequestBody CustomerDto customerDto) {
        return service.addCustomer(customerDto);
    }

    @PutMapping("/{id}")
    public ApiResponse editCustomer(@RequestBody CustomerDto customerDto,@PathVariable int id){
        return service.editCustomer(customerDto,id);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteCustom(@PathVariable int  id){
        return service.deleteCustom(id);
    }
}












