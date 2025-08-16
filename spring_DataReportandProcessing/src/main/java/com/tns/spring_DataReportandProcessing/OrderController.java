package com.tns.spring_DataReportandProcessing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private DataProcessingService dataProcessingService;

    @Autowired 
    private ReportService reportService; 
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id).orElse(null);
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @PutMapping("/{id}")
    public Order updateOrder(@PathVariable Long id, @RequestBody Order order) {
        if (!id.equals(order.getId())) {
            throw new IllegalArgumentException("Order ID in path must match ID in the request body");
        }

        return orderService.updateOrder(order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @GetMapping("/{id}/process-data")
    public String processDataForOrder(@PathVariable Long id) {
        Order order = orderService.getOrderById(id).orElse(null);

        if (order != null) {
            // Process the order using DataProcessingService
            dataProcessingService.processData(order);

            // Generate a report for the processed order
            String report = reportService.generateOrderReport(order);
            orderService.updateOrder(order);
            return "Data processing completed for Order ID: " + id + "\n" +
                    "Processed Order Details:\n" + report;
        } else {
            return "Order with ID " + id + " not found.";
        }
    }
    @GetMapping("/{id}/generate-report")
    public String generateReportForOrder(@PathVariable Long id) {
        Order order = orderService.getOrderById(id).orElse(null);

        if (order != null) {
            // Assuming you have a method in ReportService to generate a report for an order
            String report = reportService.generateOrderReport(order);
            return "Report generated for Order ID: " + id + "\n" + report;
        } else {
            return "Order with ID " + id + " not found.";
        }
    }
}





