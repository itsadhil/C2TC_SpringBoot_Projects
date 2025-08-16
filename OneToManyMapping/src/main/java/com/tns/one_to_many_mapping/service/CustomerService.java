package com.tns.one_to_many_mapping.service;

import com.tns.one_to_many_mapping.entity.Customer;
import com.tns.one_to_many_mapping.exception.CustomerNotFoundException; // Import the custom exception
import java.util.List;

public interface CustomerService {

    // Save a new customer
    Customer saveCustomer(Customer customer);

    // Get all customers
    List<Customer> getAllCustomers();

    // Get customer by ID
    Customer getCustomerById(Long id) throws CustomerNotFoundException; // Declare the exception

    // Update customer by ID
    Customer updateCustomer(Long id, Customer updatedCustomer) throws CustomerNotFoundException; // Declare the exception

    // Delete customer by ID
    boolean deleteCustomer(Long id) throws CustomerNotFoundException; // Declare the exception
}
