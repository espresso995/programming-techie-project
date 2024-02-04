package org.espresso.productservice.controllers;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.espresso.productservice.dtos.ProductDTO;
import org.espresso.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/products")
@RequiredArgsConstructor
public class ProductController {
  private final ProductService service;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public String createProduct(@RequestBody ProductDTO request) {
    return this.service.createProduct(request);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<ProductDTO> getAllProducts() {
    return this.service.getAllProducts();
  }
}
