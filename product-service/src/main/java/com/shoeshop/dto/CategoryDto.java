package com.shoeshop.dto;

import com.shoeshop.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    private String name;
    private String description;
    private Long parentCategoryId;

    public static CategoryDto from(Category category) {
        return CategoryDto.builder() //
                .name(category.getName()) //
                .description(category.getDescription()) //
                .parentCategoryId(category.getParentCategory() != null ? category.getParentCategory().getCategoryId() : null) //
                .build();
    }
}
