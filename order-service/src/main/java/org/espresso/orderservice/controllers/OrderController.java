package org.espresso.orderservice.controllers;

import lombok.RequiredArgsConstructor;
import org.espresso.orderservice.dtos.OrderDTO;
import org.espresso.orderservice.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
  private final OrderService service;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public String placeOrder(@RequestBody OrderDTO orderDTO) {
    this.service.placeOrder(orderDTO);
    return "Order placed successfully";
  }
}
