package com.api.wsplus.controller;

import com.api.wsplus.DTO.OrderDTO;
import com.api.wsplus.service.OrderService;
import com.api.wsplus.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = orderService.createOrder(orderDTO.cartId(), orderDTO.shippingAddressId(), orderDTO.paymentMethod(), orderDTO.items());
        return ResponseEntity.ok(orderService.convertToDTO(order));
    }
}