
package com.example.api_1_lesson1.Controller;

import com.example.api_1_lesson1.Entity.Customers;
import com.example.api_1_lesson1.Payload.ApiResponse;
import com.example.api_1_lesson1.Payload.CustomerDto;
import com.example.api_1_lesson1.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class ContollerSecond {
    @Autowired
    CustomerService service;

    @GetMapping
    public ResponseEntity<List<Customers>> getCustomer() {
        List<Customers> customer = service.getCustomer();
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/{id}")
    public Customers getCustombyId(@PathVariable int id) {
        return service.getCustomerBYid(id);
    }

    @PostMapping
    public ResponseEntity<ApiResponse> addCustomer(@RequestBody CustomerDto customerDto) {
        ApiResponse apiResponse = service.addCustomer(customerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ?HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/{id}")
    public HttpEntity<ApiResponse> editCustomer(@RequestBody CustomerDto customerDto, @PathVariable int id) {
        ApiResponse apiResponse = service.editCustomer(customerDto, id);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.ACCEPTED : HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<ApiResponse> deleteCustom(@PathVariable int id) {
        ApiResponse apiResponse = service.deleteCustom(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }
}

