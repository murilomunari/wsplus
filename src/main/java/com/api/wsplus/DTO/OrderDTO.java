package com.api.wsplus.DTO;

import com.api.wsplus.Entity.Address;
import com.api.wsplus.Entity.Client;
import com.api.wsplus.Entity.OrderItem;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record OrderDTO(LocalDateTime orderDate,
                       Client client,
                       List<OrderItem> items,
                       BigDecimal totalAmount,
                       String paymentMethod,
                       Address shippingAdress) {
}
