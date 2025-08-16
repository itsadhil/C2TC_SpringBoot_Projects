package com.tns.one_to_many_mapping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tns.one_to_many_mapping.entity.Customer;
import com.tns.one_to_many_mapping.entity.Order;
import com.tns.one_to_many_mapping.exception.CustomerNotFoundException;
import com.tns.one_to_many_mapping.repository.CustomerRepository;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    // Save a new customer
    @Override
    public Customer saveCustomer(Customer customer) {
        // Ensure each order has a reference to the customer
        if (customer.getOrders() != null) {
            for (Order order : customer.getOrders()) {
                order.setCustomer(customer);
            }
        }
        return customerRepository.save(customer);
    }

    // Get all customers
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    // Get a customer by ID
    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + id)); // Pass custom message
    }

    // Update a customer by ID
    @Override
    public Customer updateCustomer(Long id, Customer updatedCustomer) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with ID: " + id)); // Pass custom message

        // Update customer fields
        existingCustomer.setName(updatedCustomer.getName());
        existingCustomer.setCity(updatedCustomer.getCity());

        // Ensure each order has a reference to the customer
        if (updatedCustomer.getOrders() != null) {
            for (Order order : updatedCustomer.getOrders()) {
                order.setCustomer(existingCustomer);
            }
            existingCustomer.setOrders(updatedCustomer.getOrders()); // Set updated orders
        }

        return customerRepository.save(existingCustomer);
    }

    // Delete a customer by ID
    @Override
    public boolean deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("Customer not found with ID: " + id); // Pass custom message
        }
        customerRepository.deleteById(id);
        return true;
    }
}
