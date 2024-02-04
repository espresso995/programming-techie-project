package org.espresso.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.math.BigDecimal;
import org.espresso.productservice.dtos.ProductDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductServiceApplicationTest {

  @Container static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;

  static {
    mongoDBContainer.start();
  }

  @DynamicPropertySource
  static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
    dynamicPropertyRegistry.add("spring.data.mongo.uri", mongoDBContainer::getReplicaSetUrl);
  }

  @Test
  public void shouldCreateProduct() throws Exception {
    this.mockMvc
        .perform(
            MockMvcRequestBuilders.post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(this.getProductRequest())))
        .andExpect(MockMvcResultMatchers.status().isCreated());
  }

  private ProductDTO getProductRequest() {
    return new ProductDTO(null, "iPhone 13", "iPhone13", BigDecimal.valueOf(1200));
  }
}
