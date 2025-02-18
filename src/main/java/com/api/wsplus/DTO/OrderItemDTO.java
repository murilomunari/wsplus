package com.api.wsplus.DTO;

import java.math.BigDecimal;

public record OrderItemDTO(Long productId, String productName, int quantity, BigDecimal price) {}
