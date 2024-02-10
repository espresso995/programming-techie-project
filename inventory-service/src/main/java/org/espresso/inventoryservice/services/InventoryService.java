package org.espresso.inventoryservice.services;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.espresso.clients.inventory.InventoryDTO;
import org.espresso.inventoryservice.entities.Inventory;
import org.espresso.inventoryservice.repositories.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {
  private final InventoryRepository repository;

  @Transactional(readOnly = true)
  public List<InventoryDTO> isInStock(List<String> skuCodes) {
    List<Inventory> inventories = this.repository.findBySkuCodeIn(skuCodes);
    return inventories.stream()
        .map(
            inventory ->
                InventoryDTO.builder()
                    .skuCode(inventory.getSkuCode())
                    .isInStock(inventory.getQuantity() > 0)
                    .build())
        .collect(Collectors.toList());
  }
}
