package com.api.wsplus.DTO;

import com.api.wsplus.Entity.OrderItem;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(LocalDateTime orderDate,
                       Long clientId,
                       Long cartId,
                       String paymentMethod,
                       Long shippingAddressId,
                       List<OrderItem> items) {}