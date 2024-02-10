package org.espresso.inventoryservice.controllers;

import lombok.RequiredArgsConstructor;
import org.espresso.inventoryservice.services.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/inventories")
@RequiredArgsConstructor
public class InventoryControllers {
  private final InventoryService service;

  @GetMapping("/{sku-code}")
  @ResponseStatus(HttpStatus.OK)
  public boolean isInStock(@PathVariable(value = "sku-code") String skuCode) {
    return this.service.isInStock(skuCode);
  }
}
