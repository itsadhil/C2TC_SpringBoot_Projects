package com.tns.spring_DataReportandProcessing;

import org.springframework.stereotype.Service;

@Service
public class DataProcessingService {

    // Threshold amount for applying a discount
    private static final double DISCOUNT_THRESHOLD = 100.0;
    // Discount percentage
    private static final double DISCOUNT_PERCENTAGE = 10.0;

    public void processData(Order order) {
        double orderAmount = order.getAmount();

        // Check if the order amount is above the threshold
        if (orderAmount > DISCOUNT_THRESHOLD) {
            // Calculate the discount
            double discountAmount = orderAmount * (DISCOUNT_PERCENTAGE / 100.0);

            // Apply the discount
            double discountedAmount = orderAmount - discountAmount;

            // Update the order amount with the discounted amount
            order.setAmount(discountedAmount);

            // Add a note to the order
            order.setOrderNumber(order.getOrderNumber() + " (Discount Applied)");
        } else {
            // No discount applied
            order.setOrderNumber(order.getOrderNumber() + " (No Discount)");
        }
    }
}
