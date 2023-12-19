package com.shoeshop.controller;

import static com.shoeshop.response.SuccessInfo.CREATE_PRODUCT;
import static com.shoeshop.response.SuccessInfo.GET_ALL_PRODUCT;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shoeshop.dto.ProductCreateDto;
import com.shoeshop.dto.ProductDto;
import com.shoeshop.response.DataResponse;
import com.shoeshop.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
@Tag(name = "Product", description = "Product API")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public DataResponse<ProductDto> createProduct(
            @Valid @RequestBody ProductCreateDto productCreateDto) {
        ProductDto productDto = productService.createProduct(productCreateDto);
        log.info("Creating product.");
        return new DataResponse<>(CREATE_PRODUCT, productDto);
    }

    @GetMapping
    @Operation(summary = "Get all product")
    public DataResponse<List<ProductDto>> getAllProduct() {
        List<ProductDto> productDtos = productService.getAllProduct();
        return new DataResponse<>(GET_ALL_PRODUCT, productDtos);
    }
}
