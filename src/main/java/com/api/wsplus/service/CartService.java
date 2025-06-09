package com.api.wsplus.service;

import com.api.wsplus.entity.Cart;
import com.api.wsplus.entity.CartItem;
import com.api.wsplus.entity.Client;
import com.api.wsplus.entity.Product;
import com.api.wsplus.Repository.CartItemRepository;
import com.api.wsplus.Repository.CartRepository;
import com.api.wsplus.Repository.ClientRepository;
import com.api.wsplus.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientRepository clientRepository;

    public Cart getCartByClient(String cpf) {
        Client client = clientRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        return cartRepository.findByClient(client)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado!"));
    }

    public Cart addItemToCart(Long clientId, Long productId, int quantity) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

        if (product.getStockQuantity() < quantity) {
            throw new RuntimeException("Estoque insuficiente para o produto: " + product.getName());
        }

        Cart cart = cartRepository.findByClient(client)
                .orElseGet(() -> {
                    Cart newCart = new Cart(client, new ArrayList<>(), BigDecimal.ZERO);
                    cartRepository.save(newCart);
                    return newCart;
                });

        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            int newTotalQuantity = existingItem.getQuantity() + quantity;

            if (product.getStockQuantity() < newTotalQuantity) {
                throw new RuntimeException("Quantidade total no carrinho excede o estoque disponível para o produto: " + product.getName());
            }

            existingItem.setQuantity(newTotalQuantity);
            existingItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(newTotalQuantity)));
        } else {
            CartItem cartItem = new CartItem(cart, product, quantity, product.getPrice().multiply(BigDecimal.valueOf(quantity)));
            cart.getItems().add(cartItem);
            cartItemRepository.save(cartItem);
        }

        cart.setTotalAmount(cart.getItems().stream()
                .map(CartItem::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add));

        cartRepository.save(cart);
        return cart;
    }


}
