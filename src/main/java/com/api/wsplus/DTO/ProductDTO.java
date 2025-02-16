package com.api.wsplus.DTO;

import java.math.BigDecimal;

public record ProductDTO(String name,
                         String description,
                         BigDecimal price,
                         int stockQuantity,
                         Long categoryId) {  // Agora recebe apenas o ID da categoria
}
