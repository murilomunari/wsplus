package com.api.wsplus.Service;

import com.api.wsplus.Entity.Cart;
import com.api.wsplus.Entity.CartItem;
import com.api.wsplus.Entity.Client;
import com.api.wsplus.Entity.Product;
import com.api.wsplus.Repository.CartItemRepository;
import com.api.wsplus.Repository.CartRepository;
import com.api.wsplus.Repository.ClientRepository;
import com.api.wsplus.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    public Cart getCartByClient(Long clientId) {
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        return cartRepository.findByClient(client)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado!"));
    }

    public Cart addItemToCart(Long clientId, Long productId, int quantity) {

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));


        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));


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

            existingItem.setQuantity(existingItem.getQuantity() + quantity);
            existingItem.setPrice(product.getPrice().multiply(BigDecimal.valueOf(existingItem.getQuantity())));
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
