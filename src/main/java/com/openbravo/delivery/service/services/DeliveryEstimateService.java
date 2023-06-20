package com.openbravo.delivery.service.services;

import com.openbravo.delivery.service.exceptions.OrderNotFoundException;
import com.openbravo.delivery.service.exceptions.TechnicalFailureException;
import com.openbravo.delivery.service.interfaces.DeliveryEstimateServiceInterface;
import com.openbravo.delivery.service.types.CustomerType;
import org.springframework.stereotype.Service;

@Service
public class DeliveryEstimateService implements DeliveryEstimateServiceInterface {
    private final OrderService orderService;

    public DeliveryEstimateService(OrderService orderService) {
        this.orderService = orderService;
    }

    public int requestDeliveryEstimation(String orderId, CustomerType customerType) throws OrderNotFoundException, TechnicalFailureException {
        String orderStatus =  orderService.getOrderStatus(orderId);
        return getShippingAndDeliveryDays(orderStatus, customerType);
    }

    public int getShippingAndDeliveryDays(String orderStatus, CustomerType customerType) throws TechnicalFailureException {
        switch (orderStatus) {
            case "R" -> {
                return switch (customerType) {
                    case STANDARD -> 10;
                    case SILVER -> 7;
                    case GOLD -> 3;
                };
            }
            case "P" -> {
                return switch (customerType) {
                    case STANDARD -> 7;
                    case SILVER -> 5;
                    case GOLD -> 2;
                };
            }
            case "T" -> {
                return switch (customerType) {
                    case STANDARD -> 4;
                    case SILVER -> 3;
                    case GOLD -> 1;
                };
            }
            case "D" -> {
                return 0;
            }
        }
        // Default case: handle not valid values for orderStatus or customerType
        // TODO: Create new exception classes for each error type.
       throw new TechnicalFailureException("customer type or order status is not valid");
    }

}


