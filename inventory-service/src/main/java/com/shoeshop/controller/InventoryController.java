package com.shoeshop.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shoeshop.dto.SkuDto;
import com.shoeshop.entity.Sku;
import com.shoeshop.service.SkuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

  private final SkuService skuService;

  @GetMapping("/sku/{skuId}")
  public SkuDto getSkuById(@PathVariable Long skuId) {
    log.info("Fetching sku with id: {}", skuId);

    Sku sku = this.skuService.getSkuById(skuId);
    return SkuDto.from(sku);
  }
}
