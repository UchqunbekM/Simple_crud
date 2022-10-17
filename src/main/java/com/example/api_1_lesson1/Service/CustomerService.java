package com.example.api_1_lesson1.Service;

import com.example.api_1_lesson1.Entity.Customers;
import com.example.api_1_lesson1.Payload.ApiResponse;
import com.example.api_1_lesson1.Payload.CustomerDto;
import com.example.api_1_lesson1.Repository.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo repo;

    public List<Customers> getCustomer() {
        return repo.findAll();
    }

    public Customers getCustomerBYid( int id){
        Optional<Customers> byId = repo.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse addCustomer(CustomerDto customerDto){
        boolean b = repo.existsByPhoneNumber(customerDto.getPhoneNumber());
        if (b) {
            return new ApiResponse("Already have",false);
        }
        Customers customers=new Customers();
        customers.setAddress(customerDto.getAddress());
        customers.setFullName(customerDto.getFullName());
        customers.setPhoneNumber(customerDto.getPhoneNumber());
        repo.save(customers);
        return new ApiResponse("Successfully saved",true);
    }

    public ApiResponse editCustomer(CustomerDto customerDto,int id){
        boolean b = repo.existsByPhoneNumberAndIdNot(customerDto.getPhoneNumber(), id);
        if (b) {
            return new ApiResponse("already have", false);
        }
        Optional<Customers> byId = repo.findById(id);
        if (!byId.isPresent()) {
            return new ApiResponse("Customer not found",false);
        }
        Customers customers = byId.get();
        customers.setPhoneNumber(customerDto.getPhoneNumber());
        customers.setFullName(customerDto.getFullName());
        customers.setAddress(customerDto.getAddress());
        repo.save(customers);
        return new ApiResponse("Successfully saved",true);
    }

    public ApiResponse deleteCustom(int id) {
     try {
         repo.deleteById(id);
         return new ApiResponse("Successfully deleted",true);
     }catch (Exception e){
         return new ApiResponse("Error deleting",false);
     }
    }
}
