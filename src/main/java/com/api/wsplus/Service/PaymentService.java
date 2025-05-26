package com.api.wsplus.Service;

import com.api.wsplus.Entity.Cart;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {


    public String paymentLinkFromCart(Cart cart){

        BigDecimal total = cart.getTotalAmount();

        return "https://pagamento-simulador.com/checkout?amount=" + total;
    }
}
