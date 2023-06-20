package com.openbravo.delivery.service.interfaces;

import com.openbravo.delivery.service.exceptions.OrderNotFoundException;
import com.openbravo.delivery.service.exceptions.TechnicalFailureException;

public interface OrderServiceInterface {
    String getOrderStatus(String orderId)
            throws OrderNotFoundException, TechnicalFailureException;
}
