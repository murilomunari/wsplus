package com.api.wsplus.Entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders") // "order" é uma palavra reservada no SQL, então alterei para "orders"
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Garante que o ID será gerado automaticamente
    private Long id;

    private LocalDateTime orderDate;

    @ManyToOne // Muitos pedidos para um cliente
    @JoinColumn(name = "client_id", nullable = false) // Nome da chave estrangeira
    private Client client;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items;

    private BigDecimal totalAmount;

    private String paymentMethod;

    @ManyToOne // Muitos pedidos podem estar associados a um endereço
    @JoinColumn(name = "shipping_address_id", nullable = false)
    private Address shippingAddress;


    public Order() {}


    public Order(Long id, LocalDateTime orderDate, Client client, List<OrderItem> items,
                 BigDecimal totalAmount, String paymentMethod, Address shippingAddress) {
        this.id = id;
        this.orderDate = orderDate;
        this.client = client;
        this.items = items;
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.shippingAddress = shippingAddress;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
