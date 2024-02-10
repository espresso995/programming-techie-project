package org.espresso.orderservice.entities;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_order_line_items")
public class OrderLineItem {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String skuCode;
  private BigDecimal price;
  private Integer quantity;
}
