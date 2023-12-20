package com.shoeshop.service;

import static com.shoeshop.response.FailureInfo.INVALID_INPUT;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.shoeshop.dto.CategoryDto;
import com.shoeshop.dto.CategoryNode;
import com.shoeshop.dto.ProductDto;
import com.shoeshop.entity.Category;
import com.shoeshop.entity.Product;
import com.shoeshop.exceptions.EntityNotFoundException;
import com.shoeshop.repository.CategoryRepository;
import com.shoeshop.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    @Transactional
    public CategoryDto getCategory(Long id) {

        Category c = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(INVALID_INPUT));
        return CategoryDto.from(c);

    }

    @Transactional
    public List<CategoryDto> getAllCategories() {

        return categoryRepository.findAll().stream().map(category -> CategoryDto.from(category))
                .collect(Collectors.toList());
    }

    @Transactional
    @Cacheable("categoryTree")
    public List<CategoryNode> getHierarchicalCategories() {
        List<Category> allCategories = categoryRepository.findAll();
        Map<Long, CategoryNode> nodeMap = new HashMap<>();

        allCategories.forEach( //
                cat -> nodeMap.put( //
                        cat.getCategoryId(), //
                        new CategoryNode(cat.getCategoryId(), cat.getName(), new ArrayList<>()) //
                ) //
        );

        CategoryNode root = new CategoryNode(); // Root node
        for (Category cat : allCategories) {
            CategoryNode node = nodeMap.get(cat.getCategoryId());
            if (cat.getParentCategory() == null) {
                root.getSubcategories().add(node);
            } else {
                CategoryNode parentNode = nodeMap.get(cat.getParentCategory().getCategoryId());
                parentNode.getSubcategories().add(node);
            }
        }
        return root.getSubcategories();
    }

    public List<ProductDto> getProductsByCategory(Long categoryId) {
        // Fetch products by category ID
        List<Product> productDtos = productRepository.findByCategoryId(categoryId).orElseThrow(() -> new EntityNotFoundException(INVALID_INPUT));
        return productDtos.stream()
                       .map(ProductDto::from)
                       .collect(Collectors.toList());
    }

}
