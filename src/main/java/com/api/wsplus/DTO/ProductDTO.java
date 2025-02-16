package com.api.wsplus.DTO;

import com.api.wsplus.Entity.Category;

import java.math.BigDecimal;

public record ProductDTO(String name,
                         String description,
                         BigDecimal price,
                         int stockQuantity,
                         Category category) {
}
