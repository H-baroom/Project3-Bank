package com.example.project3bankw10day3.Repository;

import com.example.project3bankw10day3.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findEmployeeById(Integer id);
}
