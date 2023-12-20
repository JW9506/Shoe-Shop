package com.shoeshop.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryNode {
    private Long id;
    private String name;
    private List<CategoryNode> subcategories = new ArrayList<>();
}
