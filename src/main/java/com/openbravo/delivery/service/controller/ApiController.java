package com.openbravo.delivery.service.controller;

import com.openbravo.delivery.service.exceptions.OrderNotFoundException;
import com.openbravo.delivery.service.exceptions.TechnicalFailureException;
import com.openbravo.delivery.service.helpers.helpers;
import com.openbravo.delivery.service.services.DeliveryEstimateService;
import com.openbravo.delivery.service.types.CustomerType;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;

import org.json.JSONObject;

@RestController
public class ApiController {

    private final DeliveryEstimateService deliveryEstimateService;

    public ApiController(DeliveryEstimateService deliveryEstimateService) {
        this.deliveryEstimateService = deliveryEstimateService;
    }

    /**
     *  Handle HTTP POST Request to /index URI.
     * @param @RequestBody requestParams
     * @return String Json with estimated data or error instead.
     */
    @PostMapping("/index")
    public ResponseEntity<String> RequestFromConsumer(@RequestBody Map<String, String> requestParams) throws JSONException {

        // Get Params
        String orderId = requestParams.get("orderId");
        String customerType = requestParams.get("customerType");

        // Process request and return new delivery date
        try {
            int pendingDays = deliveryEstimateService.requestDeliveryEstimation(orderId, CustomerType.valueOf(customerType.toUpperCase()));
            LocalDate estimatedDeliveryDate = LocalDate.now().plusDays(pendingDays);
            JSONObject responseJson = helpers.getJson("estimated_delivery_date", estimatedDeliveryDate);
            return ResponseEntity.ok(responseJson.toString());

        } catch (OrderNotFoundException e) {
            JSONObject responseJson = helpers.getJson("Order not found", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseJson.toString());

        } catch (TechnicalFailureException | JSONException e) {
            JSONObject responseJson = helpers.getJson("Technical failure: ", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseJson.toString());

        } catch (IllegalArgumentException e) {
            JSONObject responseJson = helpers.getJson("Invalid customer type", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseJson.toString());
        }

    }

}
