package com.nerfballer.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.nerfballer.orderservice.dto.OrderLineItemsDto;
import com.nerfballer.orderservice.dto.OrderRequest;
import com.nerfballer.orderservice.model.Order;
import com.nerfballer.orderservice.model.OrderLineItems;
import com.nerfballer.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
	
	// constructor injection
	private final OrderRepository orderRepository;
	private final WebClient webClient;
	
	public void placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
			.stream()
			.map(this::mapToDto)
			.toList();
//			.map(orderLineItemsDto -> mapToDto(orderLineItemsDto))
		
		order.setOrderLineItemsList(orderLineItems);
		
		// Call Inventory Service, and place order if product is in stock
		Boolean result = webClient.get()
			.uri("http://localhost:8082/api/inventory")
			.retrieve()
			.bodyToMono(Boolean.class)
			.block();
		
		if(result) {
			orderRepository.save(order);
		} else {
			throw new IllegalArgumentException("Product is not in stock, please try again later.");
		}
		orderRepository.save(order);
	}
	
	private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
		OrderLineItems orderLineItems = new OrderLineItems();
		orderLineItems.setPrice(orderLineItemsDto.getPrice());
		orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
		orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
		return orderLineItems;
	}
}
