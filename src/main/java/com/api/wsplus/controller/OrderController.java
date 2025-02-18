package com.api.wsplus.controller;

import com.api.wsplus.DTO.OrderDTO;
import com.api.wsplus.Service.OrderService;
import com.api.wsplus.Entity.Order;
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
        // Chama o serviço para criar o pedido a partir do DTO
        Order order = orderService.createOrder(orderDTO.cartId(), orderDTO.shippingAddressId(), orderDTO.paymentMethod());

        // Converte o pedido para DTO e retorna
        return ResponseEntity.ok(orderService.convertToDTO(order));
    }

}
