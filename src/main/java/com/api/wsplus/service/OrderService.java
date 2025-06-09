package com.api.wsplus.service;

import com.api.wsplus.DTO.OrderDTO;
import com.api.wsplus.entity.*;
import com.api.wsplus.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Transactional
    public Order createOrder(Long cartId, Long shippingAddressId, String paymentMethod, List<OrderItem> items) {

        Cart cart = cartRepository.findById(cartId)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado!"));

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Carrinho está vazio!");
        }

        Client client = cart.getClient();
        Address shippingAddress = addressRepository.findById(shippingAddressId)
                .orElseThrow(() -> new RuntimeException("Endereço de entrega não encontrado!"));

        Order order = new Order(client, cart, paymentMethod, shippingAddress, items);
        order = orderRepository.save(order);

        for (CartItem cartItem : cart.getItems()) {
            Product product = cartItem.getProduct();

            if (product.getStockQuantity() < cartItem.getQuantity()) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + product.getName());
            }

            product.setStockQuantity(product.getStockQuantity() - cartItem.getQuantity());
            productRepository.save(product);


            OrderItem orderItem = new OrderItem(product, cartItem.getQuantity(), cartItem.getPrice(), order);

            orderItemRepository.save(orderItem);
            order.getItems().add(orderItem);

        }

        return order;
    }

    public OrderDTO convertToDTO(Order order) {
        return new OrderDTO(
                order.getOrderDate(),
                order.getClient().getId(),
                order.getCart().getId(),
                order.getPaymentMethod(),
                order.getShippingAddress().getId(),
                order.getItems()
        );
    }
}