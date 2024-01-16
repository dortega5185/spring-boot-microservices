package com.nerfballer.orderservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.nerfballer.orderservice.dto.OrderLineItemsDto;
import com.nerfballer.orderservice.dto.OrderRequest;
import com.nerfballer.orderservice.model.Order;
import com.nerfballer.orderservice.model.OrderLineItems;
import com.nerfballer.orderservice.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
	
	// constructor injection
	private final OrderRepository orderRepository;
	
	public void placeOrder(OrderRequest orderRequest) {
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());
		
		List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
			.stream()
			.map(this::mapToDto)
			.toList();
//			.map(orderLineItemsDto -> mapToDto(orderLineItemsDto))
		
		order.setOrderLineItemsList(orderLineItems);
		
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
