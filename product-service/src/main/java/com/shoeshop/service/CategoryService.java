package com.shoeshop.service;

import static com.shoeshop.response.FailureInfo.INVALID_INPUT;
import org.springframework.stereotype.Service;
import com.shoeshop.dto.CategoryDto;
import com.shoeshop.entity.Category;
import com.shoeshop.exceptions.EntityNotFoundException;
import com.shoeshop.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public CategoryDto getCategory(Long id) {

        Category c = categoryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(INVALID_INPUT));
        return CategoryDto.from(c);
        
    }
}
