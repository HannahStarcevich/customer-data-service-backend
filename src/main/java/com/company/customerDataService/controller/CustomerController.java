package com.company.customerDataService.controller;

import com.company.customerDataService.dto.Customer;
import com.company.customerDataService.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    @Autowired
    CustomerRepository customerRepository;

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getCustomers(@RequestParam (required = false) Integer id,
                                       @RequestParam (required = false) Customer.Level level,
                                       @RequestParam (required = false) String state,
                                       @RequestParam (required = false) String firstName){

        if(id != null){
            Optional<Customer> customer = customerRepository.findById(id);
               if(!customer.isPresent()){
                   return null;
               } else {
                   List<Customer> customerList = new ArrayList<>();
                   customerList.add(customer.get());
                   return customerList;
               }
        } else if (level != null){
            return customerRepository.findByLevel(level);
        } else if (state != null){
            return customerRepository.findByState(state);
        } else if (firstName != null){
            return customerRepository.findByFirstName(firstName);
        } else {
            return customerRepository.findAll();
        }

    }

//    @RequestMapping(value = "/customer/level/{level}", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public List<Customer> getCustomersByLevel(@PathVariable Customer.Level level) {
//
//        return customerRepository.findByLevel(level);
//
//    }
//
//    @RequestMapping(value = "/customer/state/{state}", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public List<Customer> getCustomersByState(@PathVariable String state) {
//
//        return customerRepository.findByState(state);
//
//    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void updateCustomer(@PathVariable int id, @RequestBody Customer customer) throws Exception {

        if(customer.getId() != id){
            customer.setId(id);
        }

        if(customer.getId() == null){
            throw new IllegalArgumentException("Id in request body " + customer.getId() + "this is correct " + id);
        }

        customerRepository.save(customer);
    }

    @RequestMapping(value = "/customer/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void deleteCustomer(@PathVariable int id) {

        customerRepository.deleteById(id);

    }

}
