package com.plantshop.service;

import org.springframework.stereotype.Service;
import com.plantshop.dto.ProductCreateDto;
import com.plantshop.dto.ProductDto;
import com.plantshop.entity.Category;
import com.plantshop.entity.Product;
import com.plantshop.repository.CategoryRepository;
import com.plantshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductDto createProduct(ProductCreateDto productCreateDto) {
        Category category = categoryRepository.getReferenceById(productCreateDto.getParentCategoryId());
        Product p = Product.builder() //
                .name(productCreateDto.getName()) //
                .description(productCreateDto.getDescription()) //
                .price(productCreateDto.getPrice()) //
                .category(category) //
                .build();
        p = productRepository.save(p);
        return ProductDto.from(p);
    }
}
