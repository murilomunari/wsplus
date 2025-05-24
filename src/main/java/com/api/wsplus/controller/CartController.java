package com.api.wsplus.controller;

import com.api.wsplus.DTO.CartDTO;
import com.api.wsplus.DTO.CartItemDTO;
import com.api.wsplus.DTO.CartRequest;
import com.api.wsplus.Entity.Cart;
import com.api.wsplus.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;


    @GetMapping("/{cpf}")
    public ResponseEntity<Cart> getCart(@PathVariable String cpf) {
        return ResponseEntity.ok(cartService.getCartByClient(cpf));
    }


    @PostMapping
    public ResponseEntity<Cart> addItem(@RequestBody CartRequest cartRequest) {
        // Verifique se a lista de itens está nula ou vazia
        List<CartItemDTO> cartItemDTOs = cartRequest.getCartItemDTOs();
        if (cartItemDTOs == null || cartItemDTOs.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        CartDTO cartDTO = cartRequest.getCartDTO();


        Cart updatedCart = cartService.addItemToCart(
                cartDTO.clientId(),
                cartItemDTOs.get(0).productId(),
                cartItemDTOs.get(0).quantity()
        );


        for (int i = 1; i < cartItemDTOs.size(); i++) {
            cartService.addItemToCart(
                    cartDTO.clientId(),
                    cartItemDTOs.get(i).productId(),
                    cartItemDTOs.get(i).quantity()
            );
        }

        return ResponseEntity.ok(updatedCart);
    }
}
