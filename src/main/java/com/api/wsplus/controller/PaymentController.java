package com.api.wsplus.controller;

import com.api.wsplus.entity.Cart;
import com.api.wsplus.service.CartService;
import com.api.wsplus.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private CartService cartService;

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/generate/{cpf}")
    public ResponseEntity<String> generatePayment(@PathVariable String cpf){

        Cart cart = cartService.getCartByClient(cpf);

        if (cart.getItems().isEmpty()) {
            return ResponseEntity.badRequest().body("Carrinho vazio.");
        }

        String paymentLink = paymentService.paymentLinkFromCart(cart);

        return ResponseEntity.ok(paymentLink);
    }
}
