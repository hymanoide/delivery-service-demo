package com.openbravo.delivery.service.services;

import com.openbravo.delivery.service.exceptions.OrderNotFoundException;
import com.openbravo.delivery.service.exceptions.TechnicalFailureException;
import com.openbravo.delivery.service.interfaces.OrderServiceInterface;
import com.openbravo.delivery.service.types.PossibleStatuses;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements OrderServiceInterface {
    @Override
    public String getOrderStatus(String orderId) throws OrderNotFoundException, TechnicalFailureException {
        // Here goes the implementation to get order statuses from "Associated Couriers Inc. API or service"
        // Replace the following for real use case. By the moment it returns a random status for development and testing.
        PossibleStatuses status = PossibleStatuses.randomStatusForThisExercise();

        return String.valueOf(status);
    }
}
