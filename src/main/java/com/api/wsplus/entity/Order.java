package com.api.wsplus.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders") // "order" é uma palavra reservada no SQL
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime orderDate;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    @NotNull
    private Client client;

    @OneToOne
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    
    private String paymentMethod;

    @ManyToOne
    @JoinColumn(name = "shipping_address_id", nullable = false)
    @NotNull
    private Address shippingAddress;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;

    public Order() {
    }

    public Order(Long id, LocalDateTime orderDate, Client client, Cart cart, String paymentMethod, Address shippingAddress, List<OrderItem> items) {
        this.id = id;
        this.orderDate = orderDate;
        this.client = client;
        this.cart = cart;
        this.paymentMethod = paymentMethod;
        this.shippingAddress = shippingAddress;
        this.items = items;
    }

    public Order(Client client, Cart cart, String paymentMethod, Address shippingAddress, List<OrderItem> items) {
        this.orderDate = LocalDateTime.now();
        this.client = client;
        this.cart = cart;
        this.paymentMethod = paymentMethod;
        this.shippingAddress = shippingAddress;
        this.items = items;
    }


    public Long getId() {
        return id;
    }

    public Order setId(Long id) {
        this.id = id;
        return this;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public Order setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public Client getClient() {
        return client;
    }

    public Order setClient(Client client) {
        this.client = client;
        return this;
    }

    public Cart getCart() {
        return cart;
    }

    public Order setCart(Cart cart) {
        this.cart = cart;
        return this;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Order setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public Order setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
        return this;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Order setItems(List<OrderItem> items) {
        this.items = items;
        return this;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", client=" + client +
                ", cart=" + cart +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", shippingAddress=" + shippingAddress +
                ", items=" + items +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(orderDate, order.orderDate) && Objects.equals(client, order.client) && Objects.equals(cart, order.cart) && Objects.equals(paymentMethod, order.paymentMethod) && Objects.equals(shippingAddress, order.shippingAddress) && Objects.equals(items, order.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, orderDate, client, cart, paymentMethod, shippingAddress, items);
    }
}