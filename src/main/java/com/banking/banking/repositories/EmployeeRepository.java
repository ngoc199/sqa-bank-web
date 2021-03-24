package com.banking.banking.repositories;

import java.util.Optional;

import com.banking.banking.models.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    public Optional<Employee> findByUsername(String username);
}
