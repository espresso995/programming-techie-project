package org.espresso.clients.inventory;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@FeignClient("inventory")
public interface InventoryClient {

  @GetMapping("api/v1/inventories")
  @ResponseStatus(HttpStatus.OK)
  List<InventoryDTO> isInStock(@RequestParam(name = "skuCode") List<String> skuCodes);
}
