package com.shoeshop.service;

import static com.shoeshop.response.FailureInfo.INVALID_INPUT;
import org.springframework.stereotype.Service;
import com.shoeshop.entity.Sku;
import com.shoeshop.exception.EntityNotFoundException;
import com.shoeshop.repository.SkuRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SkuService {

  private final SkuRepository skuRepository;

  public Sku getSkuById(Long skuId) {
    return this.skuRepository.findById(skuId)
        .orElseThrow(() -> new EntityNotFoundException(INVALID_INPUT));
  }
}
