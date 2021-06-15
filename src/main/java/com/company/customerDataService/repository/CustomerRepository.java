package com.company.customerDataService.repository;

import com.company.customerDataService.dto.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findByLevel(Customer.Level level);
    List<Customer> findByState(String state);
    List<Customer> findByFirstName(String firstName);
}
