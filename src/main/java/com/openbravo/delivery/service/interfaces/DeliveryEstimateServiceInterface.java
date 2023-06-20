package com.openbravo.delivery.service.interfaces;

import com.openbravo.delivery.service.exceptions.OrderNotFoundException;
import com.openbravo.delivery.service.exceptions.TechnicalFailureException;
import com.openbravo.delivery.service.types.CustomerType;

public interface DeliveryEstimateServiceInterface {
     int requestDeliveryEstimation(String orderId, CustomerType customerType) throws OrderNotFoundException, TechnicalFailureException;
     int getShippingAndDeliveryDays(String orderId, CustomerType customerType) throws OrderNotFoundException, TechnicalFailureException;

}
