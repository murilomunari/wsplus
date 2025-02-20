package com.api.wsplus.DTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderDTO(LocalDateTime orderDate,
                       Long clientId,
                       Long cartId,
                       String paymentMethod,
                       Long shippingAddressId) {}