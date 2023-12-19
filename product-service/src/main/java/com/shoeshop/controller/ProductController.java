package com.shoeshop.controller;

import static com.shoeshop.response.SuccessInfo.CREATE_PRODUCT;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shoeshop.dto.ProductCreateDto;
import com.shoeshop.dto.ProductDto;
import com.shoeshop.response.DataResponse;
import com.shoeshop.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public DataResponse<ProductDto> createProduct(
            @Valid @RequestBody ProductCreateDto productCreateDto) {
        ProductDto productDto = productService.createProduct(productCreateDto);
        log.info("Creating product.");
        return new DataResponse<>(CREATE_PRODUCT, productDto);
    }
}
