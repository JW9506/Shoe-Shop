package com.shoeshop.controller;

import static com.shoeshop.response.SuccessInfo.GET_ALL_CATEGORIES;
import static com.shoeshop.response.SuccessInfo.GET_HIERARCHICAL_CATEGORIES;
import static com.shoeshop.response.SuccessInfo.GET_PRODUCTS_BY_CATEGORY;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.shoeshop.dto.CategoryDto;
import com.shoeshop.dto.CategoryNode;
import com.shoeshop.dto.ProductDto;
import com.shoeshop.response.DataResponse;
import com.shoeshop.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
@Tag(name = "Category", description = "Category API")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    @Operation(summary = "Get all categories")
    public DataResponse<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> allCategories = categoryService.getAllCategories();
        return new DataResponse<>(GET_ALL_CATEGORIES, allCategories);
    }

    @GetMapping("/hierarchical")
    @Operation(summary = "Get hierarchical categories")
    public DataResponse<List<CategoryNode>> getHierarchicalCategories() {
        List<CategoryNode> hierarchicalCategories = categoryService.getHierarchicalCategories();
        return new DataResponse<>(GET_HIERARCHICAL_CATEGORIES, hierarchicalCategories);
    }

    @GetMapping("/{categoryId}/products")
    public DataResponse<List<ProductDto>> getProductsByCategory(@PathVariable Long categoryId) {
        List<ProductDto> products = categoryService.getProductsByCategory(categoryId);
        return new DataResponse<>(GET_PRODUCTS_BY_CATEGORY, products);
    }
}
