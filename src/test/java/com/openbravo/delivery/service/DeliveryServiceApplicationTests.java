package com.openbravo.delivery.service;

import com.openbravo.delivery.service.exceptions.OrderNotFoundException;
import com.openbravo.delivery.service.exceptions.TechnicalFailureException;
import com.openbravo.delivery.service.services.DeliveryEstimateService;
import com.openbravo.delivery.service.services.OrderService;
import com.openbravo.delivery.service.types.CustomerType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class DeliveryEstimateServiceTest {
	@Mock
	private OrderService orderService;

	private DeliveryEstimateService deliveryEstimateService;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
		deliveryEstimateService = new DeliveryEstimateService(orderService);
	}

	@Test
	public void testDaysLeftToDelivery() throws OrderNotFoundException, TechnicalFailureException {
		// Force order service with every possible order status
		when(orderService.getOrderStatus("OrdStat-R")).thenReturn("R");
		when(orderService.getOrderStatus("OrdStat-P")).thenReturn("P");
		when(orderService.getOrderStatus("OrdStat-T")).thenReturn("T");
		when(orderService.getOrderStatus("OrdStat-D")).thenReturn("D");

		// Execute requestDeliveryEstimation with every orderService, deliveryEstimateService and customerType and
		// check if value of days is what we expected
		assertEquals(10, deliveryEstimateService.requestDeliveryEstimation("OrdStat-R", CustomerType.STANDARD));
		assertEquals(7, deliveryEstimateService.requestDeliveryEstimation("OrdStat-R", CustomerType.SILVER));
		assertEquals(3, deliveryEstimateService.requestDeliveryEstimation("OrdStat-R", CustomerType.GOLD));

		assertEquals(7, deliveryEstimateService.requestDeliveryEstimation("OrdStat-P", CustomerType.STANDARD));
		assertEquals(5, deliveryEstimateService.requestDeliveryEstimation("OrdStat-P", CustomerType.SILVER));
		assertEquals(2, deliveryEstimateService.requestDeliveryEstimation("OrdStat-P", CustomerType.GOLD));

		assertEquals(4, deliveryEstimateService.requestDeliveryEstimation("OrdStat-T", CustomerType.STANDARD));
		assertEquals(3, deliveryEstimateService.requestDeliveryEstimation("OrdStat-T", CustomerType.SILVER));
		assertEquals(1, deliveryEstimateService.requestDeliveryEstimation("OrdStat-T", CustomerType.GOLD));

		assertEquals(0, deliveryEstimateService.requestDeliveryEstimation("OrdStat-D", CustomerType.STANDARD));
		assertEquals(0, deliveryEstimateService.requestDeliveryEstimation("OrdStat-D", CustomerType.SILVER));
		assertEquals(0, deliveryEstimateService.requestDeliveryEstimation("OrdStat-D", CustomerType.GOLD));
	}
}

