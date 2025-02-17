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
        // Buscar cliente
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado!"));

        // Buscar produto
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));

        // Buscar ou criar o carrinho do cliente
        Cart cart = cartRepository.findByClient(client)
                .orElseGet(() -> new Cart(client, new ArrayList<>(), BigDecimal.ZERO));

        // Criar item do carrinho
        CartItem cartItem = new CartItem(cart, product, quantity, product.getPrice().multiply(BigDecimal.valueOf(quantity)));
        cart.getItems().add(cartItem);
        cart.setTotalAmount(cart.getTotalAmount().add(cartItem.getPrice()));

        // Salvar carrinho e item do carrinho
        cartRepository.save(cart);
        cartItemRepository.save(cartItem);

        return cart;
    }
}
