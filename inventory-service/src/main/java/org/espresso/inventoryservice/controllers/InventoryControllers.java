package org.espresso.inventoryservice.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.espresso.clients.inventory.InventoryDTO;
import org.espresso.inventoryservice.services.InventoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/inventories")
@RequiredArgsConstructor
public class InventoryControllers {
  private final InventoryService service;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<InventoryDTO> isInStock(@RequestParam(name = "skuCode") List<String> skuCodes) {
    return this.service.isInStock(skuCodes);
  }
}
