package com.tns.spring_DataReportandProcessing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private DataProcessingService dataProcessingService;
    
    @Autowired
    private OrderService orderService;
    
    @Autowired 
    private ReportService reportService; 

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.getCustomerById(id).orElse(null);
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        if (!id.equals(customer.getId())) {
            throw new IllegalArgumentException("Customer ID in path must match ID in the request body");
        }

        return customerService.updateCustomer(customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }

    @GetMapping("/{id}/process-order")
    public String processDataForOrder(@PathVariable Long id) {
        // Assuming we have an OrderService method to get an order by ID
        Order order = orderService.getOrderById(id).orElse(null);

        if (order != null) {
            dataProcessingService.processData(order);
            return "Data processing completed for Order ID: " + id;
        } else {
            return "Order with ID " + id + " not found.";
        }
    }
    @GetMapping("/{id}/generate-report")
    public String generateReportForCustomer(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id).orElse(null);

        if (customer != null) {
            // Assuming you have a method in ReportService to generate a report for a customer
            String report = reportService.generateCustomerReport(customer);
            return "Report generated for Customer ID: " + id + "\n" + report;
        } else {
            return "Customer with ID " + id + " not found.";
        }
    }
}

