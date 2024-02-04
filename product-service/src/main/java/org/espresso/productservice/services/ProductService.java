package org.espresso.productservice.services;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.espresso.productservice.dtos.ProductDTO;
import org.espresso.productservice.entities.Product;
import org.espresso.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
  private final ProductRepository repository;

  public String createProduct(ProductDTO request) {
    Product product =
        Product.builder()
            .name(request.name())
            .description(request.description())
            .price(request.price())
            .build();
    this.repository.save(product);
    log.info("Product with id {} was saved", product.getId());
    return product.getId();
  }

  public List<ProductDTO> getAllProducts() {
    List<Product> products = this.repository.findAll();

    return products.stream()
        .map(
            product ->
                new ProductDTO(
                    product.getId(),
                    product.getName(),
                    product.getDescription(),
                    product.getPrice()))
        .collect(Collectors.toList());
  }
}
