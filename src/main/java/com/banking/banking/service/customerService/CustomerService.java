package com.banking.banking.service.customerService;

import com.banking.banking.model.user.customer.Customer;
import com.banking.banking.repository.customerRepo.CustomerRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public Customer findCustomerByUsername(String username) throws Exception {
        return customerRepository.findByUserLogin_Username(username)
                .orElseThrow(() -> new Exception());
    }
}
