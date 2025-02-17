package com.api.wsplus.DTO;

import java.math.BigDecimal;

public record CartItemDTO(
        Long productId,
        String productName,
        int quantity,
        BigDecimal price
) {}
