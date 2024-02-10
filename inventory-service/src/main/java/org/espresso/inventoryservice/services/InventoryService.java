package org.espresso.inventoryservice.services;

import lombok.RequiredArgsConstructor;
import org.espresso.inventoryservice.repositories.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {
  private final InventoryRepository repository;

  @Transactional(readOnly = true)
  public boolean isInStock(String skuCode) {
    return this.repository.findBySkuCode(skuCode).isPresent();
  }
}
