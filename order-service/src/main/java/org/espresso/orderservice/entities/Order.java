package org.espresso.orderservice.entities;

import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_orders")
public class Order {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String orderNumber;

  @OneToMany(cascade = CascadeType.ALL)
  private List<OrderLineItem> orderLineItems;
}
