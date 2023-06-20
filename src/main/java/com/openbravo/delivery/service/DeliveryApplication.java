package com.openbravo.delivery.service;

import com.openbravo.delivery.service.services.DeliveryEstimateService;
import com.openbravo.delivery.service.services.OrderService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DeliveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(DeliveryApplication.class, args);
	}

	@Bean
	public DeliveryEstimateService deliveryEstimationService(OrderService orderService) {
		return new DeliveryEstimateService(orderService);
	}

}
