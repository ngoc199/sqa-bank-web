package com.banking.banking.service.customerService;

import com.banking.banking.model.user.customer.Customer;
import com.banking.banking.repository.customerRepo.CustomerRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer findCustomerByUsername(String username) {
        return customerRepository.findByUserLogin_Username(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
