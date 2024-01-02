package com.shoeshop.service;

import static com.shoeshop.response.FailureInfo.INVALID_INPUT;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Service;
import com.shoeshop.dto.ProductCreateDto;
import com.shoeshop.dto.ProductDto;
import com.shoeshop.entity.Category;
import com.shoeshop.entity.Product;
import com.shoeshop.exceptions.EntityNotFoundException;
import com.shoeshop.product.model.SkuDto;
import com.shoeshop.repository.CategoryRepository;
import com.shoeshop.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    private Map<String, SkuDto> skuMap = new ConcurrentHashMap<>();

    public ProductDto createProduct(ProductCreateDto productCreateDto) {
        Category category = categoryRepository.getReferenceById(productCreateDto.getCategoryId());
        Product p = Product.builder() //
                .name(productCreateDto.getName()) //
                .description(productCreateDto.getDescription()) //
                .salePrice(productCreateDto.getSalePrice()) //
                .category(category) //
                .build();
        p = productRepository.save(p);
        return ProductDto.from(p);
    }

    public List<ProductDto> getAllProduct() {
        return productRepository.findAll().stream().map(ProductDto::from).toList();
    }

    public ProductDto deleteProduct(Long id) {
        Product p = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(INVALID_INPUT));
        productRepository.deleteById(p.getId());
        return ProductDto.from(p);
    }
}
