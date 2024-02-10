package org.espresso.inventoryservice.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_inventories")
public class Inventory {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String skuCode;
  private Integer quantity;
}
