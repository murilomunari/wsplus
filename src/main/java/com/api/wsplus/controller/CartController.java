package com.api.wsplus.controller;

import com.api.wsplus.DTO.CartDTO;
import com.api.wsplus.DTO.CartItemDTO;
import com.api.wsplus.Entity.Cart;
import com.api.wsplus.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{clientId}")
    public ResponseEntity<Cart> getCart(@PathVariable Long clientId) {
        return ResponseEntity.ok(cartService.getCartByClient(clientId));
    }

    @PostMapping()
    public ResponseEntity<Cart> addItem(@RequestBody CartDTO cartDTO, CartItemDTO cartItemDTO) {
        Cart updatedCart = cartService.addItemToCart(cartDTO.clientId(), cartItemDTO.productId(), cartItemDTO.quantity());
        return ResponseEntity.ok(updatedCart);
    }


}
