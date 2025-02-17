package com.api.wsplus.DTO;

import java.math.BigDecimal;
import java.util.List;

public record CartDTO(
        Long clientId,
        List<CartItemDTO> items,
        BigDecimal totalAmount
) {}
