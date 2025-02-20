package com.api.wsplus.Service;

import com.api.wsplus.DTO.OrderDTO;
import com.api.wsplus.Entity.*;
import com.api.wsplus.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CartRepository cartRepository;

    @Transactional
    public Order createOrder(Long cartId, Long shippingAddressId, String paymentMethod) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado!"));

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Carrinho está vazio!");
        }

        Client client = cart.getClient();
        Address shippingAddress = addressRepository.findById(shippingAddressId)
                .orElseThrow(() -> new RuntimeException("Endereço de entrega não encontrado!"));

        Order order = new Order(client, cart, paymentMethod, shippingAddress);
        orderRepository.save(order);

        return order;
    }

    public OrderDTO convertToDTO(Order order) {
        return new OrderDTO(
                order.getOrderDate(),
                order.getClient().getId(),
                order.getCart().getId(),
                order.getPaymentMethod(),
                order.getShippingAddress().getId()
        );
    }
}