package com.plantshop.service;

import static com.plantshop.response.FailureInfo.INVALID_INPUT;
import org.springframework.stereotype.Service;
import com.plantshop.dto.CategoryDto;
import com.plantshop.entity.Category;
import com.plantshop.exceptions.EntityNotFoundException;
import com.plantshop.repository.CategoryRepository;
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
