package com.example.project3bankw10day3.Repository;

import com.example.project3bankw10day3.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findCustomerById(Integer id);
}
