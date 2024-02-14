package org.espresso.orderservice.services;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.espresso.clients.inventory.InventoryClient;
import org.espresso.clients.inventory.InventoryDTO;
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
  private final InventoryClient inventoryClient;

  public String placeOrder(OrderDTO orderDTO) {
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

    List<String> skuCodes =
        orderLineItems.stream().map(OrderLineItem::getSkuCode).collect(Collectors.toList());
    List<InventoryDTO> inventories = this.inventoryClient.isInStock(skuCodes);

    boolean allProductsInStock = inventories.stream().allMatch(InventoryDTO::isInStock);

    if (!allProductsInStock) {
      throw new IllegalArgumentException("Some product are not in stock, please try again later");
    }

    Order order =
        Order.builder()
            .orderNumber(UUID.randomUUID().toString())
            .orderLineItems(orderLineItems)
            .build();
    this.repository.save(order);
    return "Order placed successfully";
  }
}
