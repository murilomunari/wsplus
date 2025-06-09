package com.api.wsplus.service;

import com.api.wsplus.entity.Cart;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {


    public String paymentLinkFromCart(Cart cart){

        BigDecimal total = cart.getTotalAmount();

        return "https://pagamento-simulador.com/checkout?amount=" + total;
    }
}
