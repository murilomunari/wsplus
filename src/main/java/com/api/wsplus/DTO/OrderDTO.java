package com.api.wsplus.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(LocalDateTime orderDate,
                       Long clientId,
                       List<OrderItemDTO> items,
                       BigDecimal totalAmount,
                       String paymentMethod,
                       Long shippingAddressId,
                       Long cartId) {}
