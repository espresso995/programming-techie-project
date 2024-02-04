package org.espresso.productservice.dtos;

import java.math.BigDecimal;

public record ProductDTO(String id, String name, String description, BigDecimal price) {}
