package com.api.wsplus.Service;

import com.api.wsplus.DTO.OrderDTO;
import com.api.wsplus.Entity.Order;
import com.api.wsplus.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;


    public Order create (OrderDTO orderDTO){
        Order order = new Order();
        order.setOrderDate(orderDTO.orderDate());
        order.setClient(orderDTO.client());
        order.setItems(orderDTO.items());
        order.setPaymentMethod(orderDTO.paymentMethod());
        order.setShippingAddress(orderDTO.shippingAdress());

        return orderRepository.save(order);
    }
}
