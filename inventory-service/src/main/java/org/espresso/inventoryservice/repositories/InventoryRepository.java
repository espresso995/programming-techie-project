package org.espresso.inventoryservice.repositories;

import java.util.List;
import org.espresso.inventoryservice.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

  List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
