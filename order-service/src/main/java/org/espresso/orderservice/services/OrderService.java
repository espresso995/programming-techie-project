package org.espresso.orderservice.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.espresso.orderservice.dtos.OrderDTO;
import org.espresso.orderservice.entities.Order;
import org.espresso.orderservice.entities.OrderLineItem;
import org.espresso.orderservice.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {
  private final OrderRepository repository;

  public void placeOrder(OrderDTO orderDTO) {
    List<OrderLineItem> orderLineItems =
        orderDTO.getOrderLineItems().stream()
            .map(
                orderLineItemDTO ->
                    OrderLineItem.builder()
                        .skuCode(orderLineItemDTO.getSkuCode())
                        .price(orderLineItemDTO.getPrice())
                        .quantity(orderLineItemDTO.getQuantity())
                        .build())
            .collect(Collectors.toList());
    Order order =
        Order.builder()
            .orderNumber(UUID.randomUUID().toString())
            .orderLineItems(orderLineItems)
            .build();
    this.repository.save(order);
  }
}
