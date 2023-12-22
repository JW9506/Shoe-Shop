package com.shoeshop.service;

import static com.shoeshop.response.FailureInfo.INVALID_INPUT;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import org.springframework.cache.annotation.CacheEvict;
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
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;
    private final ConcurrentHashMap<Long, CategoryNode> categoryNodeMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {

        getHierarchicalCategories();
    }

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
    @Cacheable(value = "getHierarchicalCategories")
    public List<CategoryNode> getHierarchicalCategories() {

        List<Category> allCategories = categoryRepository.findAll();

        // Ensuring categoryNodeMap is empty before repopulating
        categoryNodeMap.clear();
        allCategories.forEach( //
                cat -> categoryNodeMap.put( //
                        cat.getCategoryId(), //
                        new CategoryNode(cat.getCategoryId(), cat.getName(), new ArrayList<>()) //
                ) //
        );

        CategoryNode root = new CategoryNode(); // Root node
        for (Category cat : allCategories) {
            CategoryNode node = categoryNodeMap.get(cat.getCategoryId());
            if (cat.getParentCategory() == null) {
                root.getSubcategories().add(node);
            } else {
                CategoryNode parentNode = categoryNodeMap.get(cat.getParentCategory().getCategoryId());
                parentNode.getSubcategories().add(node);
            }
        }
        return root.getSubcategories();
    }

    @Transactional
    public List<ProductDto> getProductsByCategory(Long categoryId) {

        log.info("getProductsByCategory: {}", categoryId);
        CategoryNode categoryNode = categoryNodeMap.get(categoryId);

        // If category does not exist, throw exception
        if (categoryNode == null) {
            throw new EntityNotFoundException(INVALID_INPUT);
        }

        // Use BFS to get all products
        List<ProductDto> productDtos = new ArrayList<>();
        Deque<CategoryNode> queue = new LinkedList<>();
        queue.add(categoryNode);
        while (!queue.isEmpty()) {
            CategoryNode node = queue.pollFirst();

            List<Product> batch = productRepository.findByCategoryId(node.getId()).orElseThrow(() -> new EntityNotFoundException(INVALID_INPUT));
            List<ProductDto> batchDto = batch.stream()
                                            .map(ProductDto::from)
                                            .collect(Collectors.toList());

            productDtos.addAll(batchDto);
            for (CategoryNode next : node.getSubcategories()) {
                queue.add(next);
            }
        }
        return productDtos;
    }

}
