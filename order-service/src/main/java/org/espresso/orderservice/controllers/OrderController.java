package org.espresso.orderservice.controllers;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import java.util.concurrent.CompletableFuture;
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
  @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
  @TimeLimiter(name = "inventory")
  @Retry(name = "inventory")
  public CompletableFuture<String> placeOrder(@RequestBody OrderDTO orderDTO) {
    return CompletableFuture.supplyAsync(() -> this.service.placeOrder(orderDTO));
  }

  public CompletableFuture<String> fallbackMethod(
      OrderDTO orderDTO, RuntimeException runtimeException) {
    return CompletableFuture.supplyAsync(
        () -> "Oops! Something went wrong, please order after some time!");
  }
}
